package br.com.rafaellino.taxapi.infra.port.out;

import br.com.rafaellino.taxapi.app.port.out.TaxPaymentDispatch;
import br.com.rafaellino.taxapi.app.port.out.contract.TaxPaymentRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.providers.DefaultAwsRegionProviderChain;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SqsException;

import java.net.URI;

@Component
public class TaxPaymentDispatchImpl implements TaxPaymentDispatch {

  private static final Logger log = LogManager.getLogger(TaxPaymentDispatchImpl.class);

  @Value("${queue.tax-payment}")
  private String queueTaxPayment;

  private static final String LOCALSTACK_ENDPOINT = "http://localhost:4566";


  @Override
  public void dispatchPayment(TaxPaymentRequestDto taxPaymentRequestDto) {
    var om = new ObjectMapper();
    try {
      sendMessage(queueTaxPayment, om.writeValueAsString(taxPaymentRequestDto));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public static SqsClient getSqsClient() {
    var regionProvider = DefaultAwsRegionProviderChain.builder().build();
    var region = regionProvider.getRegion();
    return SqsClient.builder()
        .region(region)
        .endpointOverride(URI.create(LOCALSTACK_ENDPOINT))
        .build();
  }

  public void sendMessage(String queueName, String message) {
    try {
      SqsClient sqsClient = getSqsClient();
      GetQueueUrlRequest getQueueRequest = GetQueueUrlRequest.builder()
          .queueName(queueName)
          .build();

      String queueUrl = sqsClient.getQueueUrl(getQueueRequest).queueUrl();
      SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
          .queueUrl(queueUrl)
          .messageBody(message)
          .delaySeconds(5)
          .build();

      sqsClient.sendMessage(sendMsgRequest);

    } catch (SqsException e) {
      log.error("Could not send message to SQS {}", queueName, e);
      throw new RuntimeException(e);
    }
  }
}

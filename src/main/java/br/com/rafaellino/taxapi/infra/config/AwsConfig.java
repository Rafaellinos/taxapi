package br.com.rafaellino.taxapi.infra.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import software.amazon.awssdk.regions.providers.DefaultAwsRegionProviderChain;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.net.URI;

@Configuration
public class AwsConfig {

  private static final String LOCALSTACK_ENDPOINT = "http://localhost:4566";

  @Bean
  @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  public SqsClient sqsClient() {
    var regionProvider = DefaultAwsRegionProviderChain.builder().build();
    var region = regionProvider.getRegion();
    return SqsClient.builder()
        .region(region)
        .endpointOverride(URI.create(LOCALSTACK_ENDPOINT))
        .build();
  }
}

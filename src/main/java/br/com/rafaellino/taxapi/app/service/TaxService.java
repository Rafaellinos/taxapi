package br.com.rafaellino.taxapi.app.service;

import br.com.rafaellino.taxapi.app.port.in.TaxUseCase;
import br.com.rafaellino.taxapi.app.port.in.contract.TaxInGetContract;
import br.com.rafaellino.taxapi.app.port.in.contract.TaxInPaymentRequestDto;
import br.com.rafaellino.taxapi.app.port.in.contract.TaxInPaymentResponseDto;
import br.com.rafaellino.taxapi.app.port.in.contract.TaxInPaymentStatusResponseDto;
import br.com.rafaellino.taxapi.app.port.mapper.TaxMapper;
import br.com.rafaellino.taxapi.app.port.out.ProdespIntegration;
import br.com.rafaellino.taxapi.app.port.out.TaxPaymentDispatch;
import br.com.rafaellino.taxapi.app.repository.TaxPaymentRepository;
import br.com.rafaellino.taxapi.domain.exception.TaxPaymentNotFoundException;
import br.com.rafaellino.taxapi.domain.model.Document;
import br.com.rafaellino.taxapi.domain.model.TaxPayment;
import br.com.rafaellino.taxapi.domain.model.TaxPaymentStatusEnum;
import br.com.rafaellino.taxapi.domain.model.TaxType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class TaxService implements TaxUseCase {

  private static final Logger log = LogManager.getLogger(TaxService.class);

  private final ProdespIntegration prodespIntegration;
  private final TaxMapper taxMapper;
  private final TaxPaymentRepository taxPaymentRepository;
  private final TaxPaymentDispatch taxPaymentDispatch;

  public TaxService(ProdespIntegration prodespIntegration, TaxMapper taxMapper, TaxPaymentRepository taxPaymentRepository, TaxPaymentDispatch taxPaymentDispatch) {
    this.prodespIntegration = prodespIntegration;
    this.taxMapper = taxMapper;
    this.taxPaymentRepository = taxPaymentRepository;
    this.taxPaymentDispatch = taxPaymentDispatch;
  }

  @Override
  public TaxInGetContract getTaxByTypeAndDocument(List<String> types, String document) {
    new Document(document);
    return taxMapper.toTaxInGetContract(prodespIntegration.getProdespTaxByType(types));
  }

  @Override
  public Long payTax(TaxInPaymentRequestDto taxInPaymentRequestDto) {
    var res = getTaxByTypeAndDocument(Collections.singletonList(taxInPaymentRequestDto.taxType()), taxInPaymentRequestDto.document());
    if (res.tributo().isEmpty()) {
      throw new TaxPaymentNotFoundException("Tax type is not available for payment");
    }
    TaxPayment taxPayment = new TaxPayment(
        TaxPaymentStatusEnum.IN_PROGRESS,
        new Document(taxInPaymentRequestDto.document()),
        TaxType.valueOf(taxInPaymentRequestDto.taxType()),
        res.tributo().get(0).valor(),
        res.tributo().get(0).dueDate()
    );
    taxPayment = taxPaymentRepository.save(taxPayment);
    taxPaymentDispatch.dispatchPayment(taxMapper.toTaxPaymentRequestDto(taxPayment));
    return taxPayment.getId();
  }

  @Override
  public TaxInPaymentResponseDto getPaymentById(Long id) {
    return taxMapper.toInPaymentResponseDto(taxPaymentRepository.findById(id));
  }

  @Override
  public TaxInPaymentResponseDto rejectTaxPayment(TaxInPaymentStatusResponseDto taxInPaymentStatusResponseDto) {
    TaxInPaymentResponseDto res = updateStatus(taxInPaymentStatusResponseDto.id(), TaxPaymentStatusEnum.ERROR);
    log.info("payment failed due to {}", taxInPaymentStatusResponseDto.cause());
    return res;
  }

  @Override
  public TaxInPaymentResponseDto acceptTaxPayment(TaxInPaymentStatusResponseDto taxInPaymentStatusResponseDto) {
    TaxInPaymentResponseDto res = updateStatus(taxInPaymentStatusResponseDto.id(), TaxPaymentStatusEnum.COMPLETED);
    log.info("payment successfully completed due to {}", taxInPaymentStatusResponseDto.cause());
    return res;
  }

  private TaxInPaymentResponseDto updateStatus(Long id, TaxPaymentStatusEnum statusEnum) {
    TaxPayment taxPayment = taxPaymentRepository.findById(id);
    if (taxPayment == null) {
      throw new TaxPaymentNotFoundException("Payment not found in db");
    }
    taxPayment.setStatus(statusEnum);
    return taxMapper.toInPaymentResponseDto(taxPayment);
  }
}

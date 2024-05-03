package br.com.rafaellino.taxapi.app.port.in;


import br.com.rafaellino.taxapi.app.port.in.contract.TaxInGetContract;
import br.com.rafaellino.taxapi.app.port.in.contract.TaxInPaymentRequestDto;
import br.com.rafaellino.taxapi.app.port.in.contract.TaxInPaymentResponseDto;

import java.util.List;

public interface TaxUseCase {
  TaxInGetContract getTaxByTypeAndDocument(List<String> types, String document);

  Long payTax(TaxInPaymentRequestDto taxInPaymentRequestDto);

  TaxInPaymentResponseDto getPaymentById(Long id);
}

package br.com.rafaellino.taxapi.app.port.out;

import br.com.rafaellino.taxapi.app.port.out.contract.TaxPaymentRequestDto;

public interface TaxPaymentDispatch {
  void dispatchPayment(TaxPaymentRequestDto taxPaymentRequestDto);
}

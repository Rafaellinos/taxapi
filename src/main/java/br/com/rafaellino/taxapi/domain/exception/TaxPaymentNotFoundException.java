package br.com.rafaellino.taxapi.domain.exception;

public class TaxPaymentNotFoundException extends RuntimeException{
  public TaxPaymentNotFoundException() {
    super();
  }

  public TaxPaymentNotFoundException(String message) {
    super(message);
  }
}

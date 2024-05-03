package br.com.rafaellino.taxapi.domain.exception;

public class InvalidTaxForPaymentException extends RuntimeException{
  public InvalidTaxForPaymentException() {
    super();
  }

  public InvalidTaxForPaymentException(String message) {
    super(message);
  }
}

package br.com.rafaellino.taxapi.domain.exception;

public class DocumentInvalidException extends RuntimeException {

  public DocumentInvalidException() {
    super();
  }

  public DocumentInvalidException(String message) {
    super(message);
  }
}

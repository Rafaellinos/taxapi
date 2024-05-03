package br.com.rafaellino.taxapi.domain.exception;

public class DueDateExpiredException extends RuntimeException{
  public DueDateExpiredException() {
  }

  public DueDateExpiredException(String message) {
    super(message);
  }
}

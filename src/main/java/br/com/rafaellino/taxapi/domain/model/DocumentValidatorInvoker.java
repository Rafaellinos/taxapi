package br.com.rafaellino.taxapi.domain.model;

import br.com.rafaellino.taxapi.domain.exception.DocumentInvalidException;

public class DocumentValidatorInvoker {
  public static void validate(final Document value) {
    boolean valid = false;
    if (value.length() == 11) {
      valid = new DocumentCpfValidator().doValidate(value);
    } else if (value.length() == 14) {
      valid = new DocumentCnpjValidator().doValidate(value);
    }
    if (!valid) {
      throw new DocumentInvalidException("Invalid document " + value);
    }
  }
}

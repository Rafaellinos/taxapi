package br.com.rafaellino.taxapi.domain.model;

public class Document {

  private final String document;

  public Document(String document) {
    this.document = document;
    DocumentValidatorInvoker.validate(this);
  }

  public String getDocument() {
    return document;
  }

  @Override
  public String toString() {
    return this.document;
  }

  public int length() {
    return this.document.length();
  }
}

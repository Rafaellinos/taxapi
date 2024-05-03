package br.com.rafaellino.taxapi.domain.model;

import br.com.rafaellino.taxapi.domain.exception.DueDateExpiredException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TaxPayment {
  private TaxPaymentStatusEnum status;
  private Document document;
  private TaxType taxType;
  private Long id;
  private BigDecimal amount;
  private LocalDate dueDate;

  public TaxPayment(TaxPaymentStatusEnum status, Document document, TaxType taxType, Long id, BigDecimal amount) {
    this.status = status;
    this.document = document;
    this.taxType = taxType;
    this.id = id;
    this.amount = amount;
  }

  @Default
  public TaxPayment(TaxPaymentStatusEnum status, Document document, TaxType taxType, Long id, BigDecimal amount, LocalDate dueDate) {
    this.status = status;
    this.document = document;
    this.taxType = taxType;
    this.id = id;
    this.amount = amount;
    this.setDueDate(dueDate);
  }

  public TaxPayment(TaxPaymentStatusEnum status, Document document, TaxType taxType, BigDecimal amount, LocalDate dueDate) {
    this.taxType = taxType;
    this.document = document;
    this.status = status;
    setDueDate(dueDate);
    this.amount = amount;
  }

  public TaxPaymentStatusEnum getStatus() {
    return status;
  }

  public void setStatus(TaxPaymentStatusEnum status) {
    this.status = status;
  }

  public Document getDocument() {
    return document;
  }

  public void setDocument(Document document) {
    this.document = document;
  }

  public TaxType getTaxType() {
    return taxType;
  }

  public void setTaxType(TaxType taxType) {
    this.taxType = taxType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    var now = LocalDate.now();
    if (now.isAfter(dueDate)) {
      throw new DueDateExpiredException("Due date from " + dueDate.toString() + " past date from now " + now.toString());
    }
    this.dueDate = dueDate;
  }
}

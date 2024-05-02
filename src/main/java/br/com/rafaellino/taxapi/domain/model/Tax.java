package br.com.rafaellino.taxapi.domain.model;

import br.com.rafaellino.taxapi.domain.exception.TaxTypeNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Tax {

  private TaxType taxType;
  private BigDecimal amount;
  private LocalDate dueDate;

  public Tax(TaxType taxType, BigDecimal amount, LocalDate dueDate) {
    this.taxType = taxType;
    this.amount = amount;
    this.dueDate = dueDate;
  }
  public Tax(String taxType, BigDecimal amount, LocalDate dueDate) {
    TaxType taxTypeEnum;
    try {
      taxTypeEnum = TaxType.valueOf(taxType);
    } catch (IllegalArgumentException e) {
      throw new TaxTypeNotFoundException(e.getMessage());
    }
    this.taxType = taxTypeEnum;
    this.amount = amount;
    this.dueDate = dueDate;
  }

  public TaxType getTaxType() {
    return taxType;
  }

  public void setTaxType(TaxType taxType) {
    this.taxType = taxType;
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
    this.dueDate = dueDate;
  }
}

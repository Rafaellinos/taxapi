package br.com.rafaellino.taxapi.app.port.in.contract;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TaxInPaymentResponseDto(
    String status,
    String document,
    String taxType,
    Long id,
    BigDecimal amount,
    LocalDate dueDate
) {
}

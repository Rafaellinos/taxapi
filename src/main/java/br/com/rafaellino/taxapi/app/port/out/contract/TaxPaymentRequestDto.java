package br.com.rafaellino.taxapi.app.port.out.contract;

import java.math.BigDecimal;

public record TaxPaymentRequestDto(
   Long id,
   String document,
   BigDecimal amount,
   String taxType
) {
}

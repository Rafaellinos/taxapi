package br.com.rafaellino.taxapi.app.port.in.contract;


import java.math.BigDecimal;
import java.time.LocalDate;

public record TaxInDto(
    BigDecimal valor,
    String tipo,
    LocalDate dueDate
) {

}

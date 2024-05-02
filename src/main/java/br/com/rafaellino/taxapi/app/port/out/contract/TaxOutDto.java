package br.com.rafaellino.taxapi.app.port.out.contract;


import java.math.BigDecimal;
import java.time.LocalDate;

public record TaxOutDto(
    BigDecimal valor,
    String tipo,
    LocalDate dueDate
) {

}

package br.com.rafaellino.taxapi.app.port.out.contract;


import java.util.List;

public record TaxOutGetContract(
   List<TaxOutDto> tributo
) {
}

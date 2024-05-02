package br.com.rafaellino.taxapi.app.port.in.contract;


import java.util.List;

public record TaxInGetContract(
   List<TaxInDto> tributo
) {
}

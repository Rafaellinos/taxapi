package br.com.rafaellino.taxapi.app.port.in.contract;

public record TaxInPaymentRequestDto(
    String document,
    String taxType
) {
}

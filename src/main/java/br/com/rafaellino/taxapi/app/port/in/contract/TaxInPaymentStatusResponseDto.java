package br.com.rafaellino.taxapi.app.port.in.contract;

public record TaxInPaymentStatusResponseDto(
    Long id,
    String cause
) {
}

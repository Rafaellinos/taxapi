package br.com.rafaellino.taxapi.app.port.mapper;

import br.com.rafaellino.taxapi.app.port.in.contract.TaxInGetContract;
import br.com.rafaellino.taxapi.app.port.in.contract.TaxInPaymentResponseDto;
import br.com.rafaellino.taxapi.app.port.out.contract.TaxOutGetContract;
import br.com.rafaellino.taxapi.app.port.out.contract.TaxPaymentRequestDto;
import br.com.rafaellino.taxapi.domain.model.Document;
import br.com.rafaellino.taxapi.domain.model.TaxPayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface TaxMapper {

  TaxInGetContract toTaxInGetContract(TaxOutGetContract taxOutGetContract);

  @Named("toDocumentString")
  default String toDocumentString(Document document) {
    return document.toString();
  }

  @Mapping(source = "document", target = "document", qualifiedByName = "toDocumentString")
  TaxPaymentRequestDto toTaxPaymentRequestDto(TaxPayment taxPayment);

  @Mapping(source = "document", target = "document", qualifiedByName = "toDocumentString")
  TaxInPaymentResponseDto toInPaymentResponseDto(TaxPayment taxPayment);
}

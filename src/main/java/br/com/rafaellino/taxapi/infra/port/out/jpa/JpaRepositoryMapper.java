package br.com.rafaellino.taxapi.infra.port.out.jpa;

import br.com.rafaellino.taxapi.domain.model.Document;
import br.com.rafaellino.taxapi.domain.model.TaxPayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface JpaRepositoryMapper {

  @Named("toDocumentString")
  default String toDocumentString(Document document) {
    return document.toString();
  }

  @Named("toStringDocument")
  default Document toStringDocument(String document) {
    return new Document(document);
  }

  @Mapping(source = "document", target = "document", qualifiedByName = "toDocumentString")
  @Mapping(source = "status", target = "status")
  @Mapping(source = "taxType", target = "taxType")
  @Mapping(source = "dueDate", target = "dueDate")
  @Mapping(source = "amount", target = "amount")
  TaxPaymentEntity toEntity(TaxPayment taxPayment);

  @Mapping(source = "document", target = "document", qualifiedByName = "toStringDocument")
  TaxPayment toDomain(TaxPaymentEntity entity);
}

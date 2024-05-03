package br.com.rafaellino.taxapi.infra.port.out.jpa.repository;

import br.com.rafaellino.taxapi.domain.exception.TaxPaymentNotFoundException;
import br.com.rafaellino.taxapi.domain.model.TaxPayment;
import br.com.rafaellino.taxapi.app.repository.TaxPaymentRepository;
import br.com.rafaellino.taxapi.infra.port.out.jpa.JpaRepositoryMapper;
import br.com.rafaellino.taxapi.infra.port.out.jpa.TaxPaymentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TaxPaymentRepositoryImpl implements TaxPaymentRepository {

  private final TaxPaymentJpaRepository jpaRepository;
  private final JpaRepositoryMapper mapper;

  @Autowired
  public TaxPaymentRepositoryImpl(TaxPaymentJpaRepository jpaRepository, JpaRepositoryMapper mapper) {
    this.jpaRepository = jpaRepository;
    this.mapper = mapper;
  }

  @Override
  public TaxPayment save(final TaxPayment taxPayment) {
    TaxPaymentEntity taxPaymentEntity = mapper.toEntity(taxPayment);
    taxPaymentEntity = jpaRepository.save(taxPaymentEntity);
    return mapper.toDomain(taxPaymentEntity);
  }

  @Override
  public TaxPayment findById(final Long id) {
    return mapper.toDomain(jpaRepository.findById(id).orElseThrow(TaxPaymentNotFoundException::new));
  }
}

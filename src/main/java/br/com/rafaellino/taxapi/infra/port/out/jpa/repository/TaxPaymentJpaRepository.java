package br.com.rafaellino.taxapi.infra.port.out.jpa.repository;


import br.com.rafaellino.taxapi.infra.port.out.jpa.TaxPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxPaymentJpaRepository extends JpaRepository<TaxPaymentEntity, Long> {
}

package br.com.rafaellino.taxapi.app.repository;

import br.com.rafaellino.taxapi.domain.model.TaxPayment;

public interface TaxPaymentRepository {

  TaxPayment save(TaxPayment taxPayment);

  TaxPayment findById(Long id);

}

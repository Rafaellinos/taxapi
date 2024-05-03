package br.com.rafaellino.taxapi.infra.config;

import br.com.rafaellino.taxapi.app.port.in.TaxUseCase;
import br.com.rafaellino.taxapi.app.port.mapper.TaxMapper;
import br.com.rafaellino.taxapi.app.port.out.ProdespIntegration;
import br.com.rafaellino.taxapi.app.port.out.TaxPaymentDispatch;
import br.com.rafaellino.taxapi.app.repository.TaxPaymentRepository;
import br.com.rafaellino.taxapi.app.service.TaxService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

  @Bean
  @Autowired
  public TaxUseCase createTaxUseCase(
      ProdespIntegration prodespIntegration, TaxPaymentRepository taxPaymentRepository, TaxPaymentDispatch taxPaymentDispatch) {
    return new TaxService(
        prodespIntegration, Mappers.getMapper(TaxMapper.class),
        taxPaymentRepository, taxPaymentDispatch
    );
  }

}

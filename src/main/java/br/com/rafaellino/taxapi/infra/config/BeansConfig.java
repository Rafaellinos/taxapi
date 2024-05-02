package br.com.rafaellino.taxapi.infra.config;

import br.com.rafaellino.taxapi.app.port.in.TaxUseCase;
import br.com.rafaellino.taxapi.app.port.mapper.TaxMapper;
import br.com.rafaellino.taxapi.app.port.out.ProdespIntegration;
import br.com.rafaellino.taxapi.domain.usecase.TaxUseCaseImpl;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

  @Bean
  public TaxUseCase createTaxUseCase(@Autowired ProdespIntegration prodespIntegration) {
    return new TaxUseCaseImpl(prodespIntegration, Mappers.getMapper(TaxMapper.class));
  }

}

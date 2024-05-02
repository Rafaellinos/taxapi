package br.com.rafaellino.taxapi.domain.usecase;

import br.com.rafaellino.taxapi.app.port.in.TaxUseCase;
import br.com.rafaellino.taxapi.app.port.in.contract.TaxInGetContract;
import br.com.rafaellino.taxapi.app.port.mapper.TaxMapper;
import br.com.rafaellino.taxapi.app.port.out.ProdespIntegration;
import br.com.rafaellino.taxapi.domain.model.Document;

import java.util.List;

public class TaxUseCaseImpl implements TaxUseCase {

  private final ProdespIntegration prodespIntegration;
  private final TaxMapper taxMapper;

  public TaxUseCaseImpl(ProdespIntegration prodespIntegration, TaxMapper taxMapper) {
    this.prodespIntegration = prodespIntegration;
    this.taxMapper = taxMapper;
  }

  @Override
  public TaxInGetContract getTaxByTypeAndDocument(List<String> types, String document) {
    new Document(document);
    return taxMapper.toTaxInGetContract(prodespIntegration.getProdespTaxByType(types));
  }
}

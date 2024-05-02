package br.com.rafaellino.taxapi.app.port.out;

import br.com.rafaellino.taxapi.app.port.out.contract.TaxOutGetContract;

import java.util.List;

public interface ProdespIntegration {
  TaxOutGetContract getProdespTaxByType(List<String> types);
}

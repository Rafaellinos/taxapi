package br.com.rafaellino.taxapi.app.port.in;


import br.com.rafaellino.taxapi.app.port.in.contract.TaxInGetContract;

import java.util.List;

public interface TaxUseCase {
  TaxInGetContract getTaxByTypeAndDocument(List<String> types, String document);
}

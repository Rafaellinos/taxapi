package br.com.rafaellino.taxapi.app.port.mapper;

import br.com.rafaellino.taxapi.app.port.in.contract.TaxInGetContract;
import br.com.rafaellino.taxapi.app.port.out.contract.TaxOutGetContract;
import org.mapstruct.Mapper;

@Mapper
public interface TaxMapper {

  TaxInGetContract toTaxInGetContract(TaxOutGetContract taxOutGetContract);

}

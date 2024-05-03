package br.com.rafaellino.taxapi.infra.port.in;

import br.com.rafaellino.taxapi.app.port.in.TaxUseCase;
import br.com.rafaellino.taxapi.app.port.in.contract.TaxInGetContract;
import br.com.rafaellino.taxapi.domain.exception.DocumentInvalidException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tax")
public class TaxController {

  private static final Logger log = LogManager.getLogger(TaxController.class);
  private final TaxUseCase taxUseCase;

  @Autowired
  public TaxController(TaxUseCase taxUseCase) {
    this.taxUseCase = taxUseCase;
  }

  @GetMapping
  public ResponseEntity<TaxInGetContract> getTaxByTypes(
      @RequestParam(required = true) final List<String> types,
      @RequestParam(required = true) final String document
  ) {
    try {
      return new ResponseEntity<>(taxUseCase.getTaxByTypeAndDocument(types, document), HttpStatus.OK);
    } catch (DocumentInvalidException e) {
      log.error("DocumentInvalidException for {}", document, e);
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

}

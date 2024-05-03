package br.com.rafaellino.taxapi.infra.port.in;

import br.com.rafaellino.taxapi.app.port.in.TaxUseCase;
import br.com.rafaellino.taxapi.app.port.in.contract.TaxInPaymentRequestDto;
import br.com.rafaellino.taxapi.app.port.in.contract.TaxInPaymentResponseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tax-payment")
public class PaymentController {

  private static final Logger log = LogManager.getLogger(TaxController.class);
  private final TaxUseCase taxUseCase;

  @Autowired
  public PaymentController(TaxUseCase taxUseCase) {
    this.taxUseCase = taxUseCase;
  }

  @PostMapping
  public ResponseEntity<String> postPaymentRequest(
      @RequestBody TaxInPaymentRequestDto taxInPaymentRequestDto) {
    try {
      var id = taxUseCase.payTax(taxInPaymentRequestDto);
      return ResponseEntity.status(HttpStatus.CREATED).header(
          "Resource-ID", id.toString()
      ).build();
    } catch (RuntimeException e) {
      log.error(e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping
  public ResponseEntity<TaxInPaymentResponseDto> getPaymentRequest(@RequestParam Long id) {
    try {
      var res = taxUseCase.getPaymentById(id);
      return ResponseEntity.status(HttpStatus.OK).body(res);
    } catch (RuntimeException e) {
      log.error(e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }
}

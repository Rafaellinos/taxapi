package br.com.rafaellino.taxapi.infra.port.out;

import br.com.rafaellino.taxapi.app.port.out.ProdespIntegration;
import br.com.rafaellino.taxapi.app.port.out.contract.TaxOutGetContract;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class ProdespIntegrationImpl implements ProdespIntegration {

  @Autowired
  public ProdespIntegrationImpl(WebClient webClient) {
    this.webClient = webClient;
  }

  private final WebClient webClient;

  @Value("${api.acl.prodesp-acl.path}")
  private String prodespAclPath;


  private Mono<TaxOutGetContract> getResponse(List<String> tributos) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path(prodespAclPath + "/tributo")
            .queryParam("tipo", tributos)
            .build()
        )
        .accept(MediaType.APPLICATION_JSON)
        .exchangeToMono(res -> {
          if (res.statusCode().is2xxSuccessful()) {
            return res.bodyToMono(TaxOutGetContract.class);
          }
          return res.createError();
        });
  }

  @Override
  public TaxOutGetContract getProdespTaxByType(List<String> types) {
    TaxOutGetContract res = this.getResponse(types).block();
    Objects.requireNonNull(res);
    return res;
  }
}

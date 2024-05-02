package br.com.rafaellino.taxapi.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class DocumentValidator {

  abstract boolean doValidate(final Document document);

  protected String listToString(final List<Integer> list) {
    return list.stream().map(Object::toString).reduce("", String::concat);
  }

  protected int mod11(final List<Integer> digits, final int... multipliers) {
    final var i = new AtomicInteger(0);
    final var rest = digits.stream().reduce(0, (p, e) -> p + e * multipliers[i.getAndIncrement()]) % 11;
    return rest > 9 ? 0 : rest;
  }

  protected String extractNumbers(final String s) {
    return Objects.nonNull(s) ? s.replaceAll("\\D+", "") : "";
  }

  protected List<Integer> extractNumbersToList(final String value) {
    final var digits = new ArrayList<Integer>();
    for (char item : extractNumbers(value).toCharArray()) {
      digits.add(Integer.parseInt(String.valueOf(item)));
    }
    return digits;
  }

}

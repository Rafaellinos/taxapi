package br.com.rafaellino.taxapi.domain.model;

import java.util.List;

public class DocumentCnpjValidator extends DocumentValidator {

  @Override
  public boolean doValidate(Document document) {
    final String docStr = extractNumbers(document.toString()).replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    return isCnpjValid(docStr);
  }

  private boolean isCnpjValid(final String cnpj) {
    final List<Integer> digits = extractNumbersToList(cnpj);
    if (digits.size() == 14 && digits.stream().distinct().count() > 1) {
      return getCnpjValid(digits.subList(0, 12)).equals(extractNumbers(cnpj));
    }
    return false;
  }

  private String getCnpjValid(final List<Integer> digits) {
    digits.add(mod11(digits, 6, 7, 8, 9, 2, 3, 4, 5, 6, 7, 8, 9));
    digits.add(mod11(digits, 5, 6, 7, 8, 9, 2, 3, 4, 5, 6, 7, 8, 9));
    return this.listToString(digits);
  }
}

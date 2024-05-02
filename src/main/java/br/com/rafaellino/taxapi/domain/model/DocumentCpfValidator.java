package br.com.rafaellino.taxapi.domain.model;

import java.util.List;

public class DocumentCpfValidator extends DocumentValidator {

  @Override
  public boolean doValidate(Document document) {
    final String docStr = extractNumbers(document.toString()).replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    return isCpfValid(docStr);
  }

  private boolean isCpfValid(final String cpf) {
    final List<Integer> digits = extractNumbersToList(cpf);
    if (digits.size() == 11 && digits.stream().distinct().count() > 1) {
      return getCpfValid(digits.subList(0, 9)).equals(extractNumbers(cpf));
    }
    return false;
  }

  private String getCpfValid(final List<Integer> digits) {
    digits.add(mod11(digits, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    digits.add(mod11(digits, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    return this.listToString(digits);
  }


}

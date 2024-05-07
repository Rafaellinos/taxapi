package br.com.rafaellino.taxapi.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class DocumentCnpjValidatorTest {

  @InjectMocks
  DocumentCpfValidator validator;

  @Mock
  Document document;

  @Test
  void doValidateCpf_Success() {
    // arrange
    doReturn("26477081008").when(document).toString();

    // act
    boolean res = validator.doValidate(document);

    // assert
    Assertions.assertTrue(res);
  }

  @Test
  void doValidateCpf_Fail() {
    // arrange
    doReturn("26477081001213").when(document).toString();

    // act
    boolean res = validator.doValidate(document);

    // assert
    Assertions.assertFalse(res);
  }
}
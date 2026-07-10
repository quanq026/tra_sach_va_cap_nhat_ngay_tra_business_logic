package com.rikkei.course141.ss1;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistingBookIdValidator implements ConstraintValidator<ExistingBookId, Long> {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public boolean isValid(Long bookId, ConstraintValidatorContext context) {
        if (bookId == null) return false;
        return bookRepository.existsById(bookId);
    }
}

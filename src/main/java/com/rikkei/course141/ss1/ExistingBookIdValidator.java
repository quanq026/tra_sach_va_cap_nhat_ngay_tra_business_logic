package com.rikkei.course141.ss1;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class ExistingBookIdValidator implements ConstraintValidator<ExistingBookId, Long> {
    private final BookRepository bookRepository;

    @Override
    public boolean isValid(Long bookId, ConstraintValidatorContext context) {
        if (bookId == null) return false;
        return bookRepository.existsById(bookId);
    }
}

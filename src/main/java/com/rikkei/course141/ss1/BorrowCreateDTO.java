package com.rikkei.course141.ss1;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BorrowCreateDTO {
    @NotNull
    @ExistingBookId
    private Long bookId;
    @NotNull
    private Long readerId;
}

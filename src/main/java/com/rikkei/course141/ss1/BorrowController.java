package com.rikkei.course141.ss1;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/borrows")
@RequiredArgsConstructor
public class BorrowController {
    private final BorrowService borrowService;

    @PostMapping
    public ResponseEntity<BorrowRecord> borrowBook(@Valid @RequestBody BorrowCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowService.borrowBook(dto));
    }

    @PatchMapping("/return/{id}")
    public ResponseEntity<BorrowRecord> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(borrowService.returnBook(id));
    }
}

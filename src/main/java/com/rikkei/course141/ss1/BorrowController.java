package com.rikkei.course141.ss1;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @PostMapping
    public ResponseEntity<BorrowRecord> borrowBook(@Valid @RequestBody BorrowCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowService.borrowBook(dto));
    }

    @PatchMapping("/return/{id}")
    public ResponseEntity<BorrowRecord> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(borrowService.returnBook(id));
    }
}

package com.rikkei.course141.ss1;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@ModelAttribute BookCreateDTO dto) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(dto));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody BookUpdateStockDTO dto) {
        return ResponseEntity.ok(bookService.updateBook(id, dto));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String keyword) {
        return ResponseEntity.ok(bookService.searchBooks(keyword));
    }
}

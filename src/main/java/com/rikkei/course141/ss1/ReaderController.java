package com.rikkei.course141.ss1;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @PostMapping
    public ResponseEntity<Reader> createReader(@Valid @ModelAttribute ReaderCreateDTO dto) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(readerService.createReader(dto));
    }
}

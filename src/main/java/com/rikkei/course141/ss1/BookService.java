package com.rikkei.course141.ss1;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    private final Path uploadDir = Paths.get("uploads");

    public Book createBook(BookCreateDTO dto) throws IOException {
        Files.createDirectories(uploadDir);
        MultipartFile file = dto.getCoverImage();
        String coverUrl = null;
        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), uploadDir.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            coverUrl = "/uploads/" + fileName;
        }
        Book book = Book.builder()
            .title(dto.getTitle())
            .author(dto.getAuthor())
            .stock(dto.getStock())
            .coverUrl(coverUrl)
            .build();
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, BookUpdateStockDTO dto) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách id=" + id));
        book.setStock(dto.getStock());
        return bookRepository.save(book);
    }

    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCase(keyword);
    }
}

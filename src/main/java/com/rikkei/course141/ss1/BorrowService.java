package com.rikkei.course141.ss1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class BorrowService {
    @Autowired
    private BorrowRecordRepository borrowRepository;
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public BorrowRecord borrowBook(BorrowCreateDTO dto) {
        Book book = bookRepository.findById(dto.getBookId())
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách"));
        if (book.getStock() <= 0) {
            throw new IllegalStateException("Sách đã hết");
        }
        book.setStock(book.getStock() - 1);
        bookRepository.save(book);
        BorrowRecord record = BorrowRecord.builder()
            .bookId(dto.getBookId())
            .readerId(dto.getReaderId())
            .borrowDate(LocalDate.now())
            .build();
        return borrowRepository.save(record);
    }

    @Transactional
    public BorrowRecord returnBook(Long id) {
        BorrowRecord record = borrowRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phiếu mượn"));
        record.setReturnDate(LocalDate.now());
        Book book = bookRepository.findById(record.getBookId())
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách"));
        book.setStock(book.getStock() + 1);
        bookRepository.save(book);
        return borrowRepository.save(record);
    }
}

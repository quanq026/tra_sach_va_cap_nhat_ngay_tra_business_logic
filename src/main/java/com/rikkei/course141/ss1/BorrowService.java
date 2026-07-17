package com.rikkei.course141.ss1;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class BorrowService {
    private final BorrowRecordRepository borrowRepository;
    private final BookRepository bookRepository;

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
            .status(BorrowRecord.BorrowStatus.BORROWING)
            .build();
        return borrowRepository.save(record);
    }

    @Transactional
    public BorrowRecord returnBook(Long id) {
        BorrowRecord record = borrowRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phiếu mượn"));
        if (record.getStatus() == BorrowRecord.BorrowStatus.RETURNED) {
            throw new BookAlreadyReturnedException("Sách đã trả rồi");
        }
        record.setReturnDate(LocalDate.now());
        record.setStatus(BorrowRecord.BorrowStatus.RETURNED);
        Book book = bookRepository.findById(record.getBookId())
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách"));
        book.setStock(book.getStock() + 1);
        bookRepository.save(book);
        return borrowRepository.save(record);
    }
}

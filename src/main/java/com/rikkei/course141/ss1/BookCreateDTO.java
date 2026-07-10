package com.rikkei.course141.ss1;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BookCreateDTO {
    private String title;
    private String author;
    private Integer stock;
    private MultipartFile coverImage;
}

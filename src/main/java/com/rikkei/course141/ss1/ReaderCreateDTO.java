package com.rikkei.course141.ss1;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReaderCreateDTO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String fullName;
    @Pattern(regexp = "^(0)(3|5|7|8|9)[0-9]{8}$", message = "Số điện thoại không hợp lệ")
    private String phoneNumber;
    @NotBlank
    private String address;
    private MultipartFile avatarFile;
}

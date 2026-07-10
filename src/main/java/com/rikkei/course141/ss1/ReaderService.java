package com.rikkei.course141.ss1;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ReaderService {
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private Cloudinary cloudinary;

    public Reader createReader(ReaderCreateDTO dto) throws IOException {
        if (readerRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email đã tồn tại");
        }
        MultipartFile file = dto.getAvatarFile();
        if (file == null || file.isEmpty()) {
            throw new InvalidFileException("Vui lòng upload ảnh đại diện");
        }
        String original = file.getOriginalFilename();
        if (original == null || !(original.toLowerCase().endsWith(".png") || original.toLowerCase().endsWith(".jpg") || original.toLowerCase().endsWith(".jpeg"))) {
            throw new InvalidFileException("Chỉ chấp nhận file ảnh png, jpg, jpeg");
        }
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String url = uploadResult.get("url").toString();
        Reader reader = Reader.builder()
            .email(dto.getEmail())
            .fullName(dto.getFullName())
            .phoneNumber(dto.getPhoneNumber())
            .address(dto.getAddress())
            .avatar(url)
            .build();
        return readerRepository.save(reader);
    }
}

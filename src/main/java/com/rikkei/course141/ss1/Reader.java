package com.rikkei.course141.ss1;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "readers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String avatar;
}

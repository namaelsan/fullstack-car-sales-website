package com.staj.CarCommerceApp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "T_Image")
public class Image {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name = "filename", nullable=false)
    private String filename;

    @Column(name = "full_path", nullable=false, unique=true)
    private String fullPath;
}

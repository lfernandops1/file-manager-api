package com.example.filemanager.domain;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private Long size;

    @ManyToOne
    @JoinColumn(name = "directory_id")
    private Directory directory;

}

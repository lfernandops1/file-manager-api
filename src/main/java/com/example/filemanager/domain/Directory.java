package com.example.filemanager.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Directory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_directory_id")
    private Directory parentDirectory;

    @OneToMany(mappedBy = "parentDirectory", cascade = CascadeType.ALL)
    private List<Directory> subDirectories;

    @OneToMany(mappedBy = "directory", cascade = CascadeType.ALL)
    private List<File> files;

}

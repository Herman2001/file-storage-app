package com.herman.fileStorage.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "folders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Folder {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private User owner;

    @OneToMany(mappedBy = "folder")
    private List<FileEntity> files;

    public Folder(User owner, String name) {
        this.owner = owner;
        this.name = name;
    }

}

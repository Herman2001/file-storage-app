package com.herman.fileStorage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private User owner;

    @OneToMany(mappedBy = "folder")
    @JsonIgnore
    private List<FileEntity> files;

    public Folder(User owner, String name) {
        this.owner = owner;
        this.name = name;
    }

}

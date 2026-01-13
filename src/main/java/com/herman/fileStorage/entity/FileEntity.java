package com.herman.fileStorage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String filename;

    @Lob
    @Column(nullable = false)
    private byte[] content;

    @ManyToOne(optional = false)
    private Folder folder;

    @ManyToOne(optional = false)
    private User owner;

    public FileEntity(String filename, byte[] content, Folder folder, User owner) {
        this.filename = filename;
        this.content = content;
        this.folder = folder;
        this.owner = owner;
    }
}

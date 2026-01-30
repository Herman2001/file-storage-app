package com.herman.fileStorage.repository;

import com.herman.fileStorage.entity.Folder;
import com.herman.fileStorage.entity.User;
import com.herman.fileStorage.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for files
 * Provides CRUD operations and custom query methods
 */
public interface FileRepository  extends JpaRepository<FileEntity, Long> {

    /**
     * Fins all files in a specific folder
     * @param folder the folder to find files in
     * @return list of files in the folder
     */
    List<FileEntity> findAllByFolder(Folder folder);

    /**
     * Finds all files owned by a specific user
     *
     * @param owner the user that owns the file
     * @return list of files owned by the user
     */
    List<FileEntity> findAllByOwner(User owner);
    Optional<FileEntity> findByIdAndOwnerId(Long id, UUID ownerId);
}

package com.herman.fileStorage.repository;

import com.herman.fileStorage.entity.Folder;
import com.herman.fileStorage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface ffor Folder entity
 * Provides CRUD operations and query methods
 */

public interface FolderRepository extends JpaRepository<Folder, Long>{

    /**
     * Finds all folders owned by a specific user.
     *
     * @param owner the user who owns the folders
     * @return list of folders owned by the user
     */
    List<Folder> findAllByOwner(User owner);
}

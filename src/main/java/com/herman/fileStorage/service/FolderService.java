package com.herman.fileStorage.service;

import com.herman.fileStorage.entity.Folder;
import com.herman.fileStorage.entity.User;
import com.herman.fileStorage.repository.FolderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * Service class for managing Folder entities.
 */


@Service
public class FolderService {

    private final FolderRepository folderRepository;

    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    /**
     * Creates a new folder for a given user
     *
     * @param name the name of the folder
     * @param owner the user that owns the folder
     * @return the created FOlder enetity
     */
    public Folder createFolder(String name, User owner) {
        Folder folder = new Folder();
        folder.setName(name);
        folder.setOwner(owner);
        return folderRepository.save(folder);
    }

    /**
     * Retrives a folder by its id
     *
     * @param id the id of the folder
     * @return an Optional containing the Folder if found, else empty
     */
    public Optional<Folder> getFolderById(Long id) {
        return folderRepository.findById(id);
    }

    /**
     * Retrives all folders owned by a specific user
     *
     * @param owner the user that owns folders
     * @return list of folders
     */
    public List<Folder> findAllByOwner(User owner) {
        return folderRepository.findAllByOwner(owner);
    }

    /**
     * Deltes a folder by its id
     *
     * @param id the id of the folder to delete
     */
    public void deleteFolder(long id) {
        folderRepository.deleteById(id);
    }
}

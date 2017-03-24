package org.data.enums;

import java.io.File;

/**
 * Created by Sphiinx on 3/24/17.
 */
public enum DirectoryFolder {

    SPXBot("SPXBot", null),
    DATA("data", SPXBot, DirectoryFile.GAMEPACK);

    private final String FOLDER_NAME;
    private final DirectoryFolder PARENT_DIRECTORY;
    private final DirectoryFile[] DIRECTORY_FILES;

    DirectoryFolder(String folder_name, DirectoryFolder parent_directory, DirectoryFile... directory_files) {
        this.FOLDER_NAME = folder_name;
        this.PARENT_DIRECTORY = parent_directory;
        this.DIRECTORY_FILES = directory_files;
    }

    public String getFolderName() {
        return this.FOLDER_NAME;
    }

    public DirectoryFolder getParentDirectory() {
        return this.PARENT_DIRECTORY;
    }

    public DirectoryFile[] getDirectoryFiles() {
        return DIRECTORY_FILES;
    }

}

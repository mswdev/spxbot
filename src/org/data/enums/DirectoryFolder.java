package org.data.enums;

import java.io.File;

/**
 * Created by Sphiinx on 3/24/17.
 */
public enum DirectoryFolder {

    SPXBot("SPXBot", System.getProperty("user.home"), null),
    DATA("data", SPXBot.getDirectoryPath() + File.separator + SPXBot.getFolderName(), DirectoryFile.GAMEPACK),
    SETTINGS("settings", SPXBot.getDirectoryPath() + File.separator + SPXBot.getFolderName(), DirectoryFile.CLIENT_BUILD);

    private final String FOLDER_NAME;
    private final String PATH;
    private final DirectoryFile[] DIRECTORY_FILES;

    DirectoryFolder(String folder_name, String path, DirectoryFile... directory_files) {
        this.FOLDER_NAME = folder_name;
        this.PATH = path;
        this.DIRECTORY_FILES = directory_files;
    }

    public String getFolderName() {
        return FOLDER_NAME;
    }

    public String getDirectoryPath() {
        return PATH;
    }

    public DirectoryFile[] getDirectoryFiles() {
        return DIRECTORY_FILES;
    }

}

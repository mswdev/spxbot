package org.data.enums;

/**
 * Created by Sphiinx on 3/24/17.
 */
public enum DirectoryFile {

    GAMEPACK("gamepack", ".jar");

    private final String FILE_NAME;
    private final String FILE_EXTENSION;

    DirectoryFile(String folder_name, String file_extension) {
        this.FILE_NAME = folder_name;
        this.FILE_EXTENSION = file_extension;
    }

    public String getFileName() {
        return FILE_NAME;
    }

    public String getFileExtension() {
        return FILE_EXTENSION;
    }

}


package org.data.enums;

/**
 * Created by Sphiinx on 3/24/17.
 */
public enum DirectoryFile {

    GAMEPACK("gamepack", ".jar", false),
    CLIENT_BUILD("client_build", ".dat", true, "local_revision");

    private final String FILE_NAME;
    private final String FILE_EXTENSION;
    private final boolean SHOULD_CREATE_FILE;
    private final String[] PROPERTIES;

    DirectoryFile(String folder_name, String file_extension, boolean create_file, String... properties) {
        this.FILE_NAME = folder_name;
        this.FILE_EXTENSION = file_extension;
        this.SHOULD_CREATE_FILE = create_file;
        this.PROPERTIES = properties;
    }

    public String getFileName() {
        return FILE_NAME;
    }

    public String getFileExtension() {
        return FILE_EXTENSION;
    }

    public boolean shouldCreateFile() {
        return SHOULD_CREATE_FILE;
    }

    public String[] getProperties() {
        return PROPERTIES;
    }

}


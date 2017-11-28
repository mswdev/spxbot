package main.data.enums;

/**
 * Created by Sphiinx on 3/24/17.
 */
public enum DirectoryFile {

    GAMEPACK("gamepack", ".jar", null, false),
    CLIENT_BUILD("client_build", ".dat", null, true, "local_revision"),
    CLIENT_INITIALIZATION_LOGO("client_initialization_logo", ".png", "http://138.68.45.96/client_assets/client_initialization_logo.png", false),
    LOGIN_SCREEN_LOGO("client_login_screen_logo", ".png", "http://138.68.45.96/client_assets/client_login_screen_logo.png", false),
    SPLASH_SCREEN_LOGO("client_splash_screen_logo", ".png", "http://138.68.45.96/client_assets/client_splash_screen_logo.png", false);

    private final String FILE_NAME;
    private final String FILE_EXTENSION;
    private final String FILE_URL;
    private final boolean SHOULD_CREATE_FILE;
    private final String[] PROPERTIES;

    DirectoryFile(String folder_name, String file_extension, String file_url, boolean create_file, String... properties) {
        this.FILE_NAME = folder_name;
        this.FILE_EXTENSION = file_extension;
        this.FILE_URL = file_url;
        this.SHOULD_CREATE_FILE = create_file;
        this.PROPERTIES = properties;
    }

    public String getFileName() {
        return FILE_NAME;
    }

    public String getFileExtension() {
        return FILE_EXTENSION;
    }

    public String getFileURL() {
        return FILE_URL;
    }

    public boolean shouldCreateFile() {
        return SHOULD_CREATE_FILE;
    }

    public String[] getProperties() {
        return PROPERTIES;
    }

}


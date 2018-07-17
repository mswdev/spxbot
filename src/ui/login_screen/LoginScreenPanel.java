package ui.login_screen;

import api.security.Authentication;
import api.util.Images;
import api.web.Request;
import client.design.enums.SPXColor;
import client.design.enums.SPXFont;
import data.Vars;
import data.enums.DirectoryFile;
import data.enums.DirectoryFolder;
import database.DBManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Sphiinx on 6/14/2017.
 */
public class LoginScreenPanel extends JPanel implements KeyListener {

    /**
     * The ImageIcon containing the logo.
     */
    private final ImageIcon IMAGE_ICON = new ImageIcon();

    /**
     * The JLabel containing the logo.
     */
    private final JLabel LOGO_LABEL = new JLabel();

    /**
     * The JTextField for the username field.
     */
    private final JTextField USERNAME_FIELD = new JTextField();

    /**
     * The JPasswordField for the password field.
     */
    private final JPasswordField PASSWORD_FIELD = new JPasswordField();

    /**
     * The JLabel for the forgotten password link.
     */
    private final JLabel FORGOT_PASSWORD_LINK = new JLabel();

    /**
     * The JButton for the login button.
     */
    private final JButton LOGIN_BUTTON = new JButton();

    /**
     * The JLabel for the status text.
     */
    private final JLabel STATUS_TEXT = new JLabel();

    /**
     * The JLabel for the client version.
     */
    private final JLabel CLIENT_VERSION = new JLabel();

    /**
     * The JLabel for the incorrect login information.
     */
    private final JLabel INCORRECT_LOGIN_INFORMATION = new JLabel();

    /**
     * The JLabel for the weight alignment.
     */
    private final JLabel WEIGHT_ALIGNMENT = new JLabel();

    /**
     * The boolean determining if we've logged in successfully.
     */
    private boolean has_logged_in;

    /**
     * Initializes the login screen panel.
     */
    public void initializeLoginScreenPanel() {
        setLayout(new GridBagLayout());
        setBackground(SPXColor.SPX_GRAY.getColor());

        final GridBagConstraints CONSTRAINTS = new GridBagConstraints();

        final Image SPLASH_SCREEN_LOGO = Images.getImageFromPath(DirectoryFolder.ASSETS.getDirectoryPath() + File.separator + DirectoryFolder.ASSETS.getFolderName() + File.separator + DirectoryFile.LOGIN_SCREEN_LOGO.getFileName() + DirectoryFile.LOGIN_SCREEN_LOGO.getFileExtension());
        if (SPLASH_SCREEN_LOGO != null)
            IMAGE_ICON.setImage(SPLASH_SCREEN_LOGO);

        CONSTRAINTS.gridwidth = 2;
        CONSTRAINTS.insets = new Insets(10, 0, 0, 0);

        CONSTRAINTS.gridx = 0;
        CONSTRAINTS.gridy = 0;
        LOGO_LABEL.setIcon(IMAGE_ICON);
        add(LOGO_LABEL, CONSTRAINTS);

        CONSTRAINTS.gridx = 0;
        CONSTRAINTS.gridy = 1;
        USERNAME_FIELD.setText("Username");
        USERNAME_FIELD.setColumns(18);
        USERNAME_FIELD.setFont(SPXFont.SPX_MAIN_TEXT.getFont());
        USERNAME_FIELD.setBackground(SPXColor.SPX_WHITE.getColor());
        USERNAME_FIELD.setForeground(SPXColor.SPX_PROMPT.getColor());
        USERNAME_FIELD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SPXColor.SPX_RED.getColor(), 2), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
        USERNAME_FIELD.addFocusListener(setUsernameFocusListener());
        USERNAME_FIELD.addKeyListener(this);
        add(USERNAME_FIELD, CONSTRAINTS);

        CONSTRAINTS.gridx = 0;
        CONSTRAINTS.gridy = 2;
        PASSWORD_FIELD.setEchoChar((char) 0);
        PASSWORD_FIELD.setText("Password");
        PASSWORD_FIELD.setColumns(18);
        PASSWORD_FIELD.setFont(SPXFont.SPX_MAIN_TEXT.getFont());
        PASSWORD_FIELD.setBackground(SPXColor.SPX_WHITE.getColor());
        PASSWORD_FIELD.setForeground(SPXColor.SPX_PROMPT.getColor());
        PASSWORD_FIELD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SPXColor.SPX_RED.getColor(), 2), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
        PASSWORD_FIELD.addFocusListener(setPasswordFocusListener());
        PASSWORD_FIELD.addKeyListener(this);
        add(PASSWORD_FIELD, CONSTRAINTS);

        CONSTRAINTS.gridwidth = 2;

        CONSTRAINTS.gridx = 0;
        CONSTRAINTS.gridy = 3;
        CONSTRAINTS.insets = new Insets(10, 0, 0, 95);
        FORGOT_PASSWORD_LINK.setText("Forgot password?");
        FORGOT_PASSWORD_LINK.setFont(SPXFont.SPX_MAIN_TEXT.getFont());
        FORGOT_PASSWORD_LINK.setForeground(SPXColor.SPX_WHITE.getColor());
        FORGOT_PASSWORD_LINK.addMouseListener(setForgotPasswordMouseListener());
        add(FORGOT_PASSWORD_LINK, CONSTRAINTS);

        CONSTRAINTS.gridx = 1;
        CONSTRAINTS.gridy = 3;
        CONSTRAINTS.insets = new Insets(10, 158, 0, 0);
        LOGIN_BUTTON.setText("Log in");
        LOGIN_BUTTON.setFont(SPXFont.SPX_MAIN_TEXT.getFont());
        LOGIN_BUTTON.setBackground(SPXColor.SPX_RED.getColor());
        LOGIN_BUTTON.setForeground(SPXColor.SPX_WHITE.getColor());
        LOGIN_BUTTON.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SPXColor.SPX_WHITE.getColor(), 2), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
        LOGIN_BUTTON.setFocusPainted(false);
        LOGIN_BUTTON.addActionListener(setLoginButtonActionListener());
        LOGIN_BUTTON.addMouseListener(setLoginButtonMouseListener());
        LOGIN_BUTTON.addKeyListener(this);
        add(LOGIN_BUTTON, CONSTRAINTS);

        CONSTRAINTS.gridx = 0;
        CONSTRAINTS.gridy = 4;
        CONSTRAINTS.insets = new Insets(10, 0, 0, 100);
        CLIENT_VERSION.setText("[VERSION]: " + Vars.get().CLIENT_VERSION);
        CLIENT_VERSION.setFont(SPXFont.SPX_MAIN_TEXT.getFont());
        CLIENT_VERSION.setForeground(SPXColor.SPX_WHITE.getColor());
        add(CLIENT_VERSION, CONSTRAINTS);

        CONSTRAINTS.gridx = 0;
        CONSTRAINTS.gridy = 5;
        CONSTRAINTS.insets = new Insets(0, 0, 0, 93);
        STATUS_TEXT.setText("[STATUS]: ONLINE");
        STATUS_TEXT.setFont(SPXFont.SPX_MAIN_TEXT.getFont());
        STATUS_TEXT.setForeground(SPXColor.SPX_WHITE.getColor());
        if (!Vars.get().IS_CLIENT_ONLINE) {
            STATUS_TEXT.setText("[STATUS]: OFFLINE");
            STATUS_TEXT.setForeground(SPXColor.SPX_RED.getColor());
        }
        add(STATUS_TEXT, CONSTRAINTS);

        CONSTRAINTS.gridwidth = 2;

        CONSTRAINTS.gridx = 0;
        CONSTRAINTS.gridy = 6;
        CONSTRAINTS.insets = new Insets(10, 0, 0, 0);
        INCORRECT_LOGIN_INFORMATION.setText("Incorrect username or password.");
        INCORRECT_LOGIN_INFORMATION.setFont(SPXFont.SPX_MAIN_TEXT.getFont());
        INCORRECT_LOGIN_INFORMATION.setForeground(SPXColor.SPX_RED.getColor());
        INCORRECT_LOGIN_INFORMATION.setVisible(false);
        add(INCORRECT_LOGIN_INFORMATION, CONSTRAINTS);

        CONSTRAINTS.gridx = 0;
        CONSTRAINTS.gridy = 7;
        CONSTRAINTS.weighty = 1;
        add(WEIGHT_ALIGNMENT, CONSTRAINTS);
    }

    /**
     * Sets the username field prompt text.
     *
     * @return The FocusListener for the username prompt.
     */
    private FocusListener setUsernameFocusListener() {
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (USERNAME_FIELD.getText().equals("Username")) {
                    USERNAME_FIELD.setText("");
                    USERNAME_FIELD.setForeground(SPXColor.SPX_GRAY.getColor());
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (USERNAME_FIELD.getText().isEmpty()) {
                    USERNAME_FIELD.setText("Username");
                    USERNAME_FIELD.setForeground(SPXColor.SPX_PROMPT.getColor());
                }
            }
        };
    }

    /**
     * Sets the password field prompt text.
     *
     * @return The FocusListener for the password prompt.
     */
    private FocusListener setPasswordFocusListener() {
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String password = "";
                for (char character : PASSWORD_FIELD.getPassword())
                    password += character;

                if (password.equals("Password")) {
                    PASSWORD_FIELD.setEchoChar('\u2022');
                    PASSWORD_FIELD.setText("");
                    PASSWORD_FIELD.setForeground(SPXColor.SPX_GRAY.getColor());
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (PASSWORD_FIELD.getPassword().length <= 0) {
                    PASSWORD_FIELD.setEchoChar((char) 0);
                    PASSWORD_FIELD.setText("Password");
                    PASSWORD_FIELD.setForeground(SPXColor.SPX_PROMPT.getColor());
                }
            }
        };
    }


    /**
     * Sets the forgot password link actions.
     *
     * @return The MouseListener for the forgot password actions.
     */
    private MouseListener setForgotPasswordMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Request.openURL("http://138.68.45.96/");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                FORGOT_PASSWORD_LINK.setForeground(SPXColor.SPX_RED.getColor());
                FORGOT_PASSWORD_LINK.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                FORGOT_PASSWORD_LINK.setForeground(SPXColor.SPX_WHITE.getColor());
            }
        };
    }

    /**
     * Sets the login button actions.
     *
     * @return The ActionListener for the button actions.
     */
    private ActionListener setLoginButtonActionListener() {
        return e -> {
            try {
                if (!Vars.get().IS_CLIENT_ONLINE)
                    return;

                if (Authentication.authenticateToken(PASSWORD_FIELD.getPassword(), DBManager.getUserAccountToken(USERNAME_FIELD.getText()))) {
                    DBManager.incrementUserAccountValue(USERNAME_FIELD.getText(), "successful_logins");
                    DBManager.updateUserAccountDate(USERNAME_FIELD.getText(), "last_successful_login");
                    DBManager.updateUserAccountValue(USERNAME_FIELD.getText(), "last_successful_ip", Request.getIP());
                    has_logged_in = true;
                } else {
                    DBManager.incrementUserAccountValue(USERNAME_FIELD.getText(), "failed_logins");
                    DBManager.updateUserAccountDate(USERNAME_FIELD.getText(), "last_failed_login");
                    DBManager.updateUserAccountValue(USERNAME_FIELD.getText(), "last_failed_ip", Request.getIP());
                    INCORRECT_LOGIN_INFORMATION.setVisible(true);
                }
            } catch (InvalidKeySpecException | NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }
        };
    }

    /**
     * Sets the login button actions.
     *
     * @return The MouseListener for the button actions.
     */
    private MouseListener setLoginButtonMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                LOGIN_BUTTON.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        };
    }

    /**
     * Determines if the user has successfully logged in.
     *
     * @return True if the user has successfully logged in; false otherwise.
     * */
    public boolean hasLoggedIn() {
        return has_logged_in;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER)
            LOGIN_BUTTON.doClick();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}


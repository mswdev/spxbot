package org.api.design.enums;

import java.awt.*;

/**
 * Created by Sphiinx on 6/9/2017.
 */
public enum SPXFont {

    SPX_MAIN_TEXT(new Font("Verdana", Font.PLAIN, 14));

    private final Font FONT;

    SPXFont(Font font) {
        this.FONT = font;
    }

    public Font getFont() {
        return FONT;
    }

}


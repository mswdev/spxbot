package org.api.design.enums;

import java.awt.*;

/**
 * Created by Sphiinx on 6/8/2017.
 */
public enum SPXColor {

    SPX_RED(new Color(137, 46, 46)),
    SPX_GRAY(new Color(26, 24, 24)),
    SPX_WHITE(new Color(251, 249, 250));

    private final Color COLOR;

    SPXColor(Color color) {
        this.COLOR = color;
    }

    public Color getColor() {
        return COLOR;
    }

}


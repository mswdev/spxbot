package api.util;

import java.awt.*;

public class Text {

    /**
     * Gets the pixel width of the specified text.
     *
     * @param text The text to get the width of.
     * @param g    Graphics.
     */
    public static int getTextLength(String text, Graphics g) {
        if (text == null)
            return -1;

        return g.getFontMetrics().stringWidth(text);
    }

    /**
     * Replaces the null fake spaces in text.
     *
     * @param text The text.
     * @return The text with the null fake space replaced with a real space.
     * */
    public static String removeNullSpace(String text) {
        if (text == null)
            return null;

        String fixed_text = "";
        for (int i = 0; i < text.length(); i++) {
            final char character = text.charAt(i);
            final int numValue = Character.getNumericValue(character);
            fixed_text += numValue == -1 ? " " : character;
        }

        return fixed_text;
    }

    /**
     * Returns a number of periods depending on the remainder.
     * It will look somewhat like loading periods...
     *
     * @return ...
     */
    public static String getLoadingPeriods() {
        final long time = System.currentTimeMillis() % 3000;
        if (time < 600)
            return "";
        else if (time < 1300)
            return ".";
        else if (time < 2000)
            return "..";
        else
            return "...";
    }

}


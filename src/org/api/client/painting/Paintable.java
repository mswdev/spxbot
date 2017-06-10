package org.api.client.painting;

import org.api.General;

/**
 * Created by Sphiinx on 6/10/2017.
 */
public class Paintable implements Runnable, Painting {

    /**
     * The master Painting instance.
     * */
    private final Painting PAINTING;

    public Paintable(Painting painting) {
        this.PAINTING = painting;
    }

    @Override
    public void run() {
        General.sleep(50);
        this.PAINTING.onPaint();
    }

    @Override
    public void onPaint() {

    }

}


package client.painting;


import api.General;
import api.util.Logging;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sphiinx on 6/10/2017.
 */
public class Painting implements Runnable {

    /**
     * The master Painting instance list.
     * */
    private final List<Paintable> paintable_list = new ArrayList<>();

    private final Graphics graphics;

    public Painting(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void run() {
        General.sleep(50);

        //TODO iterate through paintable list, find focused tab

        //TODO paint focused tab if necessary
        Logging.debug("Should be running");
        for (Paintable paintable : paintable_list) {
            paintable.onPaint(graphics);
        }
    }

    //Getters & Setters
    public void addPaintable(Paintable p)
    {
        paintable_list.add(p);
    }

}


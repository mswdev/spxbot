package org.api;

import org.util.Logging;
import org.util.Random;
import org.util.Text;

/**
 * Created by Sphiinx on 6/9/2017.
 */
public class General {

    /**
     * Generates a random number between the min and max then sleeps for that amount of time in milliseconds.
     *
     * @param min The minimum amount of time to sleep in milliseconds.
     * @param max The maximum amount of time to sleep in milliseconds.
     * */
    public static void sleep(int min, int max) {
        final int RANDOM_NUMBER = Random.getRandomInt(min, max);
        try {
            Thread.sleep(RANDOM_NUMBER);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sleeps for the specified amount of time in milliseconds.
     * */
    public static void sleep(int sleep_time) {
        sleep(sleep_time, sleep_time);
    }

}


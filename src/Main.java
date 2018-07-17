import api.util.Logging;
import thread.ScheduledThread;
import ui.client_initialization.ClientInitialization;
import ui.login_screen.LoginScreen;
import ui.rs_applet.RSFrame;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sphiinx on 3/23/17.
 */
public class Main {

    private static final ScheduledThreadPoolExecutor THREAD_POOL_EXECUTOR = new ScheduledThreadPoolExecutor(3);

    private static final ClientInitialization CLIENT_INITIALIZATION = new ClientInitialization();

    private static final LoginScreen LOGIN_SCREEN = new LoginScreen();

    private static final RSFrame RS_FRAME = new RSFrame();

    public static void main(String[] args) {
        Logging.setDebug(true);
        THREAD_POOL_EXECUTOR.scheduleAtFixedRate(new ScheduledThread(), 0, 100, TimeUnit.MILLISECONDS);

        CLIENT_INITIALIZATION.run();
        LOGIN_SCREEN.run();
        RS_FRAME.run();
    }

}


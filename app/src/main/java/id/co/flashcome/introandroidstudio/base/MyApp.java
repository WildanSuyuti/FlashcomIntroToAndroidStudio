package id.co.flashcome.introandroidstudio.base;

import android.app.Application;

import id.co.flashcome.introandroidstudio.utility.DatabaseHandler;
import id.co.flashcome.introandroidstudio.utility.SessionManager;

/**
 * Created by kakaroto on 23/08/18.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SessionManager.init(this);
        DatabaseHandler.init(this);
    }
}

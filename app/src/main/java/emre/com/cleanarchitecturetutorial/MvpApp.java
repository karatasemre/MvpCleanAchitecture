package emre.com.cleanarchitecturetutorial;

import android.app.Application;
import android.content.Context;

import emre.com.cleanarchitecturetutorial.di.component.ApplicationComponent;
import emre.com.cleanarchitecturetutorial.di.component.DaggerApplicationComponent;
import emre.com.cleanarchitecturetutorial.di.module.ApplicationModule;
import timber.log.Timber;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */

public class MvpApp extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static MvpApp get(Context context) {
        return (MvpApp) context.getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

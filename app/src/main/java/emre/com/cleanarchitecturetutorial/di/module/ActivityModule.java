package emre.com.cleanarchitecturetutorial.di.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import emre.com.cleanarchitecturetutorial.di.ActivityScope;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Context provideContext(){
        return activity;
    }
}

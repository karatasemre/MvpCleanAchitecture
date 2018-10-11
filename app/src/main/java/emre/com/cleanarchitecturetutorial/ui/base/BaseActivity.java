package emre.com.cleanarchitecturetutorial.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import emre.com.cleanarchitecturetutorial.MvpApp;
import emre.com.cleanarchitecturetutorial.di.component.ActivityComponent;
import emre.com.cleanarchitecturetutorial.di.component.DaggerActivityComponent;
import emre.com.cleanarchitecturetutorial.di.module.ActivityModule;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .applicationComponent(MvpApp.get(this).getApplicationComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }

        return activityComponent;
    }
}

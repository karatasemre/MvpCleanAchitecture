package emre.com.cleanarchitecturetutorial.di.component;

import dagger.Component;
import emre.com.cleanarchitecturetutorial.di.ActivityScope;
import emre.com.cleanarchitecturetutorial.di.module.ActivityModule;
import emre.com.cleanarchitecturetutorial.ui.detail.DetailActivity;
import emre.com.cleanarchitecturetutorial.ui.main.MainActivity;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(DetailActivity detailActivity);

}

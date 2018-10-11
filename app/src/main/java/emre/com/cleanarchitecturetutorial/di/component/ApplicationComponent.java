package emre.com.cleanarchitecturetutorial.di.component;

import javax.inject.Singleton;

import dagger.Component;
import emre.com.cleanarchitecturetutorial.data.api.RestApi;
import emre.com.cleanarchitecturetutorial.di.module.ApplicationModule;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    RestApi restApi();

}

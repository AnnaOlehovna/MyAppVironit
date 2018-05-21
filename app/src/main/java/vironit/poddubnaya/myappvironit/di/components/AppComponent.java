package vironit.poddubnaya.myappvironit.di.components;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import vironit.poddubnaya.myappvironit.App;
import vironit.poddubnaya.myappvironit.di.modules.AppActivitiesModule;
import vironit.poddubnaya.myappvironit.di.modules.ApplicationModule;
import vironit.poddubnaya.myappvironit.di.modules.InteractorModule;
import vironit.poddubnaya.myappvironit.di.modules.ManagerModule;
import vironit.poddubnaya.myappvironit.di.modules.RepositoryModule;
import vironit.poddubnaya.myappvironit.di.modules.SchedulerModule;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.TestPresenter;

@Singleton
@Component(modules = {SchedulerModule.class,
        ManagerModule.class,
        ApplicationModule.class,
        RepositoryModule.class,
        InteractorModule.class,
        AppActivitiesModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder appContext(App app);

        AppComponent build();
    }

    void inject(App app);

    void inject(TestPresenter testPresenter);
}

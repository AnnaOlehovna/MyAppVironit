package vironit.poddubnaya.myappvironit.di.components;

import dagger.Component;
import vironit.poddubnaya.myappvironit.di.annotations.ActivityScope;
import vironit.poddubnaya.myappvironit.di.modules.ActivityModule;

@ActivityScope
@Component(modules = {ActivityModule.class}, dependencies = AppComponent.class)
public interface ActivityComponent {
}

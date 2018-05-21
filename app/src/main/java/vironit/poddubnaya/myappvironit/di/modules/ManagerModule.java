package vironit.poddubnaya.myappvironit.di.modules;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import vironit.poddubnaya.myappvironit.mvp.model.manager.implementation.ResourcesManagerImpl;
import vironit.poddubnaya.myappvironit.mvp.model.manager.interfaces.ResourcesManager;

@Module
public interface ManagerModule {


    @Binds
    @Singleton
    ResourcesManager providesResourcesManager(ResourcesManagerImpl resourcesManagerImpl);

}

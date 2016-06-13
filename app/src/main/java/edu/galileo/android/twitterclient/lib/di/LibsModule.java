package edu.galileo.android.twitterclient.lib.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.twitterclient.lib.GreenRobotEventBus;
import edu.galileo.android.twitterclient.lib.base.EventBus;

/**
 * Created by carlos.gomez on 13/06/2016.
 */
@Module
public class LibsModule {

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }
}

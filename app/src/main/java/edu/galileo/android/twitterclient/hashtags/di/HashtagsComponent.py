package edu.galileo.android.twitterclient.images.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.twitterclient.images.ImagesPresenter;
import edu.galileo.android.twitterclient.images.ui.ImagesFragment;
import edu.galileo.android.twitterclient.images.ui.adapters.ImagesAdapter;
import edu.galileo.android.twitterclient.lib.di.LibsModule;

/**
 * Created by carlos.gomez on 14/06/2016.
 */
@Singleton
@Component(modules = {LibsModule.class, ImagesModule.class})
public interface ImagesComponent {
    //inyección tipo 1. Definición de método inject y definir el target donde inyectar
    void inject(ImagesFragment fragment);

    //inyección tipo 2. En el módulo se definen los provides y este método abajo lo llamo en base al componente
    ImagesPresenter getPresenter();
    ImagesAdapter getAdapter();
}

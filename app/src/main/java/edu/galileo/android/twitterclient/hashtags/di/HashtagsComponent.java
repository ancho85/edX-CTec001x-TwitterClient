package edu.galileo.android.twitterclient.hashtags.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.twitterclient.hashtags.HashtagsPresenter;
import edu.galileo.android.twitterclient.hashtags.ui.HashtagsFragment;
import edu.galileo.android.twitterclient.hashtags.ui.adapters.HashtagsAdapter;
import edu.galileo.android.twitterclient.lib.di.LibsModule;

/**
 * Created by carlos.gomez on 14/06/2016.
 */
@Singleton
@Component(modules = {LibsModule.class, HashtagsModule.class})
public interface HashtagsComponent {
    //inyección tipo 1. Definición de método inject y definir el target donde inyectar
    void inject(HashtagsFragment fragment);

    //inyección tipo 2. En el módulo se definen los provides y este método abajo lo llamo en base al componente
    HashtagsPresenter getPresenter();
    HashtagsAdapter getAdapter();
}

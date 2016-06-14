package edu.galileo.android.twitterclient.hashtags.di;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.twitterclient.api.CustomTwitterApiClient;
import edu.galileo.android.twitterclient.entities.Hashtag;
import edu.galileo.android.twitterclient.hashtags.HashtagsInteractor;
import edu.galileo.android.twitterclient.hashtags.HashtagsInteractorImpl;
import edu.galileo.android.twitterclient.hashtags.HashtagsPresenter;
import edu.galileo.android.twitterclient.hashtags.HashtagsPresenterImpl;
import edu.galileo.android.twitterclient.hashtags.HashtagsRepository;
import edu.galileo.android.twitterclient.hashtags.HashtagsRepositoryImpl;
import edu.galileo.android.twitterclient.hashtags.ui.HashtagsView;
import edu.galileo.android.twitterclient.hashtags.ui.adapters.HashtagsAdapter;
import edu.galileo.android.twitterclient.hashtags.ui.adapters.OnItemClickListener;
import edu.galileo.android.twitterclient.lib.base.EventBus;

/**
 * Created by carlos.gomez on 14/06/2016.
 */
@Module
public class HashtagsModule {
    // hay que escribir un método por cada cosa que se ha de proveer
    private HashtagsView view;
    private OnItemClickListener clickListener;

    public HashtagsModule(HashtagsView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    //Devolver un adaptador de Hashtags, los parámetros aquí se toman desde el constructor de HashtagsAdapter
    //Cada uno de los parámetros debe de tener un @Provides asociado
    @Provides
    @Singleton
    HashtagsAdapter providesAdapter(List<Hashtag> hashtags, OnItemClickListener clickListener) {
        return new HashtagsAdapter(hashtags, clickListener);
    }

    //Devolver el listener definido en el constructor
    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return this.clickListener;
    }

    //Devolver el listado de imágenes
    @Provides
    @Singleton
    List<Hashtag> providesItemsList() {
        return new ArrayList<Hashtag>();
    }

    //El objeto que se va a tener dentro del target, en este caso es el Fragment, además del adapter
    // ya definido arriba, es el Presentador, entonces hay que proveerlo también
    //OBS:  eventBus ya tiene definido un @Provides en LibModule, por el mismo motivo ya comentado. Se definen los otros
    //      Los parámetros que recive este provider también se toman desde el constructor de lo que se va a devolver
    @Provides
    @Singleton
    HashtagsPresenter providesHashtagsPresenter(HashtagsView view, EventBus eventBus, HashtagsInteractor interactor) {
        return new HashtagsPresenterImpl(view, eventBus, interactor);
    }

    //devolver el imageView desde el constructor
    @Provides
    @Singleton
    HashtagsView providesHashtagsView() {
        return this.view;
    }

    //devolver un interactor desde la implementación de la interface
    @Provides
    @Singleton
    HashtagsInteractor providesHashtagsInteractor(HashtagsRepository repository) {
        return new HashtagsInteractorImpl(repository);
    }

    //El interactor recibe un repository como parámetro, aquí se definen los @Provides para devolver HashtagsRepositoryImpl
    // eventBus también provee la libería LibsModule
    @Provides
    @Singleton
    HashtagsRepository providesHashtagsRepository(EventBus eventBus, CustomTwitterApiClient client) {
        return new HashtagsRepositoryImpl(eventBus, client);
    }

    //Devolver el api client. El parámetro es un Session de twitter core
    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(Session session) {
        return new CustomTwitterApiClient(session);
    }

    //Devolver la sesión de twitter
    @Provides
    @Singleton
    Session providesTwitterSession(){
        return Twitter.getSessionManager().getActiveSession();
    }
}

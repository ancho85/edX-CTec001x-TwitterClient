package edu.galileo.android.twitterclient.images.di;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.twitterclient.entities.Image;
import edu.galileo.android.twitterclient.images.ImagesInteractor;
import edu.galileo.android.twitterclient.images.ImagesInteractorImpl;
import edu.galileo.android.twitterclient.images.ImagesPresenter;
import edu.galileo.android.twitterclient.images.ImagesPresenterImpl;
import edu.galileo.android.twitterclient.images.ImagesRepository;
import edu.galileo.android.twitterclient.images.ui.ImagesView;
import edu.galileo.android.twitterclient.images.ui.adapters.ImagesAdapter;
import edu.galileo.android.twitterclient.images.ui.adapters.OnItemClickListener;
import edu.galileo.android.twitterclient.lib.base.EventBus;
import edu.galileo.android.twitterclient.lib.base.ImageLoader;

/**
 * Created by carlos.gomez on 14/06/2016.
 */
@Module
public class ImagesModule {
    // hay que escribir un método por cada cosa que se ha de proveer
    private ImagesView view;
    private OnItemClickListener clickListener;

    public ImagesModule(ImagesView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    //Devolver un adaptador de images, los parámetros aquí se toman desde el constructor de ImagesAdapter
    //Cada uno de los parámetros debe de tener un @Provides asociado
    //OBS:  El imageLoader ya tiene definido un @Provides en la inyección de dependencias de la librería
    //      El módulo LibModule contiene providesImageLoader y como el componente ImagesComponent define con el
    //      annotation @Component(modules = {LibsModule.class,... entonces dagger accede en tiempo de compilación es
    //      permitir acceder al imageLoader
    @Provides
    @Singleton
    ImagesAdapter providesAdapter(List<Image> items, ImageLoader imageLoader, OnItemClickListener clickListener) {
        return new ImagesAdapter(items, imageLoader, clickListener);
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
    List<Image> providesItemsList() {
        return new ArrayList<Image>();
    }

    //El objeto que se va a tener dentro del target, en este caso es el Fragment, además del adapter
    // ya definido arriba, es el Presentador, entonces hay que proveerlo también
    //OBS:  eventBus ya tiene definido un @Provides en LibModule, por el mismo motivo ya comentado. Se definen los otros
    //      Los parámetros que recive este provider también se toman desde el constructor de lo que se va a devolver
    @Provides
    @Singleton
    ImagesPresenter providesImagesPresenter(ImagesView view, EventBus eventBus, ImagesInteractor interactor) {
        return new ImagesPresenterImpl(view, eventBus, interactor);
    }

    //devolver el imageView desde el constructor
    @Provides
    @Singleton
    ImagesView providesImagesView() {
        return this.view;
    }

    //devolver un interactor desde la implementación de la interface
    @Provides
    @Singleton
    ImagesInteractor providesImagesInteractor(ImagesRepository repository) {
        return new ImagesInteractorImpl(repository);
    }

    
}

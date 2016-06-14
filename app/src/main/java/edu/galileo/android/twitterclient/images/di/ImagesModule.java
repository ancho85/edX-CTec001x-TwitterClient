package edu.galileo.android.twitterclient.images.di;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.twitterclient.entities.Image;
import edu.galileo.android.twitterclient.images.ui.ImagesView;
import edu.galileo.android.twitterclient.images.ui.adapters.ImagesAdapter;
import edu.galileo.android.twitterclient.images.ui.adapters.OnItemClickListener;
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
    ImagesAdapter providesAdapter(List<Image> items, ImageLoader imageLoader, OnItemClickListener clickListener){
        return new ImagesAdapter(items, imageLoader, clickListener);
    }
}

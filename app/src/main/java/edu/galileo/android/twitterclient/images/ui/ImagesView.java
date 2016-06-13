package edu.galileo.android.twitterclient.images.ui;

import java.util.List;

import edu.galileo.android.twitterclient.entities.Image;

/**
 * Created by carlos.gomez on 13/06/2016.
 */
public interface ImagesView {
    void showElements();
    void hideElements();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Image> items);
}

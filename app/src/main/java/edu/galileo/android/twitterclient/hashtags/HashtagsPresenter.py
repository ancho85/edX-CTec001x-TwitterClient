package edu.galileo.android.twitterclient.hashtags;

import edu.galileo.android.twitterclient.hashtags.events.HashtagsEvent;

/**
 * Created by carlos.gomez on 13/06/2016.
 */
public interface HashtagsPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagTweets();
    void onEventMainThread(HashtagsEvent event);
}

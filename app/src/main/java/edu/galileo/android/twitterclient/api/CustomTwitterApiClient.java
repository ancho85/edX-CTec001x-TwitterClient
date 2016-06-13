package edu.galileo.android.twitterclient.api;

import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterApiClient;

/**
 * Created by carlos.gomez on 13/06/2016.
 */
public class CustomTwitterApiClient extends TwitterApiClient{
    public CustomTwitterApiClient(Session session) {
        super(session);
    }

    public TimelineService getTimelineService(){
        //a través de la interface recién creada se define la forma de acceder a este API.
        return getService(TimelineService.class);
    }
}

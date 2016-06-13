package edu.galileo.android.twitterclient.lib.base;

/**
 * Created by carlos.gomez on 13/06/2016.
 */
public interface EventBus {
    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);
}

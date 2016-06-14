package edu.galileo.android.twitterclient.hashtags;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.galileo.android.twitterclient.api.CustomTwitterApiClient;
import edu.galileo.android.twitterclient.entities.Hashtag;
import edu.galileo.android.twitterclient.hashtags.events.HashtagsEvent;
import edu.galileo.android.twitterclient.lib.base.EventBus;

/**
 * Created by carlos.gomez on 14/06/2016.
 */
public class HashtagsRepositoryImpl implements HashtagsRepository {
    private EventBus eventBus;
    private CustomTwitterApiClient client;
    private final static int TWEET_COUNT = 100;

    public HashtagsRepositoryImpl(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    @Override
    public void getHashtags() {
        Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                /*List<Image> items = new ArrayList<Image>();
                for (Tweet tweet : result.data) {
                    if (containsHashtags(tweet)) {
                        Image tweetModel = new Image();

                        tweetModel.setId(tweet.idStr);
                        tweetModel.setFavoriteCount(tweet.favoriteCount);

                        String tweetText = tweet.text;
                        int index = tweetText.indexOf("http");
                        if (index > 0) {
                            tweetText = tweetText.substring(0, index);
                        }
                        tweetModel.setTweetText(tweetText);

                        MediaEntity currentPhoto = tweet.entities.media.get(0);
                        String imageUrl = currentPhoto.mediaUrl;
                        tweetModel.setImageURL(imageUrl);

                        items.add(tweetModel);
                    }
                }

                //orden por cantidad de favoritos
                Collections.sort(items, new Comparator<Image>() {
                    @Override
                    public int compare(Image image1, Image image2) {
                        return image2.getFavoriteCount() - image1.getFavoriteCount();
                    }
                });
                post(items);*/
            }

            @Override
            public void failure(TwitterException e) {
                post(e.getLocalizedMessage());
            }
        };
        client.getTimelineService().homeTimeline(TWEET_COUNT, true, true, true, true, callback);
    }

    private boolean containsHashtags(Tweet tweet) {
        return tweet.entities != null &&
                tweet.entities.media != null &&
                !tweet.entities.media.isEmpty();
    }

    private void post(List<Hashtag> hashtags) {
        post(hashtags, null);
    }

    private void post(String error) {
        post(null, error);
    }

    private void post(List<Hashtag> hashtags, String error) {
        HashtagsEvent event = new HashtagsEvent();
        event.setError(error);
        event.setHashtags(hashtags);
        eventBus.post(event);
    }
}

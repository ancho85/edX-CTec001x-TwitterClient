package edu.galileo.android.twitterclient.images.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.twitterclient.R;
import edu.galileo.android.twitterclient.entities.Image;
import edu.galileo.android.twitterclient.lib.base.ImageLoader;

/**
 * Created by carlos.gomez on 13/06/2016.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    private List<Image> items;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    public ImagesAdapter(List<Image> items, ImageLoader imageLoader, OnItemClickListener clickListener) {
        this.items = items;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_images, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image imageTweet = items.get(position);
        holder.setOnClickListener(imageTweet, clickListener);
        holder.txtTweet.setText(imageTweet.getTweetText());
        holder.txtFavs.setText(String.valueOf(imageTweet.getFavoriteCount()));
        imageLoader.load(holder.imgMedia, imageTweet.getImageURL());
    }

    public void setItems(List<Image> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txtTweet)
        TextView txtTweet;
        @Bind(R.id.txtFavs)
        TextView txtFavs;
        @Bind(R.id.imgMedia)
        ImageView imgMedia;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        public void setOnClickListener(final Image image, final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(image);
                }
            });
        }
    }
}

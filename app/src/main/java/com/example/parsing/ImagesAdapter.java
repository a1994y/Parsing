package com.example.parsing;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImageViewHolder> {

    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;
    private  ArrayList<String> mUrlImages = new ArrayList<>();

    public ImagesAdapter (Context mContext, ArrayList<String> mImages, ArrayList<String> mUrlImages) {
        this.mImages = mImages;
        this.mContext = mContext;
        this.mUrlImages = mUrlImages;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item, parent, false);
        ImageViewHolder holder = new ImageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder (@NonNull ImageViewHolder holder, int position) {
          Glide.with(mContext).load(mImages.get(position)).into(holder.itemImage);

          holder.url.setText(mUrlImages.get(position));
    }

    @Override
    public int getItemCount () {
        return mImages.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;
        TextView url;

        public ImageViewHolder (@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.image_item);
            url = itemView.findViewById(R.id.item_url);
        }

    }
}

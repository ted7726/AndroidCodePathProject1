package com.example.wilsonsu.instagramclient;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wilsonsu.instagramclient.Models.InstagramPhoto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wilsonsu on 2/2/16.
 */
public class InstagramPhotoAdapter extends ArrayAdapter<InstagramPhoto> {

    InstagramPhotoViewHolderItem viewHolder;

    public InstagramPhotoAdapter(Context context, int resource, List<InstagramPhoto> objects) {
        super(context, resource, objects);
    }



        // Use the template to display each photo
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
            viewHolder = new InstagramPhotoViewHolderItem(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (InstagramPhotoViewHolderItem) convertView.getTag();
        }

        // Get the data item for this position
        InstagramPhoto photoItem = getItem(position);

        // clear out the image view
        viewHolder.ivPhoto.setImageResource(0);
        viewHolder.ivProfile.setImageResource(0);

        // Populate the data into the template view using the data object
        viewHolder.tvTitle.setText(photoItem.title);
        viewHolder.tvUsername.setText(photoItem.user.fullname);
        String relativeDate = DateUtils.getRelativeTimeSpanString(photoItem.time.getTime(), System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS, DateUtils.FORMAT_ABBREV_TIME).toString();
        viewHolder.tvTime.setText(relativeDate);
        viewHolder.tvLocation.setText(photoItem.locationName);
        if (photoItem.locationName!=null && photoItem.locationName.length() > 0 ) {
            viewHolder.ivLocation.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ivLocation.setVisibility(View.INVISIBLE);
        }

        viewHolder.tvComments.setText(photoItem.comments.count + " comments");
        viewHolder.tvLikes.setText(photoItem.likesCount + " likes");

        // insert the image using picasso
        Picasso picasso = Picasso.with(getContext());

        picasso.load(photoItem.photo.standard.imageUrl).fit().placeholder(R.drawable.images_holder).into(viewHolder.ivPhoto);
        picasso.load(photoItem.user.profileUrl).fit().centerInside().placeholder(R.drawable.images_holder_profile).into(viewHolder.ivProfile);


        // Return the completed view to render on screen
        return convertView;
    }

    static class InstagramPhotoViewHolderItem {
        @Bind(R.id.tvCaption)   TextView tvTitle;
        @Bind(R.id.tvUsername)  TextView tvUsername;
        @Bind(R.id.tvTime)      TextView tvTime;
        @Bind(R.id.tvLocation)  TextView tvLocation;
        @Bind(R.id.tvLikes)     TextView tvLikes;
        @Bind(R.id.tvComments)  TextView tvComments;
        @Bind(R.id.ivUserProfile)ImageView ivProfile;
        @Bind(R.id.ivPhoto)     ImageView ivPhoto;
        @Bind(R.id.ivLocation)  ImageView ivLocation;

        public InstagramPhotoViewHolderItem(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

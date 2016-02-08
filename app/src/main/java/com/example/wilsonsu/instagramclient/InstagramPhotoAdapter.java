package com.example.wilsonsu.instagramclient;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wilsonsu.instagramclient.Models.InstagramComment;
import com.example.wilsonsu.instagramclient.Models.InstagramPhoto;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Timer;

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
        final InstagramPhoto photoItem = getItem(position);

        // clear out the image view
        viewHolder.ivPhoto.setImageResource(0);
        viewHolder.ivProfile.setImageResource(0);

        // Populate the data into the template view using the data object
        viewHolder.tvTitle.setText(photoItem.title);
        viewHolder.tvUsername.setText(photoItem.user.fullname);

        viewHolder.tvTime.setText(UnitConverter.converTimetoRelativeTime(photoItem.time));
        viewHolder.tvLocation.setText(photoItem.locationName);
        if (photoItem.locationName != null && photoItem.locationName.length() > 0) {
            viewHolder.ivLocation.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ivLocation.setVisibility(View.GONE);
        }

        String commentText = UnitConverter.numberConverter(photoItem.comments.count);
        String likesText = UnitConverter.numberConverter(photoItem.likesCount);

        viewHolder.tvComments.setText(commentText);
        viewHolder.tvLikes.setText(likesText);
        viewHolder.commentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CommentsActivity.class);
                intent.putExtra("commentsId", photoItem.id);
                v.getContext().startActivity(intent);
            }
        });

        // insert the image using picasso
        Picasso picasso = Picasso.with(getContext());

        if (photoItem.comments.comments.size()>1) {
            InstagramComment comment = photoItem.comments.comments.get(photoItem.comments.comments.size()-1);
            viewHolder.commentsText1.setText(comment.text);
            viewHolder.commentsText1.setVisibility(View.VISIBLE);
            picasso.load(comment.user.profileUrl).fit().placeholder(R.drawable.images_holder).into(viewHolder.commentsUserProfile1);

            InstagramComment comment2 = photoItem.comments.comments.get(photoItem.comments.comments.size()-2);
            viewHolder.commentsText2.setText(comment2.text);
            viewHolder.commentsText2.setVisibility(View.VISIBLE);
            picasso.load(comment2.user.profileUrl).fit().placeholder(R.drawable.images_holder).into(viewHolder.commentsUserProfile2);

            setAnimationGenerator(viewHolder.commentsText1, true);
            setAnimationGenerator(viewHolder.commentsText2, false);
            setAnimationGenerator(viewHolder.commentsUserProfile1, true);
            setAnimationGenerator(viewHolder.commentsUserProfile2, false);
        } else {
            viewHolder.commentsText1.setVisibility(View.INVISIBLE);
            viewHolder.commentsText2.setVisibility(View.INVISIBLE);
        }

        picasso.load(photoItem.photo.standard.imageUrl).fit().placeholder(R.drawable.images_holder).into(viewHolder.ivPhoto);
        picasso.load(photoItem.user.profileUrl).fit().centerInside().placeholder(R.drawable.images_holder_profile).into(viewHolder.ivProfile);

        if (photoItem.type.equals("video") && photoItem.videos.standard != null) {
            viewHolder.ivPlayButton.setVisibility(View.VISIBLE);
            viewHolder.videoPlayButton.setTag(photoItem.videos.standard.imageUrl);
        } else {
            viewHolder.ivPlayButton.setVisibility(View.GONE);
        }



        // Return the completed view to render on screen
        return convertView;
    }

    static class InstagramPhotoViewHolderItem {
        @Bind(R.id.tvCaption)   TextView tvTitle;
        @Bind(R.id.tvUsername)  TextView tvUsername;
        @Bind(R.id.tvCommentTime)      TextView tvTime;
        @Bind(R.id.tvLocation)  TextView tvLocation;
        @Bind(R.id.tvLikes)     TextView tvLikes;
        @Bind(R.id.tvComments)  TextView tvComments;
        @Bind(R.id.ivCommentUserProfile)ImageView ivProfile;
        @Bind(R.id.ivPhoto)     ImageView ivPhoto;
        @Bind(R.id.ivLocation)  ImageView ivLocation;
        @Bind(R.id.ivPlayButton)ImageView ivPlayButton;
        @Bind(R.id.btPlayButton)Button videoPlayButton;
        @Bind(R.id.btComments)  Button commentsButton;
        @Bind(R.id.ivLatestCommentUserProfile1) ImageView commentsUserProfile1;
        @Bind(R.id.ivLatestCommentText1) TextView commentsText1;
        @Bind(R.id.ivLatestCommentUserProfile2) ImageView commentsUserProfile2;
        @Bind(R.id.ivLatestCommentText2) TextView commentsText2;




        public InstagramPhotoViewHolderItem(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private void setAnimationGenerator(final View view, boolean show) {
        final AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
        final AlphaAnimation animation2 = new AlphaAnimation(1.0f, 0.0f);
        animation1.setDuration(1000);
        animation1.setStartOffset(5000);
        animation2.setDuration(1000);
        animation2.setStartOffset(5000);

        //animation1 AnimationListener
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation arg0) {
                // start animation2 when animation1 ends (continue)
                view.startAnimation(animation2);
            }
        });

        //animation2 AnimationListener
        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                // start animation1 when animation2 ends (repeat)
                view.startAnimation(animation1);
            }
        });

        if (show) {
            view.startAnimation(animation1);
        } else {
            view.startAnimation(animation2);
        }

    }
}



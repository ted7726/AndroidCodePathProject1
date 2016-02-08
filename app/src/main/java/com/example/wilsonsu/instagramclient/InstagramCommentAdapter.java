package com.example.wilsonsu.instagramclient;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wilsonsu.instagramclient.Models.InstagramComment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by weishengsu on 2/7/16.
 */
public class InstagramCommentAdapter extends ArrayAdapter<InstagramComment> {

    InstagramCommentViewHolderItem viewHolder;

    public InstagramCommentAdapter(CommentsActivity commentsActivity, int resource, ArrayList<InstagramComment> comments) {
        super(commentsActivity, resource, comments);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_comment, parent, false);
            viewHolder = new InstagramCommentViewHolderItem(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (InstagramCommentViewHolderItem) convertView.getTag();
        }

        final InstagramComment commentItem = getItem(position);

        viewHolder.ivProfile.setImageResource(0);
        viewHolder.tvTitle.setText(commentItem.text);
        if (commentItem.user.fullname.length()>0) {
            viewHolder.tvUser.setText(commentItem.user.fullname);
            viewHolder.tvUser.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tvUser.setVisibility(View.INVISIBLE);
        }

        viewHolder.tvTime.setText(UnitConverter.converTimetoRelativeTime(commentItem.time));
        Picasso picasso = Picasso.with(getContext());
        picasso.load(commentItem.user.profileUrl).fit().centerInside().placeholder(R.drawable.images_holder_profile).into(viewHolder.ivProfile);
        return convertView;
    }

    static class InstagramCommentViewHolderItem {
        @Bind(R.id.tvCommentText)       TextView tvTitle;
        @Bind(R.id.tvCommentTime)       TextView tvTime;
        @Bind(R.id.tvCommentUserText)   TextView tvUser;
        @Bind(R.id.ivCommentUserProfile)ImageView ivProfile;
        public InstagramCommentViewHolderItem(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

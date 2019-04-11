package com.example.challengerva;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.sip.SipSession;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>
{
    Context context;
    Cursor cursor;
    private OnItemClickListener listener;

    public interface OnItemClickListener
    {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(UserAdapter.OnItemClickListener listen){
        this.listener = listen;
    }

    public UserAdapter (Context cont, Cursor curs)
    {
        this.context = cont;
        this.cursor = curs;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView usernameTextView;
        public UserViewHolder(@NonNull View itemView, final OnItemClickListener listen) {
            super(itemView);

            imageView = itemView.findViewById(R.id.userSearchImageView);
            usernameTextView = itemView.findViewById(R.id.usernameSearchTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listen != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                        {
                            listen.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.user,viewGroup,false);
        return new UserViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        if (!cursor.moveToPosition(i))
        {
            return;
        }

        byte[] image = cursor.getBlob(10);
        if (image == null)
        {
            userViewHolder.imageView.setImageResource(R.drawable.ic_default_profile_picture);
        }
        else
        {
            Bitmap bitmap = Utils.getImage(image);
            userViewHolder.imageView.setImageBitmap(bitmap);
        }

        userViewHolder.usernameTextView.setText(cursor.getString(0));

    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
}

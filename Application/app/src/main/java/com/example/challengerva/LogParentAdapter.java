package com.example.challengerva;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class LogParentAdapter extends RecyclerView.Adapter<LogParentAdapter.LogParentViewHolder>{

    private Context context;
    private Cursor cursorParent;
    private Cursor cursorChild;
    DBHelper db;


    public LogParentAdapter(Context cont, Cursor cursParent, Cursor cursChild)
    {
        this.context = cont;
        this.cursorParent = cursParent;
        this.cursorChild = cursChild;
    }

    public class LogParentViewHolder extends RecyclerView.ViewHolder
    {
        TextView usernameTextView;
        ImageView logImageView;
        RecyclerView recyclerView;

        public LogParentViewHolder(@NonNull View itemView) {
            super(itemView);

            usernameTextView = itemView.findViewById(R.id.logUserTextView);
            logImageView = itemView.findViewById(R.id.logImageView);
            recyclerView = itemView.findViewById(R.id.logParentRecyclerView);
        }
    }

    @NonNull
    @Override
    public LogParentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.log_parent,viewGroup,false);
        return new LogParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogParentViewHolder logParentViewHolder, int i) {
        if (!cursorParent.moveToPosition(i))
        {
            return;
        }
        db = new DBHelper(this.context);
        String username = cursorParent.getString(2);
        Cursor imageCursor = db.getBitmapByUsername(username);
        imageCursor.moveToNext();
        logParentViewHolder.logImageView.setImageBitmap(Utils.getImage(imageCursor.getBlob(0)));
        logParentViewHolder.usernameTextView.setText(username);


        cursorChild = db.getLogDataInnerJoin(cursorParent.getString(0), cursorParent.getInt(1), cursorParent.getString(2));
        logParentViewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false));
        LogChildAdapter adapter = new LogChildAdapter(this.context,cursorChild,cursorParent);
        logParentViewHolder.recyclerView.swapAdapter(adapter,false);




    }

    @Override
    public int getItemCount() {
        return cursorParent.getCount();
    }

    public void swapCursor(Cursor cursor)
    {
        if (cursorParent != null)
        {
            cursorParent.close();
        }

        cursorParent = cursor;

        if (cursor != null)
        {
            notifyDataSetChanged();;
        }
    }
}

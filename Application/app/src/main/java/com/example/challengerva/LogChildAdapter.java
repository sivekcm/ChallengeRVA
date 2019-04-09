package com.example.challengerva;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LogChildAdapter extends RecyclerView.Adapter<LogChildAdapter.LogChildViewHolder> {

    private Context context;
    private Cursor cursorChild;
    private Cursor cursorParent;
    DBHelper db;

    public LogChildAdapter(Context cont, Cursor cursChild, Cursor cursParent) {
        this.context = cont;
        this.cursorChild = cursChild;
        this.cursorParent = cursParent;
    }

    public class LogChildViewHolder extends RecyclerView.ViewHolder {
        TextView valueTextView;
        TextView unitTextView;
        TextView dateTextView;

        public LogChildViewHolder(@NonNull View itemView) {
            super(itemView);

            valueTextView = itemView.findViewById(R.id.logValueTextView);
            unitTextView = itemView.findViewById(R.id.logUnitTextView);
            dateTextView = itemView.findViewById(R.id.logDateTextView);
        }
    }

    @NonNull
    @Override
    public LogChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.log_child, viewGroup, false);
        return new LogChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogChildViewHolder logChildViewHolder, int i) {

        if (!cursorChild.moveToPosition(i)) {
            return;
        }
        String date = cursorChild.getString(0);
        int value = cursorChild.getInt(1);
        String unit = cursorChild.getString(2);

        logChildViewHolder.dateTextView.setText(date);
        logChildViewHolder.valueTextView.setText(String.valueOf(value));
        logChildViewHolder.unitTextView.setText(unit);
    }

    @Override
    public int getItemCount() {
        return cursorChild.getCount();
    }

    public void swapCursor(Cursor cursor)
    {
        if (cursorChild != null)
        {
            cursorChild.close();
        }

        cursorChild = cursor;

        if (cursor != null)
        {
            notifyDataSetChanged();;
        }
    }
}

package com.example.thien.mytodolist.utility;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thien.mytodolist.R;
import com.example.thien.mytodolist.model.ToDoItem;

import java.util.ArrayList;

/**
 * Created by Thien on 2/5/2017.
 */

public class CustomAdapter extends ArrayAdapter<ToDoItem> implements View.OnClickListener{

    private ArrayList<ToDoItem> items;
    Context mContext;

    @Override
    public void onClick(View v) {

    }

    // View lookup cache
    private static class ViewHolder {
        TextView txtId;
        TextView txtTitle;
        TextView txtPriority;

    }

    public CustomAdapter(ArrayList<ToDoItem> data, Context context) {
        super(context, R.layout.todo_item, data);
        this.items = data;
        this.mContext=context;

    }


    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ToDoItem item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.todo_item, parent, false);
            viewHolder.txtId =(TextView) convertView.findViewById(R.id.itemId);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.itemTitle);
            viewHolder.txtPriority = (TextView) convertView.findViewById(R.id.priority);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

//        viewHolder.txtId.setText(item.getId());
        viewHolder.txtTitle.setText(item.getTitle());
        switch (item.getPriority()){
            case "High":
                viewHolder.txtPriority.setText(item.getPriority());
                viewHolder.txtPriority.setTextColor(Color.parseColor("#E15076"));
                break;
            case "Medium":
                viewHolder.txtPriority.setText(item.getPriority());
                viewHolder.txtPriority.setTextColor(Color.parseColor("#FFCC77"));
                break;
            case "Low":
                viewHolder.txtPriority.setText(item.getPriority());
                viewHolder.txtPriority.setTextColor(Color.parseColor("#6db376"));
                break;
        }


        // Return the completed view to render on screen
        return convertView;
    }
}

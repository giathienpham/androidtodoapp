package com.example.thien.mytodolist;


import android.content.Intent;
import android.os.Bundle;
import android.support.test.espresso.core.deps.guava.collect.Lists;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thien.mytodolist.model.DBHelper;
import com.example.thien.mytodolist.model.ToDoItem;
import com.example.thien.mytodolist.utility.CustomAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoListFragment extends Fragment {

    ArrayList<ToDoItem> dataModels;
    ListView listView;
    private static CustomAdapter adapter;
    DBHelper mydb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("onCreateView");
        View v = inflater.inflate(R.layout.fragment_todo_list, container, false);

        getActivity().getApplicationContext();
        listView = (ListView) v.findViewById(R.id.list);


        mydb = new DBHelper(getActivity().getApplicationContext());

        dataModels = mydb.getAllTodos();
        Collections.reverse(dataModels);

        adapter= new CustomAdapter(dataModels, getActivity().getApplicationContext());



        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                ToDoItem item= dataModels.get(position);
                Intent intent = new Intent(getActivity(), EditItemActivity.class);
                Bundle b = new Bundle();
                b.putInt("id", item.getId());
                b.putString("title", item.getTitle().toString());
                b.putString("priority", item.getPriority());
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        return  v;
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onResume");
        dataModels = mydb.getAllTodos();
        Collections.reverse(dataModels);
        adapter= new CustomAdapter(dataModels, getActivity().getApplicationContext());
        listView.setAdapter(adapter);
    }
}

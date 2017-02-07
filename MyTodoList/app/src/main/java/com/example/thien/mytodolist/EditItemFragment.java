package com.example.thien.mytodolist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.thien.mytodolist.model.DBHelper;
import com.example.thien.mytodolist.model.ToDoItem;

import java.util.ArrayList;

public class EditItemFragment extends Fragment {

    DBHelper mydb;
    private TextView txtTitle;
    private Spinner spnPriority;
    int id = 0;
    String title = "";
    String priority = "";
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        Bundle b = getActivity().getIntent().getExtras();


        if(b != null){

            id = b.getInt("id");
            title = b.getString("title");
            priority = b.getString("priority");
        }


        mydb = new DBHelper(getActivity().getApplicationContext());
        View v = inflater.inflate(R.layout.fragment_edit_item, container, false);
        txtTitle = (TextView) v.findViewById(R.id.txtEditTitle);
        txtTitle.setText(title);
        spnPriority = (Spinner) v.findViewById(R.id.spnEditPriority);
        spnPriority.setSelection(convertStringToPriority(priority));

        Button btnUpdate = (Button) v.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mydb.updateTodo(id, txtTitle.getText().toString(), convertIntToPriority(spnPriority.getSelectedItemPosition()));
                getActivity().finish();
            }
        });

        Button btnDelete = (Button) v.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mydb.deleteTodo(id);
                getActivity().finish();
            }
        });

        Button btnCancelEdit = (Button) v.findViewById(R.id.btnCancelEdit);
        btnCancelEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return v;
    }

    public String convertIntToPriority(int priorityInt){
        switch (priorityInt){
            case 0:
                return "Low";
            case 1:
                return "Medium";
            case 2:
                return "High";
        }
        return "";
    }

    public int convertStringToPriority(String priority){
        switch (priority){
            case "Low":
                return 0;
            case "Medium":
                return 1;
            case "High":
                return 2;
        }
        return -1;
    }
}

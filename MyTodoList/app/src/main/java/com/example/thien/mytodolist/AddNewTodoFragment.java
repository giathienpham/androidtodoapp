package com.example.thien.mytodolist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thien.mytodolist.model.DBHelper;


public class AddNewTodoFragment extends Fragment {

    DBHelper mydb;
    private TextView title;
    private Spinner priority;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        System.out.println("running here");
        mydb = new DBHelper(getActivity().getApplicationContext());
        View v = inflater.inflate(R.layout.fragment_add_new_todo, container, false);
        title = (TextView) v.findViewById(R.id.txtTitle);
        priority = (Spinner) v.findViewById(R.id.spnPriority);

        Button btnSave = (Button) v.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println(title.getText().toString());
                System.out.println(priority.getSelectedItemPosition());
                mydb.insertTodo(title.getText().toString(), convertIntToPriority(priority.getSelectedItemPosition()));
                getActivity().finish();
            }
        });

        Button btnCancel = (Button) v.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
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


}

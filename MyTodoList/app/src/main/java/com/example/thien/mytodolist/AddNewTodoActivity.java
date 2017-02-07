package com.example.thien.mytodolist;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.thien.mytodolist.model.DBHelper;

public class AddNewTodoActivity extends AppCompatActivity {


    FragmentManager fm = this.getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_todo);


        Fragment fragment = fm.findFragmentById(R.id.add_new_todo_fragment_container);
        if (fragment == null){
            fragment = new AddNewTodoFragment();
            fm.beginTransaction().add(R.id.add_new_todo_fragment_container, fragment).commit();
        }
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new, menu);
        return true;
    }


}

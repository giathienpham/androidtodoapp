package com.example.thien.mytodolist;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
{

    FragmentManager fm = this.getSupportFragmentManager();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if (fragment == null){
            fragment = new TodoListFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.btnAddNewTodo:
                newTodo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void newTodo(){
        Intent myIntent = new Intent(MainActivity.this, AddNewTodoActivity.class);
        MainActivity.this.startActivity(myIntent);

    }

}

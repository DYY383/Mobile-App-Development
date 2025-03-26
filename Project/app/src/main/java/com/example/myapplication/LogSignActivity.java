package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LogSignActivity extends AppCompatActivity {

    public static String loggedInUser = null;


    Button submit_btn;
    EditText txt_username, txt_password;

    ToggleButton toggle_btn;
    //If checked, its in login mode



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set views
        setContentView(R.layout.log_sign);

        DBHelper dblogin = new DBHelper(getApplicationContext(), "accounts_database", null, 2);
        dblogin.getReadableDatabase();

        //Sets buttons
        submit_btn = findViewById(R.id.btnSubmit);
        txt_username = findViewById(R.id.editTextUsername);
        txt_password = findViewById(R.id.editTextPassword);
        toggle_btn = findViewById(R.id.toggleButton);


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txt_username.getText().toString();
                String password = txt_password.getText().toString();

                if (toggle_btn.isChecked()){
                    submit_login(username,password,dblogin);
                }else{
                    create_account(username,password,dblogin);
                }
            }
        });
    }


    //For a start I put in Username abc and password 123 for testing
    void submit_login(String username,String password,DBHelper dblogin){
        /*
        //Prints Database
        Cursor cursor = dblogin.displayData();
        String cursorInfo ="";
        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"Nothing to display!", Toast.LENGTH_LONG).show();
        }else{
            while(cursor.moveToNext()){
                cursorInfo +="\nUsername: "+cursor.getString(0)+" Password: "+cursor.getString(1);
            }
        }
        AlertDialog.Builder alertMessage = new AlertDialog.Builder(LogSignActivity.this);
        alertMessage.setTitle("Database info:");
        alertMessage.setMessage(cursorInfo);
        alertMessage.setCancelable(true);
        alertMessage.show();*/

        //Check if we have an exact match in database
        if (dblogin.account_login_match(username,password)){
            loggedInUser = username;
            Toast.makeText(getApplicationContext(),"Matching Account Found!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LogSignActivity.this, MainMenuActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Matching Account Not Found!", Toast.LENGTH_LONG).show();

        }

    }
    void create_account(String username,String password,DBHelper dblogin){
        if (dblogin.account_exist(username)){
            Toast.makeText(getApplicationContext(),"Account Already Exists!", Toast.LENGTH_LONG).show();
        }else{
            dblogin.addItems(username, password);
            Toast.makeText(getApplicationContext(),"Account Created!", Toast.LENGTH_LONG).show();
        }

    }




}
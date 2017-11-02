package com.rileywen.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.widget.EditText;

public class MainActivity extends Activity {

    private SharedPreferences sp;
    private Button button_login;
    private AutoCompleteTextView UserName;
    private EditText Password;
    private CheckBox Auto_login, RemerberMe;
    private String Value_Username, Value_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_login = (Button) findViewById(R.id.button_Login);
        sp = this.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        UserName = (AutoCompleteTextView) findViewById(R.id.UserName);
        Password = (EditText) findViewById(R.id.Password);
        Auto_login = (CheckBox) findViewById(R.id.StayLoggedIn);
        RemerberMe = (CheckBox) findViewById(R.id.checkBox_RememberMe);

        String []User_List = new String[]{
                sp.getString("UserName","")
        };
        ArrayAdapter<String> Username_Downlist = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                User_List);
        UserName.setAdapter(Username_Downlist);


        if (sp.getBoolean("ISCHECK",false)){
            RemerberMe.setChecked(true);
            UserName.setText(sp.getString("UserName",""));
            Password.setText(sp.getString("Password",""));
        }

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Value_Username = UserName.getText().toString();
                Value_Password = Password.getText().toString();
                if ( Value_Username.equals("WRL") && Value_Password.equals("123456") ){

                    Toast.makeText(MainActivity.this,"Login Successful!",Toast.LENGTH_SHORT).show();

                    if (RemerberMe.isChecked()) {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("UserName", Value_Username);
                        editor.putString("Password", Value_Password);
                        editor.apply();
                    }

                    String[] info = new String[]{Value_Username,Value_Password};

                    Intent intent = new Intent(MainActivity.this, Dial_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("info",info);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this,"Login Failed!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        RemerberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (RemerberMe.isChecked())
                    sp.edit().putBoolean("ISCHECK",true).apply();
                else
                    sp.edit().putBoolean("ISCHECK",false).apply();
            }
        });
    }
}

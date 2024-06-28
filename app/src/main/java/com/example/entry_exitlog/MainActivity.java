package com.example.entry_exitlog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText d1,d2;
    AppCompatButton b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        SharedPreferences pre=getSharedPreferences("Log",MODE_PRIVATE);
        String name=pre.getString("user",null);
        if(name!=null){
            Intent i=new Intent(getApplicationContext(), LogScreen.class);
            startActivity(i);
        }

        d1=(EditText) findViewById(R.id.ed1);
        d2=(EditText) findViewById(R.id.ed2);
        b1=(AppCompatButton) findViewById(R.id.bt1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             try{
                 String s1,s2;
                 s1=d1.getText().toString();
                 s2=d2.getText().toString();
                 if(s1.equals("admin") && s2.equals("1234")){
                     SharedPreferences pre=getSharedPreferences("Log",MODE_PRIVATE);
                     SharedPreferences.Editor editor = pre.edit();
                     editor.putString("user","admin");
                     editor.apply();
                     Intent i=new Intent(getApplicationContext(), LogScreen.class);
                     startActivity(i);
                 }
                 else{
                     Toast.makeText(getApplicationContext(), "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                 }
             }
             catch (Exception e){
                 Toast.makeText(getApplicationContext(),e.toString()+ "", Toast.LENGTH_SHORT).show();
             }
            }
        });
    }
}
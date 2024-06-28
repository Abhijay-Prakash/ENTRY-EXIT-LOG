package com.example.entry_exitlog;

import android.content.Intent;
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

import java.time.LocalTime;

public class LogScreen extends AppCompatActivity {

    EditText d1,d2,d3,d4;
    AppCompatButton b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_screen);

        d1=(EditText) findViewById(R.id.ed1);
        d2=(EditText) findViewById(R.id.ed2);
        d3=(EditText) findViewById(R.id.ed3);
        d4=(EditText) findViewById(R.id.ed4);

        b1=(AppCompatButton) findViewById(R.id.bt1);
        b2=(AppCompatButton) findViewById(R.id.bt2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String s1,s2,s3,s4;
                    s1=d1.getText().toString();
                    s2=d2.getText().toString();
                    s3=d3.getText().toString();
                    s4=d4.getText().toString();
                    LocalTime currentTime=LocalTime.now();
                    String s5=currentTime.toString();
                    Toast.makeText(getApplicationContext(),"Login Time:"+s5, Toast.LENGTH_LONG).show();

                    Toast.makeText(getApplicationContext(),s1+s2+s3+s4,Toast.LENGTH_LONG).show();

                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.toString()+ "", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                LocalTime currentTime=LocalTime.now();
                String s5=currentTime.toString();
                Toast.makeText(getApplicationContext(),"Exit Time:"+s5, Toast.LENGTH_LONG).show();
            }
        });



    }
}
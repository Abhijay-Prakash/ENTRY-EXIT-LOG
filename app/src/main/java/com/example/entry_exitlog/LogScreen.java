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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.time.LocalTime;

public class LogScreen extends AppCompatActivity {

    EditText d1,d2,d3,d4;
    AppCompatButton b1,b2;
    String apiUrl="http://10.0.4.16:3000/api/students";
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


                    //Creating JSON object and sending it via key-value pair
                    JSONObject stud=new JSONObject();
                    try{
                        stud.put("name",s1);
                        stud.put("admission_number",s2);
                        stud.put("system_number",s3);
                        stud.put("department",s4);
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                    }


                    //JSON object request creation
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                            Request.Method.POST,
                            apiUrl,
                            stud,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "Something went wrong, Try Again", Toast.LENGTH_SHORT).show();
                                }
                            }
                    );


                    //Request Queue
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(jsonObjectRequest);


                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.toString()+ "", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pre=getSharedPreferences("Log",MODE_PRIVATE);
                SharedPreferences.Editor editor = pre.edit();
                editor.clear();
                editor.apply();
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                LocalTime currentTime=LocalTime.now();
                String s5=currentTime.toString();
                Toast.makeText(getApplicationContext(),"Exit Time:"+s5, Toast.LENGTH_LONG).show();
            }
        });



    }
}
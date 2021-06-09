package com.se.api_crud_person;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddStudentActivity extends AppCompatActivity {

    Button btnCreate, btnReturn;

    EditText edt_name, edt_class, edt_status, edt_working;

    String url = "https://60adf1fe80a61f001733205f.mockapi.io/student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        mappingId();

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        String classroom = intent.getStringExtra("classroom");
        String status = intent.getStringExtra("status");
        String working = intent.getStringExtra("working");

        if(id != 0){
            edt_name.setText(name);
            edt_class.setText(classroom);
            edt_status.setText(status);
            edt_working.setText(working);
        }

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddStudentActivity.this, com.se.api_crud_person.StudentInfoActivity.class));
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id != 0) {
                    postDataToJsonAPI(url);
                }
            }
        });
    }

    private void putDataToJsonAPI(String url, int id) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, url + '/' + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AddStudentActivity.this, "Update Successfully!", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddStudentActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("name", edt_name.getText().toString() + "");
                params.put("class", edt_class.getText().toString() + "");
                params.put("status", edt_status.getText().toString() + "");
                params.put("working", edt_working.getText().toString() + "");

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void postDataToJsonAPI(String url) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AddStudentActivity.this, "Add Student successfull", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddStudentActivity.this, "Error to add student !", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("name", edt_name.getText().toString() + "");
                params.put("class", edt_class.getText().toString() + "");
                params.put("status", edt_status.getText().toString() + "");
                params.put("working", edt_working.getText().toString() + "");

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void mappingId() {
        btnCreate = findViewById(R.id.btnUpdate);
        btnReturn = findViewById(R.id.btnReturn);

        edt_name = findViewById(R.id.edt_name);
        edt_class = findViewById(R.id.edt_class);
        edt_status = findViewById(R.id.edt_status);
        edt_working = findViewById(R.id.edt_working);
    }
}
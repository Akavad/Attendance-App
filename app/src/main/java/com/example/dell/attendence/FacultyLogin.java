package com.example.dell.attendence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class FacultyLogin extends Activity {

    Button btnlogin;
    EditText etfno,etpassword;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);
        btnlogin= (Button) findViewById(R.id.btnlogin);
        etfno= (EditText) findViewById(R.id.etfno);
        etpassword= (EditText) findViewById(R.id.etpassword);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query="select * from facultymaster where fno="+etfno.getText()+" and password="+etpassword.getText();
                AndroidNetworking.initialize(getApplicationContext());
                AndroidNetworking.post(CommonQueries.server_url)
                        .addBodyParameter("qry",query)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    if (response.getJSONArray("data").length()!=0)
                                    {
                                        Toast.makeText(getApplicationContext(),"successfully loged in",Toast.LENGTH_LONG).show();
                                        Intent i=new Intent(getApplicationContext(),BatchListActivity.class);
                                        i.putExtra("fno",Integer.parseInt(etfno.getText().toString()));
                                        startActivity(i);
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),"login error",Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(getApplicationContext(),anError.toString(),Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });
    }
}

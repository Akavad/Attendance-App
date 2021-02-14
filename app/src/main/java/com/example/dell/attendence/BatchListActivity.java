package com.example.dell.attendence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BatchListActivity extends Activity {

    int fno;
    ListView lv;
    String query;
    List<BatchBean> list;
    BatchListViewCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_list);

        Intent intent=getIntent();
        fno=intent.getIntExtra("fno",0);
        query="select * from batchmaster where facultyno="+fno;
        lv=(ListView)findViewById(R.id.listview);
        list=new ArrayList<>();
        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.post(CommonQueries.server_url)
                .addBodyParameter("qry",query)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        list=BatchBean.getAllRecords(response);
                        adapter=new BatchListViewCustomAdapter(getApplicationContext(),R.layout.batchlist_row,list);
                        lv.setAdapter(adapter);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(),anError.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getApplicationContext(),ActivityFunction.class);
                startActivity(i);
            }
        });
    }
}

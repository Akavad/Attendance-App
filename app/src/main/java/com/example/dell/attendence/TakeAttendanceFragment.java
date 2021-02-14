package com.example.dell.attendence;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 17-03-2018.
 */

public class TakeAttendanceFragment extends Fragment {

    View view;
    List<StudentBean> list;
    ListView lv;
    Button btnsubmit;
    StudentListAdapter adapter;
    boolean attlist[];
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.take_attendance_fragment,container,false);
        lv= (ListView) view.findViewById(R.id.lv);
        btnsubmit= (Button) view.findViewById(R.id.btnsubmit);
        list=new ArrayList<>();
        AndroidNetworking.initialize(getContext());
        AndroidNetworking.post(CommonQueries.server_url)
                .addBodyParameter("qry","select * from studentmaster order by rollno")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        list=StudentBean.getAllRecords(response);
                        attlist=new boolean[list.size()];
                        adapter=new StudentListAdapter(getContext(),R.layout.student_attendance_list_row,list,attlist);
                        lv.setAdapter(adapter);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("k",list.size()+"");
                SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
                Date todayDate = new Date();
                String date = currentDate.format(todayDate);
                char status[]=new char[list.size()];
                for (int i=0;i<list.size();i++){
                    if (adapter.attlist[i]){
                        status[i]='P';
                    }else {
                        status[i]='A';
                    }
                }
                for (int i=0;i<list.size();i++){
                    AttendanceBean ab=new AttendanceBean(getContext());
                    ab.setRollno(list.get(i).getRollno());
                    ab.setStatus(status[i]);
                    ab.setAttdate(date);
                    ab.addAttendance();
                }
            }
        });
        return view;
    }
}


package com.example.dell.attendence;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 09-06-2018.
 */

public class ViewAttendance extends Fragment {
    View view;
    ListView lv1;
    List<AttendanceBean> list;
    ViewAttendanceAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.view_attendance_fragment,container,false);
        lv1= (ListView) view.findViewById(R.id.lv1);
        list=new ArrayList<>();

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            AndroidNetworking.initialize(getContext());
            AndroidNetworking.post(CommonQueries.server_url)
                    .addBodyParameter("qry","select * from attendancemaster order by rollno")
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getJSONArray("data").length()!=0){
                                    list=AttendanceBean.getAllRecords(response);
                                    adapter=new ViewAttendanceAdapter(getContext(),R.layout.view_attendance_row,list);
                                    lv1.setAdapter(adapter);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(getContext(),anError.toString(),Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
}

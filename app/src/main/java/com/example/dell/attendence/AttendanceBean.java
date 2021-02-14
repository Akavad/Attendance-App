package com.example.dell.attendence;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 22-02-2018.
 */

public class AttendanceBean {
    int rollno;
    String batchid="abc";
    static Context c;
    String attdate;
    char status;
    Context context;

    public AttendanceBean(Context context) {
        this.context = context;
        c=context;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int roolno) {
        this.rollno = roolno;
    }

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }

    public String getAttdate() {
        return attdate;
    }

    public void setAttdate(String attdate) {
        this.attdate = attdate;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public void addAttendance(){
        String query = "insert into attendancemaster(rollno,batchid,attdate,status) values('"+rollno+"','"+batchid+"','" + attdate + "','" + status + "')";
        AndroidNetworking.initialize(context);
        AndroidNetworking.post(CommonQueries.server_url)
                .addBodyParameter("qry",query)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {

                            Toast.makeText(context, ex.toString(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(context, anError.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }
    public static List<AttendanceBean> getAllRecords(JSONObject response) {
        List<AttendanceBean> list = new ArrayList<AttendanceBean>();
        try {
            JSONArray arr=response.getJSONArray("data");
            for(int i=0;i<arr.length();i++)
            {
                AttendanceBean ab=new AttendanceBean(c);
                JSONObject obj=arr.getJSONObject(i);
                ab.setRollno(obj.getInt("rollno"));
                ab.setStatus(obj.getString("status").charAt(0));
                list.add(ab);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}

package com.example.dell.attendence;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 22-02-2018.
 */

public class StudentBean {
    Context context;
    static Context c;
    int rollno;
    String enrollmentno;
    String sname;
    String fname;
    String gender;
    String mobileno;
    String emailid;

    public StudentBean(Context context) {
        this.context = context;
        c=context;
    }

    public void checkConnection(){

        AndroidNetworking.initialize(context);
        AndroidNetworking.post("http://192.168.43.167/checkconnection.php").build().getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context,response,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(context,anError.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addRecord(){
        String qry = "insert into studentmaster(enrollmentno,sname,fname,gender,mobileno,emailid) values('" + enrollmentno + "','" + sname + "','" + fname + "','" + gender + "','" + mobileno + "','" + emailid + "')";

    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getEnrollmentno() {
        return enrollmentno;
    }

    public void setEnrollmentno(String enrollmentno) {
        this.enrollmentno = enrollmentno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public static List<StudentBean> getAllRecords(JSONObject response) {
        List<StudentBean> list = new ArrayList<StudentBean>();
        try {
            JSONArray arr=response.getJSONArray("data");
            for(int i=0;i<arr.length();i++)
            {
                StudentBean sb=new StudentBean(c);
                JSONObject obj=arr.getJSONObject(i);
                sb.setRollno(obj.getInt("rollno"));
                sb.setMobileno(obj.getString("mobileno"));
                sb.setGender(obj.getString("gender"));
                sb.setEmailid(obj.getString("emailid"));
                sb.setEnrollmentno(obj.getString("enrollmentno"));
                sb.setFname(obj.getString("fname"));
                sb.setSname(obj.getString("sname"));
                list.add(sb);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}

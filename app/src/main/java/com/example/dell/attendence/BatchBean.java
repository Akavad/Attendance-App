package com.example.dell.attendence;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 22-02-2018.
 */

public class BatchBean {
    String batchid;
    String startdate;
    String enddate;
    int facultyno;
    String batchtime;
    int session;
    String subjectcode;
    String coursecode;

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public int getFacultyno() {
        return facultyno;
    }

    public void setFacultyno(int facultyno) {
        this.facultyno = facultyno;
    }

    public String getBatchtime() {
        return batchtime;
    }

    public void setBatchtime(String batchtime) {
        this.batchtime = batchtime;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public String getSubjectcode() {
        return subjectcode;
    }

    public void setSubjectcode(String subjectcode) {
        this.subjectcode = subjectcode;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }

    public  static List<BatchBean> getAllRecords(JSONObject response){
        List<BatchBean> list=new ArrayList<>();
        try {
            JSONArray arr=response.getJSONArray("data");
            for (int i=0;i<arr.length();i++)
            {
                BatchBean batch=new BatchBean();
                JSONObject obj=arr.getJSONObject(i);
                batch.setBatchid(obj.getString("batchid"));
                batch.setStartdate(obj.getString("startdate"));
                batch.setEnddate(obj.getString("enddate"));
                batch.setBatchtime(obj.getString("batchtime"));
                batch.setSubjectcode(obj.getString("subjectcode"));
                batch.setCoursecode(obj.getString("coursecode"));
                list.add(batch);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}

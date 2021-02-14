package com.example.dell.attendence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dell on 03-03-2018.
 */

public class BatchListViewCustomAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<BatchBean> list;

    public BatchListViewCustomAdapter(Context context,int layout,List<BatchBean> list){
        this.context=context;
        this.layout=layout;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(layout,parent,false);
        TextView tvstartdate=(TextView)convertView.findViewById(R.id.tvsd);
        TextView tvenddate=(TextView)convertView.findViewById(R.id.tved);
        TextView tvbatchid=(TextView)convertView.findViewById(R.id.tvbid);
        TextView tvcourseid=(TextView)convertView.findViewById(R.id.tvcid);
        TextView tvsubjectid=(TextView)convertView.findViewById(R.id.tvsid);
        TextView tvbatchtime=(TextView)convertView.findViewById(R.id.tvbtime);
        tvstartdate.setText(list.get(position).getStartdate());
        tvenddate.setText(list.get(position).getEnddate());
        tvbatchid.setText(list.get(position).getBatchid());
        tvcourseid.setText(list.get(position).getCoursecode());
        tvsubjectid.setText(list.get(position).getSubjectcode());
        tvbatchtime.setText(list.get(position).getBatchtime());
        return convertView;
    }
}

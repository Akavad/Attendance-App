package com.example.dell.attendence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dell on 09-06-2018.
 */

public class ViewAttendanceAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<AttendanceBean> list;

    public ViewAttendanceAdapter(Context context, int layout, List<AttendanceBean> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
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
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(layout,parent,false);
        TextView tvrno= (TextView) convertView.findViewById(R.id.tvrno1);
        TextView tvstaus= (TextView) convertView.findViewById(R.id.tvstatus);
        tvrno.setText(""+list.get(position).getRollno());
        tvstaus.setText(""+list.get(position).getStatus());
        return convertView;
    }
}

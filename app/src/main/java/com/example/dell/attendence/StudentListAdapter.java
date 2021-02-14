package com.example.dell.attendence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Dell on 06-06-2018.
 */

public class StudentListAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<StudentBean> list;
    boolean[]attlist;

    public StudentListAdapter(Context context,int layout,List<StudentBean> list,boolean[] attlist)
    {
        this.context=context;
        this.layout=layout;
        this.list=list;
        this.attlist=attlist;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(layout,parent,false);
        TextView tvrno= (TextView) convertView.findViewById(R.id.tvrno);
        TextView tvsname= (TextView) convertView.findViewById(R.id.tvsname);
        CheckBox cbpresent= (CheckBox) convertView.findViewById(R.id.cbpresent);
        cbpresent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                attlist[position]=isChecked;
                Toast.makeText(context,"checked "+position+"="+isChecked,Toast.LENGTH_LONG).show();
            }
        });
        cbpresent.setChecked(attlist[position]);
        tvrno.setText(""+list.get(position).getRollno());
        tvsname.setText(list.get(position).getSname());
        return convertView;
    }
}

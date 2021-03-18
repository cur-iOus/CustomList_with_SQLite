package com.curious.customlistwithsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<Member> memberArrayList;

    public CustomAdapter(Context context, ArrayList<Member> memberArrayList) {
        this.context = context;
        this.memberArrayList = memberArrayList;
    }

    public CustomAdapter() {
    }

    @Override
    public int getCount() {
        return memberArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return memberArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView  = inflater.inflate(R.layout.item_layout, parent, false);
        }
        TextView nameView = convertView.findViewById(R.id.nameTextViewID);
        TextView addressView = convertView.findViewById(R.id.addressTextViewID);

        nameView.setText(memberArrayList.get(position).getName());
        addressView.setText(memberArrayList.get(position).getAddress());
        
        return convertView;
    }
}



















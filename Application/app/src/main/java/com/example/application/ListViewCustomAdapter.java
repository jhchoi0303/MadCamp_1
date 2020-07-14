package com.example.application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class ListViewCustomAdapter extends BaseAdapter{

    private ArrayList<ListViewCustomDTO> listCustom = new ArrayList<>();


    @Override
    public int getCount() {
        return listCustom.size();
    }


    @Override
    public Object getItem(int position) {
        return listCustom.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom, null, false);

            holder = new CustomViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.textTitle = (TextView) convertView.findViewById(R.id.text_title);
            holder.textContent = (TextView) convertView.findViewById(R.id.text_content);

            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        ListViewCustomDTO dto = listCustom.get(position);

        holder.imageView.setImageResource(dto.getResId());

        holder.textTitle.setText(dto.getTitle());
        holder.textContent.setText(dto.getContent());

        return convertView;
    }

    class CustomViewHolder {
        ImageView imageView;
        TextView textTitle;
        TextView textContent;
    }


    public void addItem(ListViewCustomDTO dto) {

        listCustom.add(dto);
    }

    public void delItem(ListViewCustomDTO dto){
        listCustom.remove(dto);
    }

    public void modifyItem(int i, ListViewCustomDTO dto){
        listCustom.set(i, dto);
    }

}
package com.example.truccongle.themnhahang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by truccongle on 5/8/2017.
 */

public class NhaHangAdapter extends BaseAdapter{
    Context myContext;
    int myLayout;
    List<HinhAnh>  arrayNhaHang;

    public NhaHangAdapter(Context myContext, int myLayout, List<HinhAnh> arrayNhaHang) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.arrayNhaHang = arrayNhaHang;
    }

    @Override
    public int getCount() {
        return arrayNhaHang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayNhaHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private  class  ViewHolder{
        ImageView imgHinh;
        TextView txtNhaHang;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=  convertView;
        ViewHolder holder= new ViewHolder();
        if (view==null){
            view=inflater.inflate(myLayout,null);
            holder.txtNhaHang= (TextView) view.findViewById(R.id.textViewNhaHang);
            holder.imgHinh= (ImageView) view.findViewById(R.id.imageViewHinh);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }
        holder.txtNhaHang.setText(arrayNhaHang.get(position).TenNhaHang);
        Picasso.with(myContext).load(arrayNhaHang.get(position).HinhAnh).into(holder.imgHinh);

        return view;
    }
}

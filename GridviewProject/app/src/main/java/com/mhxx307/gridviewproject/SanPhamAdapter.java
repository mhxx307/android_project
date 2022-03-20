package com.mhxx307.gridviewproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SanPhamAdapter extends BaseAdapter {
    List<SanPham> sanPhams;
    Context context;
    int layout;

    public SanPhamAdapter(List<SanPham> sanPhams, Context context, int layout) {
        this.sanPhams = sanPhams;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return sanPhams.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        ImageView imgSanPham = view.findViewById(R.id.imgSanPham);
        TextView txtTenSanPham = view.findViewById(R.id.txtTenSanPham);
        TextView txtGiaSanPham = view.findViewById(R.id.txtGiaSanPham);
        TextView txtGiamGia = view.findViewById(R.id.txtGiamGia);

        SanPham sanPham = sanPhams.get(i);

        Bitmap bmp = BitmapFactory.decodeByteArray(sanPham.getImgSanPham(), 0, sanPham.getImgSanPham().length);
        imgSanPham.setImageBitmap(bmp);

        txtTenSanPham.setText(sanPham.getTenSanPham());
        txtGiaSanPham.setText(sanPham.getGiaSanPham() + "đ");
        txtGiamGia.setText(sanPham.getGiamGia() + "%");

        return view;
    }
}

package com.mhxx307.thuchanhtuan2_3_bt1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NhanVienAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<NhanVien> listNhanVien;

    public NhanVienAdapter(Context context, int layout, List<NhanVien> listNhanVien) {
        this.context = context;
        this.layout = layout;
        this.listNhanVien = listNhanVien;
    }

    @Override
    public int getCount() {
        return listNhanVien.size();
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

        //anh xa
        ImageView img = view.findViewById(R.id.img);
        TextView txtId = view.findViewById(R.id.txtMaNV);
        TextView txtTenNV = view.findViewById(R.id.txtTenNV);

        NhanVien nv = listNhanVien.get(i);

        img.setImageResource(nv.getImgGioiTinh());
        txtId.setText(nv.getId());
        txtTenNV.setText(nv.getTen());

        return view;
    }
}

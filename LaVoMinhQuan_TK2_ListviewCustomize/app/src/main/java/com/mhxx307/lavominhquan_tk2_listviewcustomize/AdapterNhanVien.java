package com.mhxx307.lavominhquan_tk2_listviewcustomize;

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

public class AdapterNhanVien extends BaseAdapter {
    private Context context;
    private int layout;
    private List<NhanVien> listNhanVien;

    public AdapterNhanVien(Context context, int layout, List<NhanVien> listNhanVien) {
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

        //ánh xạ
        ImageView imgNhanVien = view.findViewById(R.id.imgNhanVien);
        TextView txtMaSo = view.findViewById(R.id.txtMaSo);
        TextView txtHoTen = view.findViewById(R.id.txtHoTen);
        TextView txtGioiTinh = view.findViewById(R.id.txtGioiTinh);
        TextView txtPhongBan = view.findViewById(R.id.txtPhongBan);

        // gán giá trị
        NhanVien nhanVien = listNhanVien.get(i);

        Bitmap bitmap = BitmapFactory.decodeByteArray(nhanVien.getImage(), 0, nhanVien.getImage().length);
        imgNhanVien.setImageBitmap(bitmap);

        txtMaSo.setText("Mã số: " + nhanVien.getId());
        txtHoTen.setText("Họ tên: " + nhanVien.getFullName());
        txtGioiTinh.setText("Giới tính: " + nhanVien.getGender());
        txtPhongBan.setText("Phòng ban: " + nhanVien.getDepartment());

        return view;
    }
}

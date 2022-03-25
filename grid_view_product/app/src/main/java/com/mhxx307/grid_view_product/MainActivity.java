package com.mhxx307.grid_view_product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<SanPham> sanPhams = new ArrayList<>();
    ProductAdapter adapter;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        themMang();
        gridView = findViewById(R.id.gridview);


        adapter = new ProductAdapter(MainActivity.this, R.layout.layout_product, sanPhams);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                SanPham sanPham = sanPhams.get(i);
                intent.putExtra("sanpham", sanPham);
                startActivity(intent);
            }
        });
    }

    private void themMang() {
        sanPhams.add(new SanPham(R.drawable.kho_ga, "Khô gà", 69000, "Khô gà thơm ngon, cay nồng"));
        sanPhams.add(new SanPham(R.drawable.cap_sac, "Cáp sạc", 69000, "Cáp sạc chuyển đổi usb"));
        sanPhams.add(new SanPham(R.drawable.o_cam, "Cáp sạc chuyển đổi", 69000, "Cáp sạc chuyển đổi 2"));
        sanPhams.add(new SanPham(R.drawable.xe_can_cau, "Xe cần cẩu mô hình", 69000, "Xe cần cẩu mô hình cho trẻ con"));
        sanPhams.add(new SanPham(R.drawable.ca_nau_lau, "Ca nấu lẩu", 69000, "Ca nấu lẩu đa năng"));
        sanPhams.add(new SanPham(R.drawable.do_choi_mo_hinh, "Xe mô hình", 69000, "Mô hình đồ chơi cho trẻ con"));
        sanPhams.add(new SanPham(R.drawable.do_choi_mo_hinh, "Xe mô hình", 69000, "Mô hình đồ chơi cho trẻ con"));
        sanPhams.add(new SanPham(R.drawable.kho_ga, "Khô gà", 69000, "Khô gà thơm ngon, cay nồng"));
        sanPhams.add(new SanPham(R.drawable.cap_sac, "Cáp sạc", 69000, "Cáp sạc chuyển đổi usb"));
        sanPhams.add(new SanPham(R.drawable.o_cam, "Cáp sạc chuyển đổi", 69000, "Cáp sạc chuyển đổi 2"));
        sanPhams.add(new SanPham(R.drawable.xe_can_cau, "Xe cần cẩu mô hình", 69000, "Xe cần cẩu mô hình cho trẻ con"));
        sanPhams.add(new SanPham(R.drawable.ca_nau_lau, "Ca nấu lẩu", 69000, "Ca nấu lẩu đa năng"));
        sanPhams.add(new SanPham(R.drawable.do_choi_mo_hinh, "Xe mô hình", 69000, "Mô hình đồ chơi cho trẻ con"));
        sanPhams.add(new SanPham(R.drawable.ca_nau_lau, "Ca nấu lẩu", 69000, "Ca nấu lẩu đa năng"));
        sanPhams.add(new SanPham(R.drawable.do_choi_mo_hinh, "Xe mô hình", 69000, "Mô hình đồ chơi cho trẻ con"));
        sanPhams.add(new SanPham(R.drawable.hieu_long_tre_con, "Hiểu lòng trẻ con", 69000, "Sách hay hiểu lòng trẻ con"));
        sanPhams.add(new SanPham(R.drawable.lanh_dao_don_gian, "Sách lãnh đạo", 69000, "Sách lãnh đạo đơn giản"));
        sanPhams.add(new SanPham(R.drawable.hieu_long_tre_con, "Hiểu lòng trẻ con", 69000, "Sách hay hiểu lòng trẻ con"));
    }
}
package com.mhxx307.sqliteontap12_5_2022.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mhxx307.sqliteontap12_5_2022.R;
import com.mhxx307.sqliteontap12_5_2022.entity.Category;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Category> categories;

    public CategoryAdapter(Context context, int layout, List<Category> categories) {
        this.context = context;
        this.layout = layout;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
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

        TextView tvResult = view.findViewById(R.id.tvResult);

        Category category = categories.get(i);

        tvResult.setText("id: " + category.getId() + " - name: " + category.getName());

        return view;
    }
}

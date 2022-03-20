package com.mhxx307.figma_donut;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.ViewHolder> {

    private ArrayList<Donut> donuts;
    private Context context;

    public DonutAdapter(ArrayList<Donut> donuts, Context context) {
        this.donuts = donuts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView = inflater.inflate(R.layout.donut_layout, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Donut donut = donuts.get(position);
        holder.imgDonut.setImageResource(donut.getImgDonut());
        holder.donutName.setText(donut.getDonutName());
        holder.content.setText(donut.getContent());
        holder.price.setText("$"+donut.getPrice());

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ActivitySecond.class);
                intent.putExtra("donut", donut);
                view.getContext().startActivity(intent);
            }
        });

//        holder.item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), ActivitySecond.class);
//                view.getContext().startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return donuts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View item;
        public ImageView imgDonut;
        public TextView donutName;
        public TextView content;
        public TextView price;
        public ImageView btnView;

        public ViewHolder(View itemView) {
            super(itemView);
            item = itemView;
            donutName = itemView.findViewById(R.id.txtDonutName);
            content = itemView.findViewById(R.id.txtContent);
            imgDonut = itemView.findViewById(R.id.img_donut);
            btnView = itemView.findViewById(R.id.btnView);
            price = itemView.findViewById(R.id.txtPrice);
        }

    }

}

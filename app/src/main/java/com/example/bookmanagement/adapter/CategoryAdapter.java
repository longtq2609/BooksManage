package com.example.bookmanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanagement.datai.DetailCategoryActivity;
import com.example.bookmanagement.R;
import com.example.bookmanagement.dao.CategoryDAO;
import com.example.bookmanagement.model.Category;


import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context context;
    private List<Category> list;
    private CategoryDAO categoryDAO;

    public CategoryAdapter(Context context, List<Category> list) {
        this.context = context;
        this.list = list;
        categoryDAO = new CategoryDAO(context);
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvIdCategory.setText("Mã Thể Loại: "+list.get(position).getIdCategory());
        holder.tvNameCategory.setText("Tên Thể Loại: "+list.get(position).getNameCategory());

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDAO.deleteCategory(list.get(position).getIdCategory());
                Category category = list.get(position);
                list.remove(category);
                notifyDataSetChanged();
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category =  list.get(position);
                Intent intent = new Intent(context, DetailCategoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("idCategory", category.getIdCategory());
                bundle.putString("nameCategory", category.getNameCategory());
                bundle.putString("moTa", category.getMoTa());
                bundle.putString("viTri", category.getViTri());
                intent.putExtra("category", bundle);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivIcon;
        private TextView tvIdCategory;
        private TextView tvNameCategory;
        private ImageView ivDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            tvIdCategory = itemView.findViewById(R.id.tvIdCategory);
            tvNameCategory = itemView.findViewById(R.id.tvNameCategory);
            ivDelete = itemView.findViewById(R.id.ivDeleteCategory);
        }
    }

}

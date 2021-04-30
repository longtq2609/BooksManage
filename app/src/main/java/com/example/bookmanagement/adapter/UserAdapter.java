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

import com.example.bookmanagement.datai.DetailUserActivity;
import com.example.bookmanagement.R;

import com.example.bookmanagement.dao.UserDAO;
import com.example.bookmanagement.model.User;


import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private List<User> list;
    private UserDAO userDAO;

    public UserAdapter(){
    }

    public UserAdapter(Context context, List<User> list){
        this.context = context;
        this.list = list;
        userDAO = new UserDAO(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.tvName.setText("Tên: "+list.get(position).getName());
            holder.tvPhone.setText("Số Điện Thoại: "+list.get(position).getPhone());

            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userDAO.deleteUser(list.get(position).getUsername());
                    User user = list.get(position);
                    list.remove(user);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }
            });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user =  list.get(position);
                Intent intent = new Intent(context, DetailUserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username", user.getUsername());
                bundle.putString("password", user.getPassword());
                bundle.putString("phone", user.getPhone());
                bundle.putString("name", user.getName());
                intent.putExtra("user", bundle);
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
        private TextView tvName;
        private TextView tvPhone;
        private ImageView ivDelete;

        public ViewHolder( View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            ivDelete = itemView.findViewById(R.id.ivDeleteUser);

        }



}

}

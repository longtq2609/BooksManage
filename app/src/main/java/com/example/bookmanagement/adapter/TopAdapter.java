package com.example.bookmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanagement.R;
import com.example.bookmanagement.dao.BookDAO;
import com.example.bookmanagement.model.Book;

import java.util.List;

public class TopAdapter  extends RecyclerView.Adapter<TopAdapter.ViewHolder>  {
    private Context context;
    private List<Book> list;
    private BookDAO bookDAO;

    public TopAdapter() {
    }
    public TopAdapter(Context context, List<Book> list) {
        this.context = context;
        this.list = list;
        bookDAO = new BookDAO(context);
    }
    @NonNull
    @Override
    public TopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_top_10, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopAdapter.ViewHolder holder, int position) {
        holder.tvNameBook.setText("Mã Sách: " +list.get(position).getTieuDe());
        holder.amountBook.setText("Số Lượng: "+String.valueOf(list.get(position).getSoLuong()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivIcon;
        private TextView tvNameBook;
        private TextView tvPriceBook;
        private TextView amountBook;
        private ImageView ivDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            tvNameBook = itemView.findViewById(R.id.tvNameBook);
            tvPriceBook = itemView.findViewById(R.id.tvPriceBook);
            amountBook = itemView.findViewById(R.id.amountBook);
            ivDelete = itemView.findViewById(R.id.ivDeleteBook);
        }
    }
}

package com.example.bookmanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanagement.R;
import com.example.bookmanagement.dao.BookDAO;
import com.example.bookmanagement.datai.DetailBookActivity;
import com.example.bookmanagement.model.Book;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class BookAdapter  extends RecyclerView.Adapter<BookAdapter.ViewHolder>  {
    private Context context;
    private List<Book> list;
    private BookDAO bookDAO;

    public BookAdapter() {
    }

    public BookAdapter(Context context, List<Book> list) {
        this.context = context;
        this.list = list;
        bookDAO = new BookDAO(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        NumberFormat numberFormat = new DecimalFormat("#,###");
        holder.tvNameBook.setText("Tên Sách: " +list.get(position).getTieuDe());
        holder.tvPriceBook.setText("Giá Sách: "+numberFormat.format(list.get(position).getGiaBia())+" VND");
        holder.amountBook.setText("Số Lượng: "+String.valueOf(list.get(position).getSoLuong()));

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookDAO.deleteBook(list.get(position).getIdBook());
                Book book = list.get(position);
                list.remove(book);
                notifyDataSetChanged();
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = list.get(position);
                Intent intent = new Intent(context, DetailBookActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("idBook", book.getIdBook());
                bundle.putString("idCategory", book.getIdCategory());
                bundle.putString("tieuDe", book.getTieuDe());
                bundle.putString("nxb", book.getNxb());
                bundle.putString("tacGia", book.getTacGia());
                bundle.putString("giaBia", String.valueOf(book.getGiaBia()));
                bundle.putString("soLuong", String.valueOf(book.getSoLuong()));
                intent.putExtra("book", bundle);
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
        private TextView tvNameBook;
        private TextView tvPriceBook;
        private TextView amountBook;
        private ImageView ivDelete;
        public ViewHolder( View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            tvNameBook = itemView.findViewById(R.id.tvNameBook);
            tvPriceBook = itemView.findViewById(R.id.tvPriceBook);
            amountBook = itemView.findViewById(R.id.amountBook);
            ivDelete = itemView.findViewById(R.id.ivDeleteBook);
        }
    }
}

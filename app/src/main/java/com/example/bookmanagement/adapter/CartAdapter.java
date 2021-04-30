package com.example.bookmanagement.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookmanagement.R;
import com.example.bookmanagement.dao.DetailBillDAO;
import com.example.bookmanagement.model.Bill;
import com.example.bookmanagement.model.DetailBill;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class CartAdapter extends BaseAdapter {
    List<DetailBill> detailBills;
    public Activity context;
    public LayoutInflater inflater;
    DetailBillDAO detailBillDAO;

    public CartAdapter(Activity context, List<DetailBill> arrayHoaDonChiTiet){
        super();
        this.context = context;
        this.detailBills = arrayHoaDonChiTiet;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        detailBillDAO = new DetailBillDAO(context);
    }

    @Override
    public int getCount() {
        return detailBills.size();
    }

    @Override
    public Object getItem(int position) {
        return detailBills.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder{
        ImageView img;
        TextView tvMaSach;
        TextView tvSoLuong;
        TextView tvGiaBia;
        TextView tvThanhTien;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_cart,null);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.ivIcon);
            viewHolder.tvMaSach = (TextView) convertView.findViewById(R.id.tvMaSach);
            viewHolder.tvSoLuong = (TextView) convertView.findViewById(R.id.tvSoLuong);
            viewHolder.tvGiaBia = (TextView) convertView.findViewById(R.id.tvGiaBia);
            viewHolder.tvThanhTien = (TextView) convertView.findViewById(R.id.tvThanhTien);
            viewHolder.imgDelete = (ImageView) convertView.findViewById(R.id.ivDelete);
            viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    detailBillDAO.deleteDetailBill(String.valueOf(detailBills.get(position).getIdHDCT()));
                    detailBills.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();
        NumberFormat numberFormat = new DecimalFormat("#,###");
        DetailBill _entry  = (DetailBill) detailBills.get(position);
        viewHolder.tvMaSach.setText("Mã Sách      : "+_entry.getBook().getIdBook());
        viewHolder.tvSoLuong.setText("Số Lượng    : "+_entry.getSoLuongMua());
        viewHolder.tvGiaBia.setText("Giá Bìa         : "+numberFormat.format(_entry.getBook().getGiaBia())+ " VND");
         viewHolder.tvThanhTien.setText("Thành Tiền : "+numberFormat.format(_entry.getSoLuongMua()*_entry.getBook().getGiaBia())+" VND");
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<DetailBill> items){
        this.detailBills = items;
        notifyDataSetChanged();
    }
}

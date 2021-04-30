package com.example.bookmanagement.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanagement.R;
import com.example.bookmanagement.dao.BillDAO;
import com.example.bookmanagement.dao.DetailBillDAO;
import com.example.bookmanagement.model.Bill;
import com.example.bookmanagement.model.DetailBill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BillAdapter extends BaseAdapter implements Filterable {
    List<Bill> arrBill;
    List<Bill> arrSortHoDon;
    private Filter hoaDonFilter;
    public Activity context;
    public LayoutInflater inflater;
    BillDAO billDAO;
    DetailBillDAO detailBillDAO;
    SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
    public BillAdapter(Activity context, List<Bill> arrayHoaDon){
        super();
        this.context = context;
        this.arrBill = arrayHoaDon;
        this.arrSortHoDon = arrayHoaDon;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        billDAO = new BillDAO(context);
        detailBillDAO = new DetailBillDAO(context);
    }
    @Override
    public int getCount() {
        return arrBill.size();
    }

    @Override
    public Object getItem(int position) {
        return arrBill.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    public static class ViewHolder{
        ImageView img;
        TextView txtIdBill;
        TextView txtDayBill;
        ImageView imgDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_bill,null);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.ivIcon);
            viewHolder.txtIdBill = (TextView) convertView.findViewById(R.id.tvIdBill);
            viewHolder.txtDayBill = (TextView) convertView.findViewById(R.id.tvDayBuy);
            viewHolder.imgDelete = (ImageView) convertView.findViewById(R.id.ivDeleteBill);
            viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(detailBillDAO.checkHD(arrBill.get(position).getIdBill())){
                        Toast.makeText(context,"Phải xóa hóa đơn chi tiết",Toast.LENGTH_LONG).show();
                    }else {
                        billDAO.deleteBill(arrBill.get(position).getIdBill());
                        arrBill.remove(position);
                        notifyDataSetChanged();
                    }
                }
            });
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder)convertView.getTag();
        Bill _entry = arrBill.get(position);
        viewHolder.txtIdBill.setText(_entry.getIdBill());
        viewHolder.txtDayBill.setText(simpleDateFormat.format(_entry.getDayBuy()));
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<Bill> items){
        this.arrBill = items;
        notifyDataSetChanged();
    }
    public void resetData(){
        arrBill=arrSortHoDon;
    }
    @Override
    public Filter getFilter() {
        if (hoaDonFilter == null){
            hoaDonFilter = new CustomFilter();
        }
        return hoaDonFilter;
    }
    private class CustomFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint==null || constraint.length()==0){
                results.values = arrSortHoDon;
                results.count=arrSortHoDon.size();
            }else {
                List<Bill> lsSach = new ArrayList<>();
                for (Bill p: arrBill){
                    if(p.getIdBill().toUpperCase().startsWith(constraint.toString().toUpperCase()))lsSach.add(p);
                }
                results.values=lsSach;
                results.count=lsSach.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count==0){
                notifyDataSetChanged();
            }else {
                arrBill=(List<Bill>)results.values;
                notifyDataSetChanged();
            }
        }
    }
}

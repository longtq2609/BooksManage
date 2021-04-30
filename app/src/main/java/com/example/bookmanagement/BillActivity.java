package com.example.bookmanagement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmanagement.dao.BillDAO;
import com.example.bookmanagement.dao.BookDAO;
import com.example.bookmanagement.datai.DetailBillActivity;
import com.example.bookmanagement.model.Bill;
import com.example.bookmanagement.model.DetailBill;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BillActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText edDayBill,edIdBill;
    BillDAO billDAO;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Thêm Hóa Đơn");
        setContentView(R.layout.activity_bill);
        edDayBill = (EditText) findViewById(R.id.edDayBill);
        edIdBill = (EditText) findViewById(R.id.edIdBill);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = new GregorianCalendar(year, month, dayOfMonth);
        setDate(cal);
    }
    private void setDate(final Calendar calendar){
        edDayBill.setText(simpleDateFormat.format(calendar.getTime()));
    }

    public static class DatePickerFragment extends DialogFragment{
        @Override
        public Dialog onCreateDialog( Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(), year, month,day);
        }


    }
    public void datePicker(View view){
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(),"date");
    }


    public void addDayBill(View view) {
        billDAO = new BillDAO(BillActivity.this);
        try {
            if(validation() < 0){
                Toast.makeText(getApplicationContext(),"Vui Lòng Nhập Thông Tin",Toast.LENGTH_SHORT).show();
            }else {
                Bill bill = new Bill(edIdBill.getText().toString(), simpleDateFormat.parse(edDayBill.getText().toString()));
                if(billDAO.insertBill(bill)>0){
                    Toast.makeText(getApplicationContext(),"Thêm Thành Công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BillActivity.this, DetailBillActivity.class);
                    Bundle b = new Bundle();
                    b.putString("IDBILL", edIdBill.getText().toString());
                    intent.putExtras(b);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Thêm Thất Bại",Toast.LENGTH_SHORT).show();

                }
            }
        }catch (Exception e){
            Log.e("Erro",e.toString());
        }

}





    public int validation(){
        if(edIdBill.getText().toString().isEmpty() || edDayBill.getText().toString().isEmpty()){
            return -1;
        }
        return 1;
    }
}



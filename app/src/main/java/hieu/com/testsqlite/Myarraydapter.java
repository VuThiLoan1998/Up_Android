package hieu.com.testsqlite;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Myarraydapter extends ArrayAdapter<Sinhvien> {
    Activity context;
    int layOutID;
    ArrayList<Sinhvien> arrSV;

    public Myarraydapter( Activity context, int resource,  ArrayList<Sinhvien> objects) {
        super(context, resource, objects);
        this.context=context;
        this.layOutID=resource;
        this.arrSV=objects;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null)
        {
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.h,null);

            holder = new ViewHolder();

            holder.tvMa = (TextView)convertView.findViewById(R.id.tv_ma);
            holder.tvTen = (TextView)convertView.findViewById(R.id.tv_ten);
            holder.tvGT=(TextView)convertView.findViewById(R.id.tv_gt);
            holder.tvNS=(TextView)convertView.findViewById(R.id.tv_ns);
            holder.tvMaLop=(TextView)convertView.findViewById(R.id.tv_malop);
           // holder.Hinh = (ImageView) convertView.findViewById(R.id.img_item);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tvMa.setText(arrSV.get(position).getMasv());
        holder.tvTen.setText(arrSV.get(position).getTensv());
        holder.tvGT.setText(arrSV.get(position).getGioitinh());
        holder.tvNS.setText(arrSV.get(position).getNgaysinh());
        holder.tvMaLop.setText(arrSV.get(position).getMalop());




        return convertView;

    }
    public class ViewHolder{
        private TextView tvMa,tvTen,tvGT,tvNS,tvMaLop;
        private ImageView Hinh;
    }
}

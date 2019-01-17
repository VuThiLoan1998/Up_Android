package hieu.com.testsqlite;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class xemsv extends AppCompatActivity implements View.OnClickListener{

    EditText edtmasv,edttensv,edtngaysinh;
    Button btnthem,btntrove;
    RadioGroup radgrGT;
    RadioButton radNam;
    RadioButton radNu;


    ArrayList<LopHoc>arrLH;
    ArrayAdapter<LopHoc>addLH;
    Spinner splop;
    DatabaseSQLite dbSQL;
    public  static  final  int GOITRALAI=200;

    void anhxa(){
        edtmasv=findViewById(R.id.edt_masv);
        edttensv=findViewById(R.id.edt_tensv);
        edtngaysinh=findViewById(R.id.edt_ngaysinh);
        btnthem=findViewById(R.id.btn_Them);
        btntrove=findViewById(R.id.btn_TroVe);
        splop=(Spinner)findViewById(R.id.spn_lop);
        splop=findViewById(R.id.spn_lop);
        radgrGT=(RadioGroup)findViewById(R.id.rabgr_GT);
        radNam=(RadioButton)findViewById(R.id.rab_nam);
        radNu=(RadioButton)findViewById(R.id.rab_nu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemsv);
        anhxa();
        dbSQL= new DatabaseSQLite(this,"CSDL.db",null,1);
        arrLH=dbSQL.getalllop();
        addLH= new ArrayAdapter<LopHoc>(this,android.R.layout.simple_spinner_item ,arrLH);
        addLH.setDropDownViewResource(android.R.layout.simple_spinner_item);
        splop.setAdapter(addLH);
        btnthem.setOnClickListener(this);
        btntrove.setOnClickListener(this);

        Intent intsv=getIntent();
        Intent intent=getIntent();
        if(intent.getSerializableExtra("SINHVIENSUA")!=null)
        {
            Sinhvien sv= (Sinhvien) intsv.getSerializableExtra("SINHVIENSUA");
            edtmasv.setText(sv.getMasv());
            edttensv.setText(sv.getTensv());
            edtngaysinh.setText(sv.getNgaysinh());
            if(sv.getGioitinh().equals("Nam")){
                radNam.setChecked(true);
            }
            else {
                radNu.setChecked(true);
            }

           btnthem.setText("Cập nhật");
        }

    }

/*
    public void xlthemsv(View view) {
        LopHoc lh=arrLH.get(splop.getSelectedItemPosition());
        Sinhvien sv=new Sinhvien();
        sv.setMalop(edtmasv.getText().toString());
        sv.setTensv(edttensv.getText().toString());
        sv.setMalop(lh.getMalop());
        if(!dbSQL.getallSV().contains(sv))
        {
            dbSQL.Themsv(sv);
            Toast.makeText(this,"Thêm thành công",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            Toast.makeText(this,"Trùng mã sinh viên nha !",Toast.LENGTH_SHORT).show();
        }
    }

    public void xltrovee(View view) {
        Intent intent=new Intent();
        setResult(GOITRALAI,intent);
        finish();
    }
*/
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_Them:
                if(btnthem.getText().equals("Thêm")){
                    LopHoc hsx = arrLH.get(splop.getSelectedItemPosition());
                    Sinhvien sv = new Sinhvien();
                    sv.setMasv(edtmasv.getText().toString());
                    sv.setTensv(edttensv.getText().toString());


                    int k = radgrGT.getCheckedRadioButtonId();
                    if(k==R.id.rab_nam)
                    {
                        sv.setGioitinh("Nam");
                    }
                    else
                    {
                        sv.setGioitinh("Nữ");
                    }
                    sv.setNgaysinh(edtngaysinh.getText().toString());
                    sv.setMalop(hsx.getMalop());
                    if(!dbSQL.getallSV().contains(sv))
                    {
                        dbSQL.Themsv(sv);
                        Toast.makeText(this,"Thêm thành công !",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        Toast.makeText(this,"Trùng mã sinh viên!",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    LopHoc hsx = arrLH.get(splop.getSelectedItemPosition());
                    Sinhvien sv = new Sinhvien();
                    sv.setMasv(edtmasv.getText().toString());
                    sv.setTensv(edttensv.getText().toString());


                    int k = radgrGT.getCheckedRadioButtonId();
                    if(k==R.id.rab_nam)
                    {
                        sv.setGioitinh("Nam");
                    }
                    else
                    {
                        sv.setGioitinh("Nữ");
                    }
                    sv.setNgaysinh(edtngaysinh.getText().toString());
                    sv.setMalop(hsx.getMalop());
                    dbSQL.Update(sv);
                    Toast.makeText(this,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_TroVe:
                Intent inTraLai = new Intent();
                setResult(GOITRALAI,inTraLai );//se goi ngay onActivityResult ben phan hien Activity ( mainActivity)
                finish();
                break;

        }
    }
}

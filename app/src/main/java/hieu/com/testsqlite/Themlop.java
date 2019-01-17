package hieu.com.testsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Themlop extends AppCompatActivity implements View.OnClickListener{

    EditText edtmalop,edttenlop,edtsiso;
    Button btnthemlop,btntrove;
    ArrayList<LopHoc> arrlop;
    ArrayAdapter<LopHoc> addlop;
    DatabaseSQLite dbSQL;
    public  static  final  int GOITRALAI=300;
    void anhxa(){
        edtmalop=findViewById(R.id.edt_malop);
        edttenlop=findViewById(R.id.edt_tenlop);
        edtsiso=findViewById(R.id.edt_siso);
        btnthemlop=findViewById(R.id.btn_themlophoc);
        btntrove=findViewById(R.id.btn_trovelop);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themlop);
        anhxa();
        dbSQL= new DatabaseSQLite(this,"CSDL.db",null,1);


        arrlop=dbSQL.getalllop();
        addlop=new ArrayAdapter<LopHoc>(this,android.R.layout.simple_spinner_item,arrlop);
        addlop.setDropDownViewResource(android.R.layout.simple_spinner_item);

        btnthemlop.setOnClickListener(this);
        btntrove.setOnClickListener(this);
    }



    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_themlophoc:


                LopHoc lh = new LopHoc();
                lh.setMalop(edtmalop.getText().toString());
                lh.setTenlop(edttenlop.getText().toString());
                lh.setSiso(edtsiso.getText().toString());
                if(!dbSQL.getalllop().contains(lh))
                {
                    dbSQL.Themlop(lh);
                    Toast.makeText(this,"Thêm thành công !",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    Toast.makeText(this,"Trùng mã lớp !",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_trovelop:
                Intent inTraLai = new Intent();
                setResult(GOITRALAI,inTraLai );//se goi ngay onActivityResult ben phan hien Activity ( mainActivity)
                finish();
                break;

        }
    }
}

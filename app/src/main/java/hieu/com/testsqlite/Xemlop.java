package hieu.com.testsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Xemlop extends AppCompatActivity {

    Button btnthemlop,btntrove;
    ListView lvwdslop;
    ArrayList<LopHoc>arrlop;
    ArrayAdapter<LopHoc>addlop;
    DatabaseSQLite dbSQLite;
    public static  final  int  GOIDIMA=100;
    void anhxa(){
        btnthemlop=findViewById(R.id.btn_themlop);
        btntrove=findViewById(R.id.btn_trove);
        lvwdslop=findViewById(R.id.lvw_dslop);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemlop);
        anhxa();


        dbSQLite=new DatabaseSQLite(this,"CSDL.db",null,1);

        guidulieuvaolistview();

    }
    void guidulieuvaolistview(){
        arrlop=new ArrayList<LopHoc>();
        arrlop=dbSQLite.getalllop();
        addlop=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,arrlop);
        lvwdslop.setAdapter(addlop);
    }

    public void xlthemlop(View view) {
        Intent intent=new Intent(Xemlop.this,Themlop.class);
        startActivityForResult(intent,GOIDIMA);

    }

    public void xltrove(View view) {
        Intent intent=new Intent(Xemlop.this,MainActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GOIDIMA){
            switch (resultCode){
                case 300:
                    arrlop=dbSQLite.getalllop();
                    addlop=new ArrayAdapter<LopHoc>(this,android.R.layout.simple_spinner_item,arrlop);
                    addlop.setDropDownViewResource(android.R.layout.simple_spinner_item);
                    lvwdslop.setAdapter(addlop);
                    break;
            }

        }

    }

}

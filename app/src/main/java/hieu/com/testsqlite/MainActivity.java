package hieu.com.testsqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

    Button btnxemlop,btnthemsv;
    ListView lvwds;
    ArrayList<Sinhvien>arrayList;
    ArrayAdapter<Sinhvien>arrayAdapter;
    private DatabaseSQLite dbsql;
    int viTriChon;
    public static  final  int  GOIDIMA=100;
    public static final int GOIDIMASUA=300;

    void anhxa(){
        btnthemsv=findViewById(R.id.btn_themsv);
        btnxemlop=findViewById(R.id.btn_xemlop);
        lvwds=findViewById(R.id.lvw_ds);
    }
    void showMymenuSP() {
        PopupMenu popupMenu = new PopupMenu(this, lvwds);
        popupMenu.getMenuInflater().inflate(R.menu.menutest, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.mnu_sua:
                        Sinhvien svSua= arrayList.get(viTriChon);
                        Intent intent = new Intent(MainActivity.this, xemsv.class);
                        intent.putExtra("SINHVIENSUA",  svSua);
                        startActivityForResult(intent, GOIDIMASUA);

                        break;

                    case R.id.mnu_xoa:
                        Sinhvien sv= arrayList.get( viTriChon);
                        dbsql.Delete(sv.getMasv());
                        //cập nhận lại view
                        arrayList = dbsql.getallSV();
                        arrayAdapter = new Myarraydapter(MainActivity.this, R.layout.h, arrayList);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        lvwds.setAdapter(arrayAdapter);

                        break;
                    case R.id.mnu_them:
                        Intent intents=new Intent(MainActivity.this,xemsv.class);
                        startActivityForResult(intents,GOIDIMA);
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();

        dbsql=new DatabaseSQLite(this,"CSDL.db",null,1);
        arrayList=new ArrayList<Sinhvien>();
        arrayList=dbsql.getallSV();
        arrayAdapter=new Myarraydapter(this,R.layout.h,arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        lvwds.setAdapter(arrayAdapter);
        lvwds.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                viTriChon=i;
                showMymenuSP();
                return false;

            }
        });


    }

    public void xulythemsv(View view) {
        Intent intent=new Intent(MainActivity.this,xemsv.class);
        startActivityForResult(intent,GOIDIMA);
    }

    public void xulyxemlop(View view) {
        Intent intent=new Intent(MainActivity.this,Xemlop.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GOIDIMA || requestCode==GOIDIMASUA){
            switch (resultCode){
                case 200:
                    arrayList=dbsql.getallSV();
                    arrayAdapter=new Myarraydapter(MainActivity.this,R.layout.h,arrayList);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                    lvwds.setAdapter(arrayAdapter);
                    break;
            }

        }
    }


}

package com.example.inschool;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
EditText edit_id,edit_fname,edit_lname;
MyDBOpenHelper myHelper;
SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_remove).setOnClickListener(this);
        findViewById(R.id.btn_read).setOnClickListener(this);
        edit_id=findViewById(R.id.edt_uid);
        edit_fname=findViewById(R.id.edt_firstname);
        edit_lname=findViewById(R.id.edt_lastname);
        myHelper=new MyDBOpenHelper(this,"user_db",null,1);
    }

    @Override
    public void onClick(View view) {
    if(view.getId()==R.id.btn_add){
        db=myHelper.getWritableDatabase();
        String sql="Insert into user values(?,?,?)";
        String id=edit_id.getText().toString();
        String fn=edit_fname.getText().toString();
        String ln=edit_lname.getText().toString();
        db.execSQL(sql,new String[]{id,fn,ln});
        db.close();
    } else if(view.getId()==R.id.btn_read){
    db=myHelper.getReadableDatabase();
    String sql="SELECT * FROM user";
        Cursor c=db.rawQuery(sql,null);
        String result="";
        while (c.moveToNext()){
            int uid=c.getInt(0);
            int f_index=c.getColumnIndex("fname");
            String fn=c.getString(f_index);
            int l_index=c.getColumnIndex("lname");
            String ln=c.getString(l_index);
            result+=uid+": " +fn+" "+ln+"\n";
        }
        ((TextView)findViewById(R.id.tv_show)).setText(result);
        db.close();
    } else if(view.getId()==R.id.btn_remove){

    }
    }
}
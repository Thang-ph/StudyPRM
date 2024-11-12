package com.example.inschool;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    EditText edt_uid,edt_fname,edt_lastname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "user_db").allowMainThreadQueries().build();
        edt_uid=findViewById(R.id.edt_uid);
        edt_fname=findViewById(R.id.edt_firstname);
        edt_lastname=findViewById(R.id.edt_lastname);
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User u=new User(Integer.valueOf(edt_uid.getText().toString()),edt_fname.getText().toString(),
                        edt_lastname.getText().toString());
                db.userDao().insertAll(u);
            }
        });
        findViewById(R.id.btn_read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> users=db.userDao().getAll();
                ((TextView)findViewById(R.id.tv_show)).setText(users.toString());
            }
        });
    }
}
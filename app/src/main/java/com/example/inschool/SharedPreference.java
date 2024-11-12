package com.example.inschool;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SharedPreference extends AppCompatActivity {
    EditText edt_usn, edt_pass;
    CheckBox cb;
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shared_preference);
edt_usn=findViewById(R.id.edt_username);
edt_pass=findViewById(R.id.edt_pass);
cb=findViewById(R.id.cb_remember);
sharedPreference=getSharedPreferences("account",MODE_PRIVATE); //for app
sharedPreference=getPreferences(MODE_PRIVATE);//only for current activity
        String usn=sharedPreference.getString("username","");
        String pass=sharedPreference.getString("password","");
        Boolean check=sharedPreference.getBoolean("isSaved",false);
        edt_usn.setText(usn);
        edt_pass.setText(pass);
        cb.setChecked(check);
findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(cb.isChecked()){
editor=sharedPreference.edit();
editor.putString("username",edt_usn.getText().toString());
editor.putString("password",edt_pass.getText().toString());
editor.putBoolean("isSaved",true);
editor.apply();
        }else{
            editor=sharedPreference.edit();
           editor.clear();
            editor.apply();
        }
    }
});
    }
}
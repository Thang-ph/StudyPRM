package com.example.inschool;
import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContentProvider extends AppCompatActivity {
ContentResolver resolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_content_provider);
requestContactPermission(Manifest.permission.READ_CONTACTS);
requestContactPermission(Manifest.permission.READ_PHONE_NUMBERS);
resolver=getContentResolver();
        Cursor c=resolver.query(ContactsContract.Contacts.CONTENT_URI,new String[]{ContactsContract.Contacts._ID},null,null,null);
   String result="";
    while(c.moveToNext()){
        int id_col=c.getColumnIndex(ContactsContract.Contacts._ID);
        int id=c.getInt(id_col);
        Cursor c1=resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        ,new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",new String[]{id+""},null);
       String phones="";
        while (c1.moveToNext()){
            int phone_col=c1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String phone=c1.getString(phone_col);
            phones+=phone+", ";
        }
        result+=id+": "+phones+"\n";
    }
        ((TextView)findViewById(R.id.tvContact)).setText(result);
    }
    public void requestContactPermission(String pms){
        if(checkSelfPermission(pms)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{pms},10);
        }
    }
}
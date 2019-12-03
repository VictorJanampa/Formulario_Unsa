package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class Main3Activity extends AppCompatActivity {

    TextInputLayout tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tv1=findViewById(R.id.edtCodigo);

    }
    public void escanear(View view) {
        Intent i = new Intent(this,Main4Activity.class);
        startActivityForResult(i,15);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 15 && resultCode == RESULT_OK){
            String barcode = data.getStringExtra("value");
            Toast.makeText(this, barcode, Toast.LENGTH_SHORT).show();
            tv1.getEditText().setText(barcode);
        }
    }
}

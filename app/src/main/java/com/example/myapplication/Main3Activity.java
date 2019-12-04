package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class Main3Activity extends AppCompatActivity {
    final int COD_SELECCIONA=10;
    final int COD_FOTO=20;
    ImageView imageView;
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

    public void cargarImagen(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la aplicacion"),COD_SELECCIONA);
    }

    public void takePicture(View view){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,COD_FOTO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                case COD_SELECCIONA:
                    Uri miPath = data.getData();
                    imageView.setImageURI(miPath);
                    break;

                case COD_FOTO:
                    //Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
                    Bundle ext = data.getExtras();
                    Bitmap bitmap = (Bitmap)ext.get("data");
                    imageView.setImageBitmap(bitmap);
                    break;

                case 15:
                    String barcode = data.getStringExtra("value");
                    Toast.makeText(this, barcode, Toast.LENGTH_SHORT).show();
                    tv1.getEditText().setText(barcode);
                    break;
            }
        }
    }

}

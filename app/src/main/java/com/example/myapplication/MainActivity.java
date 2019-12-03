package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    TextInputLayout email,pass;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissions(new String []{WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE,CAMERA},100);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.edtEmail);
        pass = findViewById(R.id.edtPassword);
        //final String url ="https://api.github.com/users/Evin1-/repos";
        //final String url="https://reqres.in/api/login";
        final String url="http://192.168.43.222/validar_usuario.php";
        btn = findViewById(R.id.btnLogin);


        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                String e = email.getEditText().getText().toString().trim();
                String p = pass.getEditText().getText().toString().trim();

                JSONObject json = new JSONObject();
                try {
                    json.put("Email",e);
                    json.put("Password",p);
                }catch (JSONException error){
                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                }


                OkHttpClient client=new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"),json.toString()))
                        .build();
                Toast.makeText(MainActivity.this, json.toString() , Toast.LENGTH_LONG).show();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()){
                            final String myResponse = response.body().string();
                            Log.e("MainActivity", "onResponse: " + response.body() );


                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, myResponse, Toast.LENGTH_SHORT).show();

                                }
                            });


                        }

                    }

                });
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==100){
            if(grantResults.length==3 && grantResults[0]== PackageManager.PERMISSION_GRANTED
                    && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permisos activados",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Sin permisos",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
                //


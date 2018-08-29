package com.example.pooria.myapplication.rest.readposts.SignUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pooria.myapplication.R;
import com.example.pooria.myapplication.model.SignUpModel;
import com.example.pooria.myapplication.rest.readposts.getposts.GetPosts;
import com.example.pooria.myapplication.rest.readposts.getposts.MainScreenActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    private EditText editText_email, editText_password;
    private Button button_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        bind();

    }
    void bind() {
        editText_email = findViewById(R.id.edt_email);
        editText_password = findViewById(R.id.edt_password);
        button_insert = findViewById(R.id.btn_save);
    }

    public void Ongo(View view) {
        OkHttpClient client = new OkHttpClient();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.254/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        String email = editText_email.getText().toString();
        String password = editText_password.getText().toString();
        GetPosts user = retrofit.create(GetPosts.class);
        Call<SignUpModel>call =user.signup(email,password);
        call.enqueue(new Callback<SignUpModel>() {
            @Override
            public void onResponse(Call<SignUpModel> call, Response<SignUpModel> response) {
                //Toast.makeText(SignUpActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                Log.d("log", response.toString());
                Intent intent = new Intent(SignUpActivity.this, MainScreenActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<SignUpModel> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("log", t.toString());
            }
        });
    }
}

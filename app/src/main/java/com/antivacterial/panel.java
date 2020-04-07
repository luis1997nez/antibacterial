package com.antivacterial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.antivacterial.Interface.Api;
import com.antivacterial.Model.Posts;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class panel extends AppCompatActivity {
    private TextView nombre;
    private TextView estado;
    private TextView usos;
    private TextView resultado;
    private Switch interruptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);
        /*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://http://silvermoonmx.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);*/

        nombre = findViewById(R.id.idApp);
        estado = findViewById(R.id.idEstado);
        usos = findViewById(R.id.idUsos);
        interruptor = findViewById(R.id.idSwitch);
        resultado = findViewById(R.id.idResultado);
        getPosts();
        //updatePost(String estado);
    }

    private void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://silvermoonmx.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Posts>> call = api.getPosts();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(!response.isSuccessful()){
                    resultado.setText("Codigo: " + response.code());
                    return;
                }

                List<Posts> postsList = response.body();
                for(Posts post: postsList){
                    String content = "";
                    String content2 = "";
                    String content3 = "";

                    content += response.code();
                    content += post.getName();
                    content2 += post.getDescription();
                    content3 += post.getTimes();

                    resultado.append(content);
                    nombre.append(content);
                    estado.append(content2);
                    usos.append(content3);

                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                resultado.setText("Codigo: " + t.getMessage());
            }
        });

    }


    public void onclick(View view) {
        String estados;
        if(view.getId()==R.id.idSwitch){
            if(interruptor.isChecked()){
                estados = "Encendido";
                updatePost(estados);

            }else{
                estados = "Apagado";
                updatePost(estados);
            }
        }
    }



    //PUT y PATCH
    private void updatePost(String estados){

        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://silvermoonmx.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);

        Posts post = new Posts(estados);

        Call<Posts> call = api.putPost(1, post);

        //Call<Posts> call = api.patchPost(5, post);

        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {


                Posts postResponse = response.body();

                /*

                String content = "";
                String content2 = "";
                String content3 = "";
                content += postResponse.getName();
                content2 += postResponse.getDescription();
                content3 += postResponse.getTimes();
                nombre.setText(content);
                estado.setText(content2);
                usos.setText(content3);*/

            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {

            }
        });

    }


}

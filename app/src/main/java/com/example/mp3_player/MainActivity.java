package com.example.mp3_player;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int PERMISSAO_PARA_ACESSAR_ARQUIVOS=1;
    RecyclerView recyclerView;
    PlaylistAdapter adapter;

    String musicas[]={"Lonely","Batom de Cereja","Smack That","Parabens Pra Você","O Pai ta On","Tipo Neymar","Santo Espírito","Pra Onde Eu Irei","Drip da Roça","Um dia Azul","Olha pro Oclin"
        ,"Lonely","Batom de Cereja","Smack That","Parabens Pra Você","O Pai ta On","Tipo Neymar","Santo Espírito","Pra Onde Eu Irei","Drip da Roça","Um dia Azul","Olha pro Oclin"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JcPlayerView player = (JcPlayerView) findViewById(R.id.jcplayerView);

        ArrayList<JcAudio> jcAudios = new ArrayList<>();
        jcAudios.add(JcAudio.createFromURL("Test  - Juliodev","http://www.villopim.com.br/android/Music_02.mp3"));

        player.initPlaylist(jcAudios, null);

        recyclerView=findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new PlaylistAdapter(this,musicas);

        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onResume() {super.onResume();

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

        }
        else {
            requestStoragePermission();
        }

        }

         private void requestStoragePermission(){

        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){

            new AlertDialog.Builder(this)
                .setTitle("Permissão Necessária")
                .setMessage("Essa permissão é necessária para acessar seus arquivos MP3 e reproduzir as músicas")



                 //caso positivo
                .setPositiveButton("ACEITAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSAO_PARA_ACESSAR_ARQUIVOS);
                    }
                })


                 //caso negativo
                .setNegativeButton("NEGAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                //mostrar ao usuario a caixa de pergunta
                .create().show();

        }



        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSAO_PARA_ACESSAR_ARQUIVOS);{
             }
    }

}

}
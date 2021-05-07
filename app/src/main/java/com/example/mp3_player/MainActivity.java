package com.example.mp3_player;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
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

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.nio.file.Path;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static Object ArquivosMP3;
    private int PERMISSAO_PARA_ACESSAR_ARQUIVOS = 1;
    RecyclerView recyclerView;
    PlaylistAdapter adapter;
    ArrayList<ArquivosMP3> arquivosMP3;
    JcPlayerView jcPlayerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       jcPlayerView = (JcPlayerView) findViewById(R.id.jcplayerView);


        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            adapter = new PlaylistAdapter(this, pegar_As_Musicas(this), new PlaylistAdapter.OnClickMusicaDoJulio() {
                @Override
                public void cliqueiTomaOPath(String path) {

                    JcAudio jcaudio =JcAudio.createFromFilePath(path);
                    jcPlayerView.addAudio(jcaudio);

                    jcPlayerView.playAudio(jcaudio);
                }

            });

            recyclerView.setAdapter(adapter);


        } else {
          requestStoragePermission();

        }

    }

    private void requestStoragePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permissão Necessária")
                    .setMessage("Essa permissão é necessária para acessar seus arquivos MP3 e reproduzir as músicas")


                    //caso positivo
                    .setPositiveButton("ACEITAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSAO_PARA_ACESSAR_ARQUIVOS);
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

    }

    public  ArrayList<ArquivosMP3> pegar_As_Musicas(Context context) {


        ArrayList<ArquivosMP3> tempAudioList = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] componentesDaMusica = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA
        };

        Cursor cursor = context.getContentResolver().query(uri, componentesDaMusica,
                null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String path = cursor.getString(3);
                String titulo = cursor.getString(0);
                String cantor = cursor.getString(2);
                String duração = cursor.getString(1);

                ArquivosMP3 arquivosMP3 = new ArquivosMP3(path, titulo, cantor, duração);
                tempAudioList.add(arquivosMP3);
            }
            cursor.close();
        }

        return tempAudioList;
    }
}
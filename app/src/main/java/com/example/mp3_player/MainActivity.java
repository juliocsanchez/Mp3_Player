package com.example.mp3_player;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
PlaylistAdapter adapter;

String musicas[]={"Lonely","Batom de Cereja","Smack That","Parabens Pra Você","O Pai ta On","Tipo Neymar","Santo Espírito","Pra Onde Eu Irei","Drip da Roça","Um dia Azul","Olha pro Oclin"
        ,"Lonely","Batom de Cereja","Smack That","Parabens Pra Você","O Pai ta On","Tipo Neymar","Santo Espírito","Pra Onde Eu Irei","Drip da Roça","Um dia Azul","Olha pro Oclin"};

String Artistas[]={"Gustavo Lima","Drake","Gustavo Lima","Drake","Gustavo Lima","Drake","Gustavo Lima","Drake","Gustavo Lima","Drake","Gustavo Lima","Drake","Gustavo Lima","Drake",
        "Gustavo Lima","Drake","Gustavo Lima","Drake"};

String tempo[]={"3","4:20","3","4:20","3","4:20","3","4:20","3","4:20","3","4:20","3","4:20","3","4:20","3","4:20","3","4:20","3","4:20",};

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
    protected void onResume() {
        super.onResume();
    }

}
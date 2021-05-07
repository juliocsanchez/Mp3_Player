package com.example.mp3_player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder> {

    ArrayList<ArquivosMP3> data;
    Context context;
    OnClickMusicaDoJulio onClickMusicaDoJulio;

    interface OnClickMusicaDoJulio {
        void cliqueiTomaOPath(String path);
    }


    public PlaylistAdapter(Context context, ArrayList<ArquivosMP3> data, OnClickMusicaDoJulio onClickMusicaDoJulio) {

        this.data = data;
        this.context = context;
        this.onClickMusicaDoJulio = onClickMusicaDoJulio;
    }


    @NonNull
    @Override

    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_playlist, parent, false);
        PlaylistViewHolder playlistViewHolder = new PlaylistViewHolder(view);
        return playlistViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistAdapter.PlaylistViewHolder holder, int position) {

        ArquivosMP3 musicaAtual = data.get(position);
        holder.icone_musica.setText(musicaAtual.getTitulo());
        holder.nome_musica.setText(musicaAtual.getTitulo());
        holder.minutos_musica.setText(musicaAtual.getDuração());


        holder.icone_musica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickMusicaDoJulio.cliqueiTomaOPath(musicaAtual.getPath());

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PlaylistViewHolder extends RecyclerView.ViewHolder {

        TextView icone_musica;
        TextView nome_musica;
        TextView minutos_musica;

        public PlaylistViewHolder(@NonNull View itemView) {

            super(itemView);
            icone_musica = itemView.findViewById(R.id.icone_musica);
            nome_musica = itemView.findViewById(R.id.nome_musica);
            minutos_musica = itemView.findViewById(R.id.minutos_musica);

        }
    }
}

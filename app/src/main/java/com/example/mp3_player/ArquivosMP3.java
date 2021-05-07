package com.example.mp3_player;

public class ArquivosMP3 {
    private String path;
    private String titulo;
    private String cantor;
    private String duração;

    public ArquivosMP3(String path,String titulo,String cantor,String duração) {
        this.path = path;
        this.titulo=titulo;
        this.cantor=cantor;
        this.duração=duração;
    }

    public ArquivosMP3() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCantor() {
        return cantor;
    }

    public void setCantor(String cantor) {
        this.cantor = cantor;
    }

    public String getDuração() {
        return duração;
    }

    public void setDuração(String duração) {
        this.duração = duração;
    }
}

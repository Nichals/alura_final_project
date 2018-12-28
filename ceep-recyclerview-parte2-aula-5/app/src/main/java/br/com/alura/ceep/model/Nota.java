package br.com.alura.ceep.model;

import android.content.Context;
import android.graphics.Color;

import java.io.Serializable;

import br.com.alura.ceep.R;

public class Nota implements Serializable{

    private final String titulo;
    private final String descricao;
    private final int corFundo;

    public Nota(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.corFundo = R.color.whiteColorPicker;
    }
    public Nota(String titulo, String descricao, int cor) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.corFundo = cor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCorFundo() {
        return corFundo;
    }
}
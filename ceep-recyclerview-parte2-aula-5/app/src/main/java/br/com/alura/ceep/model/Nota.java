package br.com.alura.ceep.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

import br.com.alura.ceep.R;

public class Nota implements Serializable, Comparable<Nota>{

    private Integer id;
    private String titulo;
    private String descricao;
    private int corFundo;
    private int posicao;

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
    public Nota(Integer id, String titulo, String descricao, int cor) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.corFundo = cor;
    }
    public Nota(){

    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCorFundo(int corFundo) {
        this.corFundo = corFundo;
    }

    @Override
    public int compareTo(Nota nota) {
        if (this.posicao < nota.getPosicao()) {
            return -1;
        }
        if (this.posicao > nota.getPosicao()) {
            return 1;
        }
        return 0;
    }


}
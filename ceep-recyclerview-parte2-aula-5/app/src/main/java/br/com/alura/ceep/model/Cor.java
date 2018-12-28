package br.com.alura.ceep.model;

import android.graphics.drawable.Drawable;

import br.com.alura.ceep.R;

public class Cor {
    private int cor;


    public Cor(int cor) {
        this.cor = cor;

    }
    public Cor() {
        this.cor = R.color.whiteColorPicker;
    }


    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

}

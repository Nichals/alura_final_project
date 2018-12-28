package br.com.alura.ceep.model;

import android.graphics.drawable.Drawable;

import br.com.alura.ceep.R;

public class Cor {
    private int cor;
    private int background;

    public Cor(int cor, int background) {
        this.cor = cor;
        this.background = background;
    }
    public Cor() {
        this.cor = R.color.whiteColorPicker;
        background =  R.drawable.circulo_color_picker;
    }


    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}

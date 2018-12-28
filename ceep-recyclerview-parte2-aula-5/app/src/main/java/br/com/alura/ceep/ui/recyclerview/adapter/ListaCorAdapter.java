package br.com.alura.ceep.ui.recyclerview.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.model.Cor;
import br.com.alura.ceep.ui.recyclerview.adapter.listener.ListaCorListener;

public class ListaCorAdapter extends RecyclerView.Adapter<ListaCorAdapter.CorViewHolder> {

    private final Context context;
    private final List<Cor> listaCor;
    private ListaCorListener listener;

    public ListaCorAdapter(Context context, List<Cor> listaCor, ListaCorListener listener) {
        this.context = context;
        this.listaCor = listaCor;
        this.listener = listener;
    }

    @Override
    public CorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.item_color_picker, parent, false);
        return new CorViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(CorViewHolder holder, int position) {
        Cor cor = listaCor.get(position);
        holder.vincula(cor);
    }

    @Override
    public int getItemCount() {
        return listaCor.size();
    }

    class CorViewHolder extends RecyclerView.ViewHolder{


        private Cor cor;

        public CorViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(cor);
                }
            });
        }

        public void vincula(Cor cor){
            this.cor = cor;
            Drawable background = itemView.getBackground();
            if (background instanceof ShapeDrawable) {
                // cast to 'ShapeDrawable'
                ShapeDrawable shapeDrawable = (ShapeDrawable) background;
                shapeDrawable.getPaint().setColor(ContextCompat.getColor(itemView.getContext(),cor.getCor()));
            } else if (background instanceof GradientDrawable) {
                // cast to 'GradientDrawable'
                GradientDrawable gradientDrawable = (GradientDrawable) background;
                gradientDrawable.setColor(ContextCompat.getColor(itemView.getContext(),cor.getCor()));
            } else if (background instanceof ColorDrawable) {
                // alpha value may need to be set again after this call
                ColorDrawable colorDrawable = (ColorDrawable) background;
                colorDrawable.setColor(ContextCompat.getColor(itemView.getContext(),cor.getCor()));
            }
        }
    }

}



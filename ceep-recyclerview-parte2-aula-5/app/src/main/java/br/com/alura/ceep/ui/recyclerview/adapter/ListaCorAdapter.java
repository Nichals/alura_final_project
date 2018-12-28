package br.com.alura.ceep.ui.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.zip.Inflater;

import br.com.alura.ceep.R;
import br.com.alura.ceep.model.Cor;

public class ListaCorAdapter extends RecyclerView.Adapter<CorViewHolder> {

    private final Context context;
    private final List<Cor> listaCor;

    public ListaCorAdapter(Context context, List<Cor> listaCor) {
        this.context = context;
        this.listaCor = listaCor;
    }

    @Override
    public CorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.item_color_picker, parent, false);
        return new CorViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(CorViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listaCor.size();
    }
}

class CorViewHolder extends RecyclerView.ViewHolder{

    public CorViewHolder(View itemView) {
        super(itemView);
    }
}

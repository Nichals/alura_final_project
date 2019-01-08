package br.com.alura.ceep.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NotaDAO;
import br.com.alura.ceep.model.Cor;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.ui.recyclerview.adapter.ListaCorAdapter;
import br.com.alura.ceep.ui.recyclerview.adapter.listener.ListaCorListener;

import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.CHAVE_NOTA;
import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.CHAVE_POSICAO;
import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.POSICAO_INVALIDA;

public class FormularioNotaActivity extends AppCompatActivity {


    public static final String TITULO_APPBAR_INSERE = "Insere nota";
    public static final String TITULO_APPBAR_ALTERA = "Altera nota";
    private int posicaoRecibida = POSICAO_INVALIDA;
    private TextView titulo;
    private TextView descricao;
    private RecyclerView colorPickList;
    public static Cor cor;
    private List<Cor> listaCor;
    private ConstraintLayout layout;
    private Nota notaRecebida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);

        setTitle(TITULO_APPBAR_INSERE);
        inicializaCampos();
        inicializaCor();

        Intent dadosRecebidos = getIntent();
        if(dadosRecebidos.hasExtra(CHAVE_NOTA)){
            setTitle(TITULO_APPBAR_ALTERA);
            notaRecebida = (Nota) dadosRecebidos
                    .getSerializableExtra(CHAVE_NOTA);
            posicaoRecibida = dadosRecebidos.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
            cor = new Cor(notaRecebida.getCorFundo());
            preencheCampos(notaRecebida);
        }

    }

    private void inicializaCor() {
        Cor corBranca = new Cor(R.color.whiteColorPicker);
        listaCor.add(corBranca);
        Cor corAzul = new Cor(R.color.blueColorPicker);
        listaCor.add(corAzul);
        Cor corVermelho = new Cor(R.color.redColorPicker);
        listaCor.add(corVermelho);
        Cor corVerde = new Cor(R.color.greenColorPicker );
        listaCor.add(corVerde);
        Cor corAmarelo = new Cor(R.color.yellowColorPicker);
        listaCor.add(corAmarelo);
        Cor corLilas = new Cor(R.color.pinkColorPicker);
        listaCor.add(corLilas);
        Cor corCinza = new Cor(R.color.greyColorPicker);
        listaCor.add(corCinza);
        Cor corMarrom = new Cor(R.color.brownColorPicker);
        listaCor.add(corMarrom);
        Cor corRoxo = new Cor(R.color.purpleColorPicker);
        listaCor.add(corRoxo);
    }

    private void preencheCampos(Nota notaRecebida) {
        titulo.setText(notaRecebida.getTitulo());
        descricao.setText(notaRecebida.getDescricao());
        layout.setBackgroundColor(ContextCompat.getColor(this,notaRecebida.getCorFundo()));
    }

    private void inicializaCampos() {
        titulo = findViewById(R.id.formulario_nota_titulo);
        descricao = findViewById(R.id.formulario_nota_descricao);
        layout = findViewById(R.id.formulario_backgroud);
        listaCor = new ArrayList<>();
        cor = new Cor();
        colorPickList = findViewById(R.id.formulario_colorpicker_recyclerview);
        colorPickList.setAdapter(new ListaCorAdapter(this, listaCor, new ListaCorListener() {
            @Override
            public void onItemClick(Cor cor) {
                FormularioNotaActivity.cor = cor;
                layout.setBackgroundColor(ContextCompat.getColor(FormularioNotaActivity.this,cor.getCor()));
            }
        }));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        colorPickList.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_nota_salva, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(ehMenuSalvaNota(item)){
            Nota notaCriada = criaNota();
            List<Nota> todos = new NotaDAO(this).todos();
            if(todos.size() > 0 && notaRecebida == null){
                Nota ultimaNota = todos.get(todos.size()-1);
                notaCriada.setPosicao(ultimaNota.getPosicao()+1);
            }else if(todos != null && todos.size() == 0 && notaRecebida == null){
                notaCriada.setPosicao(0);
            }
            retornaNota(notaCriada);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void retornaNota(Nota nota) {
        Intent resultadoInsercao = new Intent();
        resultadoInsercao.putExtra(CHAVE_NOTA, nota);
        resultadoInsercao.putExtra(CHAVE_POSICAO, posicaoRecibida);
        setResult(Activity.RESULT_OK,resultadoInsercao);
    }

    @NonNull
    private Nota criaNota() {
        if(cor != null){
            if(notaRecebida != null){
                return new Nota(notaRecebida.getId(),titulo.getText().toString(),
                        descricao.getText().toString(), cor.getCor());
            }else{
                return new Nota( titulo.getText().toString(),
                        descricao.getText().toString(), cor.getCor());
            }

        }else{
            return new Nota(titulo.getText().toString(),
                    descricao.getText().toString(), new Cor().getCor());
        }

    }

    private boolean ehMenuSalvaNota(MenuItem item) {
        return item.getItemId() == R.id.menu_formulario_nota_ic_salva;
    }

}

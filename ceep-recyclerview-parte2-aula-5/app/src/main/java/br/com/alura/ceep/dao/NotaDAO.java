package br.com.alura.ceep.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import br.com.alura.ceep.model.Nota;

public class NotaDAO extends SQLiteOpenHelper {

    private final static ArrayList<Nota> notas = new ArrayList<>();

    public NotaDAO(@Nullable Context context) {
        super(context,"ceep", null, 1);
    }

    public List<Nota> todos() {
        String sql = "SELECT * FROM Notas;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Nota> notas = new ArrayList<Nota>();
        while (c.moveToNext()) {
            Nota nota = new Nota();
            nota.setId(c.getInt(c.getColumnIndex("id")));
            nota.setTitulo(c.getString(c.getColumnIndex("titulo")));
            nota.setCorFundo(c.getInt(c.getColumnIndex("corFundo")));
            nota.setDescricao(c.getString(c.getColumnIndex("descricao")));
            nota.setPosicao(c.getInt(c.getColumnIndex("posicao")));

            notas.add(nota);

        }
        c.close();
        Collections.sort(notas);
        return notas;

    }

    public void insere(Nota nota) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("titulo", nota.getTitulo());
        dados.put("descricao", nota.getDescricao());
        dados.put("corFundo", nota.getCorFundo());
        dados.put("posicao", nota.getPosicao());

        db.insert("Notas", null, dados );
    }

    public void altera(Nota nota) {
//        notas.set(posicao, nota);
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDaNota(nota);

        String[] params ={String.valueOf(nota.getId())};
        db.update("Notas", dados, "id = ?", params);
    }

    private ContentValues pegaDadosDaNota(Nota nota) {
        ContentValues dados = new ContentValues();
        dados.put("titulo", nota.getTitulo());
        dados.put("corFundo", nota.getCorFundo());
        dados.put("descricao", nota.getDescricao());
        dados.put("posicao", nota.getPosicao());
        return dados;
    }

    public void remove(Nota nota) {
        SQLiteDatabase db = getWritableDatabase();

        String [] params = {String.valueOf(nota.getId())};
        db.delete("Notas", "id = ?", params);
    }

    public void troca(int posicaoInicio, int posicaoFim) {
        List<Nota> notaList = todos();
        Collections.swap(notaList, posicaoInicio, posicaoFim);
        for(int i = 0; i< notaList.size(); i++){
            Nota nota = notaList.get(i);
            nota.setPosicao(i);
            altera(nota);
        }
    }

    public void removeTodos() {
        notas.clear();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE Notas (id INTEGER PRIMARY KEY, titulo TEXT NOT NULL, descricao TEXT, corFundo INTEGER, posicao INTEGER);";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

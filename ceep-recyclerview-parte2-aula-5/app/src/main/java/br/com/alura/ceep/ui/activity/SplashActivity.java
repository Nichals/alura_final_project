package br.com.alura.ceep.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import br.com.alura.ceep.R;

public class SplashActivity extends AppCompatActivity {

    private static final String PRIMEIRO_LOGIN = "PRIMEIRO_LOGIN";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferences = getSharedPreferences(PRIMEIRO_LOGIN, this.MODE_PRIVATE);
        String primeiroLogin = sharedPreferences.getString(PRIMEIRO_LOGIN,"");
        if("".equals(primeiroLogin)){
            salvaPrimeiroLogin();
            aguardar(2000);
        }else{
            aguardar(500);
        }
    }

    private void aguardar(int i) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, ListaNotasActivity.class);
                startActivity(intent);
                finish();
            }
        }, i);
    }

    private void salvaPrimeiroLogin() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PRIMEIRO_LOGIN, PRIMEIRO_LOGIN);
        editor.apply();
    }


}

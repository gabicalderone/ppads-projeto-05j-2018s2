package br.mackenzie.easymarket;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import br.mackenzie.easymarket.BD.Arquivo;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Arquivo arq = new Arquivo();
        arq.criarPasta();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent login = new Intent(SplashScreen.this, Login.class);
                startActivity(login);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}

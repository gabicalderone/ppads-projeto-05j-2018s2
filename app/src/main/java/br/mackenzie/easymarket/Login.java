package br.mackenzie.easymarket;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

import br.mackenzie.easymarket.modelo.Item;

public class Login extends AppCompatActivity {

    // variaveis de controle do BD
    // private SQLiteDatabase conection;

    private FileOutputStream OS;
    private FileInputStream IS;
    private InputStreamReader IR;
    private BufferedReader BR;

    private String fileName = "USERS";
    private String linha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Teste conexao
        //createConection();

        // Definindo os botões
        Button btLogin = findViewById(R.id.bLogin);
        Button btRegister = findViewById(R.id.bRegister);

        // Ação do Botão Logar
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Chamada do método das ações de Logar
                LogarOnClick();
            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(Login.this, Register.class);
                startActivity(register);
            }
        });
    }
    private void LogarOnClick(){
        // Pegando as informações digitadas
        TextView eUser = findViewById(R.id.eUser);
        TextView ePass = findViewById(R.id.ePass);

        // Convertendo as informações para as variaveis
        String user = eUser.getText().toString();
        String password = ePass.getText().toString();

        //Teste da verificação do Login (SEM BD ainda)
        if(user.equals("Testador")&&password.equals("testando")){
            alert("Login efetuado com sucesso");
            Intent principal = new Intent(Login.this, MainActivity.class);
            startActivity(principal);
            finish();
        }

        if (verification(user, password)){
            alert("Login efetuado com sucesso");
            MainActivity.setUser(user);
            Intent principal = new Intent(Login.this, MainActivity.class);
            startActivity(principal);
            finish();
        }
        else{
            alert("Usuario e/ou senha incorretos");
        }
        //Fim do Teste de Verificação
    }

    private boolean verification(String user, String pass){
        boolean existe = false;
        try {
            IS = openFileInput(fileName);
            IR = new InputStreamReader(IS);
            BR = new BufferedReader(IR);
            while ((linha = BR.readLine()) != null){
                String[] separate = linha.split(Pattern.quote(":"));
                String[] verificado = separate[1].split(Pattern.quote("/"));
                String userNam = verificado[0];
                String passNam = verificado[1];
                if(user.equals(userNam) && pass.equals(passNam)){
                    Logged(separate[0],separate[1]);
                    existe = true;
                }
            }
            IS.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return existe;
    }

    // Conexão com o Banco de Dados (TESTE SQLite)
    /*private void createConection(){
        try{
            OpenHelper = new EasyMarketOpenHelper(this);
            conection = OpenHelper.getWritableDatabase();
            alert("conexão com BD feita");

        }catch(SQLException ex){
            alert("conexão com BD falhou!");
        }
    }*/

    // Metodo para a mensagem na tela
    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
    private void Logged (String name, String mail){
        try {
            OS = openFileOutput("LOG", Context.MODE_PRIVATE);
            String envio = name + "/" + mail +"\n";
            OS.write(envio.getBytes());
            OS.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

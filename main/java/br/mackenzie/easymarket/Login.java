package br.mackenzie.easymarket;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.mackenzie.easymarket.database.EasyMarketOpenHelper;

public class Login extends AppCompatActivity {

    // variaveis de controle do BD
    private SQLiteDatabase conection;
    private EasyMarketOpenHelper OpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Teste conexao
        createConection();

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
            Intent principal = new Intent(Login.this, Principal.class);
            startActivity(principal);
            finish();
        }
        else{
            alert("Usuario e/ou senha incorretos");
        }
        //Fim do Teste de Verificação
    }

    // Conexão com o Banco de Dados (TESTE SQLite)
    private void createConection(){
        try{
            OpenHelper = new EasyMarketOpenHelper(this);
            conection = OpenHelper.getWritableDatabase();
            alert("conexão com BD feita");

        }catch(SQLException ex){
            alert("conexão com BD falhou!");
        }
    }

    // Metodo para a mensagem na tela
    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}

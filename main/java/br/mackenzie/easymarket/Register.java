package br.mackenzie.easymarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btRegister = (Button) findViewById(R.id.bRegisterC);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pegar as informações digitadas
                TextView eName = (TextView) findViewById(R.id.eName);
                TextView eMail = (TextView) findViewById(R.id.eMail);
                TextView ePhone = (TextView) findViewById(R.id.ePhone);
                TextView eUser = (TextView) findViewById(R.id.eUserC);
                TextView ePass = (TextView) findViewById(R.id.ePassC);
                TextView eConfPass = (TextView) findViewById(R.id.eConfPassC);

                // Converter as Informações para Variáveis
                String name = eName.getText().toString();
                String mail = eMail.getText().toString();
                String phone = ePhone.getText().toString();
                String user = eUser.getText().toString();
                String password = ePass.getText().toString();
                String conf_password = eConfPass.getText().toString();

                // Redirecionar para a Tela Principal
                Intent principal = new Intent(Register.this, Principal.class);
                startActivity(principal);
            }
        });
    }
}

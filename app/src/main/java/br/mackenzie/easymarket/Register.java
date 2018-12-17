package br.mackenzie.easymarket;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.regex.Pattern;

import br.mackenzie.easymarket.communication.ZipCodeListener;
import br.mackenzie.easymarket.dialogs.Util;
import br.mackenzie.easymarket.modelo.Address;

public class Register extends AppCompatActivity {

    private EditText etZipCode;
    private Util util;

    private FileOutputStream OS;

    private String fileName = "USERS";
    private String salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etZipCode = (EditText) findViewById(R.id.et_zip_code);
        etZipCode.addTextChangedListener(new ZipCodeListener(this));

        Spinner spStates = (Spinner) findViewById(R.id.sp_states);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this,R.array.states,android.R.layout.simple_spinner_item);
        spStates.setAdapter(adapter);

        util = new Util(this,
                R.id.iRua,
                R.id.iCom,
                R.id.iBairro,
                R.id.iCidade,
                R.id.sp_states);

        Button btRegister = (Button) findViewById(R.id.bRegisterC);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Pegar as informações digitadas
                EditText eName = (EditText) findViewById(R.id.iName);
                EditText eMail = (EditText) findViewById(R.id.iMail);
                EditText ePhone = (EditText) findViewById(R.id.iPhone);
                EditText eUser = (EditText) findViewById(R.id.iUser);
                EditText ePass = (EditText) findViewById(R.id.iSenha);
                EditText eConfPass = (EditText) findViewById(R.id.iConfSenha);

                // Converter as Informações para Variáveis
                String name = eName.getText().toString();
                String mail = eMail.getText().toString();
                String phone = ePhone.getText().toString();
                String user = eUser.getText().toString();
                String password = ePass.getText().toString();
                String conf_password = eConfPass.getText().toString();

                // Verificação nome vazio
                if (isVoid(name) || isVoid(mail) || isVoid(phone) || isVoid(user) ||
                        isVoid(password) || isVoid(conf_password)){
                    alert("Nenhum campo pode estar vazio");
                }else if(!verifyMail(mail)){
                    alert("Email inválido!");
                }else if (!verifyPhone(phone)){
                    alert("Telefone digitado inválido, verifique o numero e o DDD");
                }else if(!verifyUser(user)){
                    alert("Usuário inválido, digite um nome com no minimo 8 caracteres");
                }else if(!verifyPass(password, conf_password)){
                    alert("Senhas não batem ou não possuem 8 digitos");
                }else{

                    // Salvar dados no BD
                    saveUser(name,mail,phone,user,password);

                    // Criando Usuário no APP
                    Logged(user, name, mail);

                    // Redirecionar para a Tela Principal
                    MainActivity.setUser(user);
                    Intent principal = new Intent(Register.this, MainActivity.class);
                    startActivity(principal);
                    finish();
                }
            }
        });
    }

    private void saveUser(String name, String mail, String phone, String user, String password){
        try {
            OS = openFileOutput(fileName, Context.MODE_APPEND);
            salvar = name + "/" + mail + "/" + phone + ":" + user +"/"+password+"\n";
            OS.write(salvar.getBytes());
            OS.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void Logged (String user, String name, String mail){
        try {
            OS = openFileOutput("LOG", Context.MODE_PRIVATE);
            String envio = user + "/" + name + "/" + mail +"\n";
            OS.write(envio.getBytes());
            OS.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean isVoid(String campo){
        if(campo.length() == 0){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean verifyPass(String pass, String conf){
        if(pass.equals(conf)){
            if (pass.length()<8){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    private boolean verifyUser(String user){
        if(user.length()<8){
            return false;
        }else{
            return true;
        }
    }

    private boolean verifyMail(String mail){
        String[] antes = mail.split(Pattern.quote("@"));
        String[] depois = antes[1].split(Pattern.quote("."));
        int tamanhoA = antes.length;
        int tamanhoD = depois.length;
        if(tamanhoA != 2){
            return false;
        }
        else if(tamanhoD < 2 || tamanhoD > 3){
            return false;
        }
        else{
            return true;
        }
    }

    private boolean verifyPhone(String num){
        if(num.length()>= 10 && num.length()<= 11){
            return true;
        }else{
            return false;
        }
    }

    private String getZipCode(){
        return etZipCode.getText().toString();
    }

    public String getUriRequest(){
        return "https://viacep.com.br/ws/"+getZipCode()+"/json/";
    }

    public void lockFields(boolean isToLock){
        util.lockFields(isToLock);
    }

    public void searchZipCode(View view){
        /* TODO */
    }

    public void setAddressFields(Address address){
        setField(R.id.iRua, address.getLogradouro());
        setField(R.id.iCom, address.getComplemento());
        setField(R.id.iBairro, address.getBairro());
        setField(R.id.iCidade, address.getLocalidade());
        setSpinner(R.id.sp_states,R.array.states,address.getUf());
    }

    private void setField(int fieldld, String data){
        ((EditText) findViewById(fieldld)).setText(data);
    }

    private void setSpinner(int fieldld, int arrayID, String UF){
        Spinner spinner = (Spinner) findViewById(fieldld);
        String[] states = getResources().getStringArray(arrayID);

        for(int i = 0; i<states.length; i++){
            if(states[i].endsWith("("+UF+")")){
                spinner.setSelection(i);
                break;
            }
        }
    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}

package br.mackenzie.easymarket;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.mackenzie.easymarket.adapter.AdapterProduct;
import br.mackenzie.easymarket.modelo.Product;

public class NewList extends AppCompatActivity{
    private ArrayList<Product> products;
    private ListView listView;
    private Button btnSaveList;
    private File diretorio;
    private String nomeDiretorio;
    private String diretorioApp;
    private String nome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        List<Product> produtos = null;

        ListView listaDeProdutos = (ListView) findViewById(R.id.listaProdutos);

        EditText nomeLista = (EditText) findViewById(R.id.eNomeLista);
        nome = nomeLista.getText().toString();

        //chamada do adapter
        AdapterProduct adapter = new AdapterProduct(produtos, this);

        listaDeProdutos.setAdapter(adapter);

        btnSaveList = (Button) findViewById(R.id.btnSalvar);
        clickSaveList();
    }
    private void clickSaveList(){
        btnSaveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(listView != null){
                        String nomeDiretorio = "EasyMarketLists";
                        String  diretorioApp = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/"+nomeDiretorio+"/";
                        File fileExt = new File(diretorioApp, nome);
                        fileExt.getParentFile().mkdirs();
                        FileOutputStream fosExt = null;
                        fosExt = new FileOutputStream(fileExt);
                        Adapter adapter = (Adapter) listView.getAdapter();
                        String texto = "";
                        for(int i = 0; i < adapter.getCount(); i++){
                            Product produtos = (Product) adapter.getItem(i);
                            texto += produtos.getNome()+" "+produtos.isChecked()+" /n";

                        }
                        fosExt.write(texto.getBytes());
                        fosExt.close();
                        //Toast.makeText(NewList.this, texto, Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){

                }
            }
        });
    }
}

package br.mackenzie.easymarket;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import br.mackenzie.easymarket.adapter.ItemAdapter;
import br.mackenzie.easymarket.dialogs.AddDialog;
import br.mackenzie.easymarket.dialogs.CustomDialog;
import br.mackenzie.easymarket.modelo.Item;


public class MainActivity extends AppCompatActivity implements CustomDialog.OnChooseOption, AddDialog.OnChooseOption{

    private static final String TAG = "MainActivity";//para testes Log.i

    private static String user;

    private RecyclerView recycler;

    private Button btMostrarImportante;
    private Button btOrdenar;
    private Button btAdd;

    private int ij;

    private List<Item> lista;
    private List<Item> listaCarregada;
    private ItemAdapter adapter;

    private boolean voltar;
    private String nome;
    private String usuario;
    private String email;



    private String fileName;
    private String fileTemp = "myListTemp";
    private String salvar;
    private String linha;
    private boolean importance;

    private File arquivo;

    private FileOutputStream OS;

    private FileInputStream IS;
    private InputStreamReader IR;
    private BufferedReader BR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ij = 0;
        setFile();
        loadItem();
        setUI();
        setRecyclerView();
        setActions();
    }

    public static void setUser(String user) {
        MainActivity.user = user;
    }

    public void setFile(){
        fileName = "myList_" + user;
        try {
            IS = openFileInput(fileName);
            IR = new InputStreamReader(IS);
            BR = new BufferedReader(IR);
            while ((linha = BR.readLine()) != null){
                String[] str = linha.split(Pattern.quote("/"));
                setUsuario(str[0]);
                nome = str[1];
                setEmail(str[2]);
            }
            IS.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadItem(){
        try {
            listaCarregada = new ArrayList<>();
            IS = openFileInput(fileName);
            IR = new InputStreamReader(IS);
            BR = new BufferedReader(IR);
            while ((linha = BR.readLine()) != null){
                String[] str = linha.split(Pattern.quote("/"));

                if (str[2] == "true"){
                    importance = true;
                }else{
                    importance = false;
                }

                String strNome = str[0];
                String strComent = str[1];
                listaCarregada.add(new Item(strNome, strComent, importance));
            }
            IS.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveItem(String nome, String coment){
        try {
            OS = openFileOutput(fileName, Context.MODE_APPEND);
            salvar = nome + "/" + coment + "/false" + "\n";
            OS.write(salvar.getBytes());
            OS.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void alterarItem(){
        try {
            OS = openFileOutput(fileName, Context.MODE_PRIVATE);
            listaCarregada = lista;
            String importancia = "false";
            for (Item i : lista){
                if (i.getNome() != "DICA!"){
                    if (i.isImportante()){
                        importancia = "true";
                    }
                    salvar = i.getNome()+"/"+i.getComentario()+"/"+importancia+"\n";
                    OS.write(salvar.getBytes());
                }
            }
            OS.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setUI(){
        recycler = (RecyclerView) findViewById(R.id.itens_recycler_view);
        btMostrarImportante = (Button) findViewById(R.id.bt_mostrar_importante);
        btOrdenar = (Button) findViewById(R.id.bt_ordenar);
        btAdd = (Button) findViewById(R.id.bt_add);
    }

    private void setRecyclerView(){
        lista = new ArrayList<>();
        lista.add(new Item("DICA!", "Toque DUAS VEZES em um item para mais opções.", true));
        if(!listaCarregada.isEmpty()) {
            for (Item item : listaCarregada) {
                lista.add(item);
            }
        }
        adapter = new ItemAdapter(lista);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layout);

        setting();

        recycler.setAdapter(adapter);
    }

    private void setting(){
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int position = recycler.getChildLayoutPosition(view);

                ij++ ;
                Handler handler = new Handler();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        ij = 0;
                    }
                };
                if(ij==1){
                    handler.postDelayed(r, 250);
                }else if(ij == 2){
                    Dialog dialog = new CustomDialog().showDialog(MainActivity.this, position,
                            lista.get(position).isImportante());
                    dialog.show();
                }


            }
        });
    }

    private void setActions(){
        btMostrarImportante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!voltar){
                    List<Item> tmpLista = new ArrayList<>();

                    for(Item item : lista){
                        if(item.isImportante()){
                            tmpLista.add(item);
                        }
                    }

                    adapter = new ItemAdapter(tmpLista);
                    recycler.setAdapter(adapter);
                    voltar = true;
                    btMostrarImportante.setText("Voltar");
                }else{
                    adapter = new ItemAdapter(lista);
                    setting();
                    recycler.setAdapter(adapter);
                    voltar =false;
                    btMostrarImportante.setText("Importante");
                }

            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(voltar){
                    adapter = new ItemAdapter(lista);
                    setting();
                    recycler.setAdapter(adapter);
                    voltar = false;
                }

                Dialog Add = new AddDialog().showDialog(MainActivity.this);
                Add.show();

            }
        });

        btOrdenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sorting
                Collections.sort(lista, new Comparator<Item>() {
                    @Override
                    public int compare(Item item2, Item item1)
                    {
                        return  item2.getNome().compareTo(item1.getNome());
                    }
                });

                adapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void onImportante(int position) {
        lista.get(position).setImportante(!lista.get(position).isImportante());
        alterarItem();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onExcluir(int position) {
        lista.remove(position);
        alterarItem();
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onConfirma(String nome, String coment) {
        lista.add(new Item(nome, coment, false));
        saveItem(nome,coment);
        adapter.notifyDataSetChanged();
    }

    public static String getTAG() {
        return TAG;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}



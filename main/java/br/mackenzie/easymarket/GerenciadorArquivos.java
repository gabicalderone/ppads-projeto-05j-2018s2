package br.mackenzie.easymarket;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GerenciadorArquivos{

    public void NovaLista (String nome){
        String nomeArquivo = nome;
        File arquivo = new File(Environment.getExternalStorageDirectory()+"/MinhasListas/"+nomeArquivo);
        String conteudo = (nome+"//");
    }

    public void InserirProduto(String Nome, String Tipo, String Lista){
        String nomeArquivo = Lista;
        File arquivo = new File(Environment.getExternalStorageDirectory()+"/MinhasListas/"+nomeArquivo);
        String conteudo = (Nome + "/" + Tipo);
        try{
            FileOutputStream salvar = new FileOutputStream(arquivo);
            salvar.write(conteudo.getBytes());
            salvar.close();
        }catch(FileNotFoundException e){
        } catch (IOException e){
        }


    }

    public void LerLista(String Lista){

    }
}

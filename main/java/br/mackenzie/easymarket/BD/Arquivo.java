package br.mackenzie.easymarket.BD;

import android.os.Environment;

import java.io.File;

public class Arquivo{
    private File diretorio;
    private String nomeDiretorio;
    private String diretorioApp;

    public void Arquivo(){
        nomeDiretorio = "EasyMarketLists";
    }
    public void criarPasta(){
        diretorioApp = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/"+nomeDiretorio+"/";
        diretorio = new File(diretorioApp);
        diretorio.mkdirs();
    }
}

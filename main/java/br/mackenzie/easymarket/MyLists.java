package br.mackenzie.easymarket;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

import static android.R.layout.simple_list_item_1;

public class MyLists extends AppCompatActivity {
    ListView listView;
    ArrayList<File> item = new ArrayList<File>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lists);
        listView = findViewById(R.id.myLists);
        ArrayAdapter<File> arrayAdapter = new ArrayAdapter<File>(MyLists.this,simple_list_item_1, item);
        listView.setAdapter(arrayAdapter);
        String nomeDiretorio = "EasyMarketLists";
        String  diretorioApp = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/"+nomeDiretorio+"/";
        File file = new File(diretorioApp);
        File[] arquivos = file.listFiles();
        for (File fileTemp : arquivos){
            item.add(fileTemp);
        }
    }
}

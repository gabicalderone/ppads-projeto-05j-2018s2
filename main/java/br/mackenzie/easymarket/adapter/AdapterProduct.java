package br.mackenzie.easymarket.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import br.mackenzie.easymarket.R;
import br.mackenzie.easymarket.modelo.Product;

public class AdapterProduct extends BaseAdapter {
    private final List<Product> produtos;
    private final Activity act;

    public AdapterProduct(List<Product> listProducts, Activity activity){
        this.produtos = listProducts;
        this.act = activity;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int position){
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position){
        return produtos.get(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = act.getLayoutInflater().inflate(R.layout.layout_product, parent, false);
        final Product produto = produtos.get(position);

        //Pegando as referencias
        TextView nome = (TextView) view.findViewById(R.id.nomeProduto);
        CheckBox check = (CheckBox) view.findViewById(R.id.checkBox);

        //Enviando os dados
        nome.setText(produto.getNome());
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                produto.setChecked(isChecked);
                // Teste de mensagem
                //Toast.makeText(ctx, product.getConteudo()+" "+isChecked, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}

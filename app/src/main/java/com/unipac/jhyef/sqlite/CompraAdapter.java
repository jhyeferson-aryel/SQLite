package com.unipac.jhyef.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CompraAdapter extends BaseAdapter {

    private ArrayList<ListaCompras> compras;
    private Context context;

    public CompraAdapter(ArrayList<ListaCompras> compras, Context context) {
        this.compras = compras;
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.compras.size();
    }

    @Override
    public ListaCompras getItem(int index) {
        return this.compras.get(index);
    }

    @Override
    public long getItemId(int position) {
        return this.compras.get(position).getId();
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {

        final ListaCompras listaCompras = getItem(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_compra, null);

        TextView itemNome = (TextView) view.findViewById(R.id.txtItem);
        Button btnEditar = (Button) view.findViewById(R.id.btnEditar);
        Button btnExcluir = (Button) view.findViewById(R.id.btnExcluir);

        itemNome.setText(listaCompras.getNomeProd());

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.listaComprasDAO.deleteItem(listaCompras);
                MainActivity.compraAdapter = new CompraAdapter(MainActivity.compras, context);
                MainActivity.compras.remove(listaCompras);
                MainActivity.lista.setAdapter(MainActivity.compraAdapter);
                MainActivity.loadList();
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.idEdit =  listaCompras.getId();
                MainActivity.nomeProd =  listaCompras.getNomeProd();
                MainActivity.qtdProd =  listaCompras.getQuantidade().toString();
                MainActivity.setEditar();
            }
        });

        return view;
    }
}

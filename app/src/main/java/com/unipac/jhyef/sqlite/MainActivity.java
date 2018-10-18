package com.unipac.jhyef.sqlite;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static EditText txtProd = null, quant = null;
    static Button btnAdd = null, btnCancel = null;
    static ListView lista = null;
    static ArrayList<ListaCompras> compras = null;
    public static CompraAdapter compraAdapter = null;
    static ListaComprasDAO listaComprasDAO = null;

    // EDITAR
    public static Long idEdit = -1L;
    public static String nomeProd = null;
    public static String qtdProd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaComprasDAO = new ListaComprasDAO(this);

        txtProd = (EditText) findViewById(R.id.nomeTxt);
        btnAdd = (Button) findViewById(R.id.addItem);
        btnCancel = (Button) findViewById(R.id.btnCancelar);
        quant = (EditText) findViewById(R.id.quantProd);
        lista = (ListView) findViewById(R.id.lstCompras);


        loadList();
        compraAdapter = new CompraAdapter(compras, MainActivity.this);
        lista.setAdapter(compraAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListaCompras l = new ListaCompras();
                l.setId(idEdit);
                l.setNomeProd(txtProd.getText().toString());
                l.setQuantidade(Integer.parseInt(quant.getText().toString()));
                if (btnCancel.getVisibility() != View.VISIBLE) {
                    compras.add(l);
                    listaComprasDAO.addItem(l);
                } else {
                    for (ListaCompras ite : compras) {
                        if (ite.getId().equals(l.getId())) {
                            ite.setNomeProd(l.getNomeProd());
                            ite.setQuantidade(l.getQuantidade());
                        }
                    }
                    listaComprasDAO.updateListaCompras(l);
                    btnCancel.setVisibility(View.GONE);
                    idEdit = -1L;
                }
                compraAdapter = new CompraAdapter(compras, MainActivity.this);
                lista.setAdapter(compraAdapter);
                loadList();
                txtProd.setText("");
                quant.setText("");
                btnAdd.setText("Adicionar");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idEdit = -1L;
                btnCancel.setVisibility(View.GONE);
                txtProd.setText("");
                quant.setText("");
                btnAdd.setText("Adicionar");
            }
        });
    }

    public static void setEditar() {
        txtProd.setText(MainActivity.nomeProd);
        quant.setText(MainActivity.qtdProd);
        btnCancel.setVisibility(View.VISIBLE);
        btnAdd.setText("Editar");
    }

    public static void loadList() {
        compras = listaComprasDAO.retornaItens();
    }

}

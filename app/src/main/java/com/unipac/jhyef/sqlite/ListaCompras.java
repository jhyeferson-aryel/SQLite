package com.unipac.jhyef.sqlite;

import java.io.Serializable;

public class ListaCompras {
    private String nomeProd;
    private Integer quantidade;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return "ListaCompras{" +
                "nomeProd='" + nomeProd + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }
}

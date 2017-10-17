package br.ufjf.dcc171;

import java.io.Serializable;
import java.util.Date;

public class Lancamento implements Serializable{
    private String descricao;
    private Date data;
    private Double valor;

    public Lancamento() {
        this(null, new Date(), null);
    }

    public Lancamento(String descricao, Double valor) {
        this(descricao, new Date(), valor);
    }

    
    
    public Lancamento(String descricao, Date data, Double valor) {
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
    }

    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
}

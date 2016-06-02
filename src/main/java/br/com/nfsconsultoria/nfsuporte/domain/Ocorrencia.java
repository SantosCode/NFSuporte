/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfsconsultoria.nfsuporte.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author luissantos
 */
@SuppressWarnings("serial")
@Entity
public class Ocorrencia extends GenericDomain {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, unique = true)
    private Long chamado;

    @Column(nullable = false, length = 45)
    private String tipo;

    @Column(nullable = false, length = 100)
    private String descricao;
    
    @Column(nullable = false, length = 300)
    private String problema;

    @ManyToOne
    @JoinColumn
    private Cliente cliente;

    @ManyToOne
    @JoinColumn
    private Equipamento equipamento;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date data_ab;

    @Temporal(TemporalType.DATE)
    @Column
    private Date data_at;

    @Temporal(TemporalType.DATE)
    @Column
    private Date data_enc;

    @Column(length = 600)
    private String solucao;
    
    @Column(nullable = false, length = 20)
    private String status;

    public Long getChamado() {
        return chamado;
    }

    public void setChamado(Long chamado) {
        this.chamado = chamado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Date getData_ab() {
        return data_ab;
    }

    public void setData_ab(Date data_ab) {
        this.data_ab = data_ab;
    }

    public Date getData_at() {
        return data_at;
    }

    public void setData_at(Date data_at) {
        this.data_at = data_at;
    }

    public Date getData_enc() {
        return data_enc;
    }

    public void setData_enc(Date data_enc) {
        this.data_enc = data_enc;
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

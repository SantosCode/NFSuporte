/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfsconsultoria.nfsuporte.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author luissantos
 */
@SuppressWarnings("serial")
@Entity
public class Cliente extends GenericDomain{
    
    @Column(nullable = false,length = 15, unique = true)
    private String nome;
    
    @Column(length = 15)
    private String tel;
    
    @Column(length = 80)
    private String rua;
    
    @Column(length = 30)
    private String bairro;
    
    @Column(length = 15)
    private String cidade;
    
    @Column(columnDefinition = "byte")
    private byte contrato;
    
    @ManyToOne
    @JoinColumn(nullable = true)
    private Usuario usuario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public byte getContrato() {
        return contrato;
    }

    public void setContrato(byte contrato) {
        this.contrato = contrato;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}

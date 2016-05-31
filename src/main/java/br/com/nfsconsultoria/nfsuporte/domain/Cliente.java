/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfsconsultoria.nfsuporte.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author luissantos
 */
@SuppressWarnings("serial")
@Entity
public class Cliente extends GenericDomain{
    
    @Column(nullable = false,length = 45, unique = true)
    private String empresa;
    
    @Column(length = 15)
    private String tel;
    
    @Column(length = 80)
    private String rua;
    
    @Column(length = 45)
    private String bairro;
    
    @Column(length = 45)
    private String cidade;
    
    @Temporal(TemporalType.DATE)
    @Column
   private Date data_c;
    
    @Column(columnDefinition = "LONGBLOB")
    private byte[] contrato;
    
    @ManyToOne
    @JoinColumn(nullable = true)
    private Usuario usuario;

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    public Date getData_c() {
        return data_c;
    }

    public void setData_c(Date data_c) {
        this.data_c = data_c;
    }

    public byte[] getContrato() {
        return contrato;
    }

    public void setContrato(byte[] contrato) {
        this.contrato = contrato;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfsconsultoria.nfsuporte.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author luissantos
 */
@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain{
    
    @Column(nullable = false, length = 15)
    private String nome;
    
    @Column(length = 15)
    private String tel;
    
    @Column(length = 45)
    private String email;
    
    @Column(nullable = false, length = 45, unique = true)
    private String login;
    
    @Column(nullable = false, length = 10)
    private String senha;
    
    @Column(nullable = false, length = 45)
    private String nivel;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfsconsultoria.nfsuporte.bean;

import br.com.nfsconsultoria.nfsuporte.dao.UsuarioDAO;
import br.com.nfsconsultoria.nfsuporte.domain.Usuario;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;

/**
 *
 * @author luissantos
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{
    
    private Usuario usuario;
    private List<Usuario> usuarios;
    
    public UsuarioBean() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        this.usuarios = usuarioDAO.listar();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
     public List<String> getNiveis() {
        String[] nivel = new String[]{"Administrador", "Usuario", "Cliente"};
        return Arrays.asList(nivel);
  
    }
    @PostConstruct
    public void listar(){
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            this.usuarios = usuarioDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " 
                    +erro.getMessage()+ " ao tentar listar usuarios");
            erro.printStackTrace();
        }
    }
    
    public void novo(){
        this.usuario = new Usuario();
    }
    
    public void salvar(){
            try {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuarioDAO.merge(usuario);
                listar();
                novo();
                Messages.addGlobalInfo("Usuario salvo com sucesso");
            } catch (RuntimeException erro) {
                Messages.addGlobalError("Ocorreu o erro " 
                    +erro.getMessage()+ " ao tentar salvar usuario");
                erro.printStackTrace();
            }
    }
    
    public void editar(ActionEvent evento){
        try {
            listar();
            usuario = (Usuario) evento.getComponent().getAttributes()
                    .get("usuarioSelecionado");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " 
                    +erro.getMessage()+ " ao tentar editar usuario");
        }
    }
    
    public void excluir(ActionEvent evento){
        try {
            usuario = (Usuario) evento.getComponent().getAttributes()
                    .get("usuarioSelecionado");
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.excluir(usuario);
            listar();
            novo();
            Messages.addGlobalInfo("Usuario removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " 
                    +erro.getMessage()+ " ao tentar excluir usuario");
            erro.printStackTrace();
        }
    }
}

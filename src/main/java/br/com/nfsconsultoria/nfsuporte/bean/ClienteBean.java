/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfsconsultoria.nfsuporte.bean;

import br.com.nfsconsultoria.nfsuporte.dao.ClienteDAO;
import br.com.nfsconsultoria.nfsuporte.dao.UsuarioDAO;
import br.com.nfsconsultoria.nfsuporte.domain.Cliente;
import br.com.nfsconsultoria.nfsuporte.domain.Usuario;
import org.apache.poi.util.IOUtils;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

/**
 * @author luissantos
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

    private Cliente cliente;
    private List<Cliente> clientes;
    private List<Usuario> usuarios;

    public ClienteBean() {
        ClienteDAO clienteDAO = new ClienteDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        this.clientes = clienteDAO.listar();
        this.usuarios = usuarioDAO.listarLazy("usuario");
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }


    @PostConstruct
    public void listar() {
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            this.clientes = clienteDAO.listar();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            this.usuarios = usuarioDAO.listarLazy("usuario");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro "
                    + erro.getMessage() + " ao tentar listar clientes");
        }
    }

    /* Metodo para fazer o upload do contrato do cliente para o banco de dados */
    public void FileUp(FileUploadEvent evento) {
        try {
            FacesMessage message = new FacesMessage("Contrato",
                    evento.getFile().getFileName() + " carregado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);
            UploadedFile contrato = evento.getFile();
            byte[] arquivo = (IOUtils.toByteArray(contrato.getInputstream()));
            this.cliente.setContrato(arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void FileTopUp(FileUploadEvent evento) {
        try {
            FacesMessage message = new FacesMessage("Topologia",
                    evento.getFile().getFileName() + " carregado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);
            UploadedFile contrato = evento.getFile();
            byte[] arquivo = (IOUtils.toByteArray(contrato.getInputstream()));
            this.cliente.setTopologia(arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Metodo para fazer o download do contrato no banco de dados para a maquina */
    public StreamedContent content(byte[] bytes) {
        try {

            InputStream is = new ByteArrayInputStream(bytes);
            StreamedContent arquivo = new DefaultStreamedContent(is);
            return arquivo;
        } catch (RuntimeException e) {
            Messages.addGlobalError("Não foi possivel fazer o download do arquivo");
            e.printStackTrace();
        }
        return null;
    }


    public void novo() {
        this.cliente = new Cliente();
    }

    public void salvar() {
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.merge(cliente);
            listar();
            novo();
            Messages.addFlashGlobalInfo("Cliente salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro "
                    + erro.getMessage() + " ao tentar salvar cliente");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            listar();
            cliente = (Cliente) evento.getComponent().getAttributes()
                    .get("clienteSelecionado");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro "
                    + erro.getMessage() + " ao tentar editar cliente");
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            cliente = (Cliente) evento.getComponent().getAttributes()
                    .get("clienteSelecionado");
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.excluir(cliente);
            listar();
            novo();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro "
                    + erro.getMessage() + " ao tentar excluir cliente");
            erro.printStackTrace();
        }
    }

}

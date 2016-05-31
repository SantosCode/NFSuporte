/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfsconsultoria.nfsuporte.bean;

import br.com.nfsconsultoria.nfsuporte.dao.ClienteDAO;
import br.com.nfsconsultoria.nfsuporte.dao.EquipamentoDAO;
import br.com.nfsconsultoria.nfsuporte.domain.Cliente;
import br.com.nfsconsultoria.nfsuporte.domain.Equipamento;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;

/**
 * @author luissantos
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EquipamentoBean implements Serializable {

    private Equipamento equipamento;
    private List<Equipamento> equipamentos;
    private List<Cliente> clientes;

    public EquipamentoBean() {
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        this.equipamentos = equipamentoDAO.listar();
        this.clientes = clienteDAO.listarLazy("cliente");
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @PostConstruct
    public void listar() {
        try {
            EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
            this.equipamentos = equipamentoDAO.listar();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocoreu o erro " + erro.getMessage()
                    + " ao tentar listar equipamentos");
        }
    }

    public void novo() {
        this.equipamento = new Equipamento();
    }

    public void salvar() {
        try {
            EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
            equipamentoDAO.merge(equipamento);
            listar();
            novo();
            Messages.addGlobalInfo("Equipamento salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocoreu o erro " + erro.getMessage()
                    + " ao tentar salvar equipamento");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            listar();
            equipamento = (Equipamento) evento.getComponent().getAttributes()
                    .get("equipamentoSelecionado");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocoreu o erro " + erro.getMessage()
                    + " ao tentar selecionar equipamento");
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            equipamento = (Equipamento) evento.getComponent().getAttributes()
                    .get("equipamentoSelecionado");
            EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
            equipamentoDAO.excluir(equipamento);
            listar();
            novo();
            Messages.addGlobalInfo("Equipamento excluido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocoreu o erro " + erro.getMessage()
                    + " ao tentar excluir equipamento");
        }
    }
}

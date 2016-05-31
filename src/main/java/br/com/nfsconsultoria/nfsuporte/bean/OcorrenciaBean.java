/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfsconsultoria.nfsuporte.bean;

import br.com.nfsconsultoria.nfsuporte.dao.ClienteDAO;
import br.com.nfsconsultoria.nfsuporte.dao.EquipamentoDAO;
import br.com.nfsconsultoria.nfsuporte.dao.OcorrenciaDAO;
import br.com.nfsconsultoria.nfsuporte.domain.Cliente;
import br.com.nfsconsultoria.nfsuporte.domain.Equipamento;
import br.com.nfsconsultoria.nfsuporte.domain.Ocorrencia;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

/**
 *
 * @author luissantos
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class OcorrenciaBean implements Serializable{
    
    private Ocorrencia ocorrencia;
    private List<Ocorrencia> ocorrencias;
    private List<Cliente> clientes;
    private List<Equipamento> equipamentos;

    public OcorrenciaBean() {
        OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
        this.ocorrencias = ocorrenciaDAO.listar();
        this.clientes = clienteDAO.listarLazy("cliente");
        this.equipamentos = equipamentoDAO.listarLazy("equipamento");
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public List<Ocorrencia> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }
    
    @PostConstruct
    public void listar(){
        try {
        OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
        this.ocorrencias = ocorrenciaDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocoreu o erro " +erro.getMessage()
                    + " ao tentar listar ocorrencia");
        }
    }
    
    public void novo(){
        this.ocorrencia = new Ocorrencia();
    }
    
    public void salvar(){
        try {
            OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
            ocorrenciaDAO.merge(ocorrencia);
            listar();
            novo();
            Messages.addGlobalInfo("Ocorrencia salva com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocoreu o erro " +erro.getMessage()
                    + " ao tentar salvar equipamento");
        }
    }
    
    public void editar(ActionEvent evento){
        try {
            listar();
            OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
            ocorrencia = (Ocorrencia) evento.getComponent().getAttributes()
                    .get("ocorrenciaSelecionada");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocoreu o erro " +erro.getMessage()
                    + " ao tentar salvar ocorrencia");
            erro.printStackTrace();
        }
    }
    
    public void excluir(ActionEvent evento){
        try {
           ocorrencia = (Ocorrencia) evento.getComponent().getAttributes()
                   .get("ocorrenciaSelecionada");
           OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
           ocorrenciaDAO.excluir(ocorrencia);
           listar();
           novo();
           Messages.addGlobalInfo("Ocorrencia excluida com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocoreu o erro " +erro.getMessage()
                    + " ao tentar excluir ocorrencia");
            erro.printStackTrace();
        }
    }
}

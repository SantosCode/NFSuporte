/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfsconsultoria.nfsuporte.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author luissantos
 */
public class HibernateUtil {
    private static SessionFactory fabricaDeSessoes = criarFabricaDeSesoes();

    public static SessionFactory getFabricaDeSessoes() {
        return fabricaDeSessoes;
    }

    private static SessionFactory criarFabricaDeSesoes() {
        try {
            Configuration configuration = new Configuration().configure();
            ServiceRegistry registro = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            SessionFactory fabrica = configuration.buildSessionFactory(registro);
            return fabrica;
        } catch (Throwable ex) {
            System.err.println("A fabrica de sessões não pode ser criada. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

}

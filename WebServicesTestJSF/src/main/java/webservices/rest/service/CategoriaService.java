/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices.rest.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import webservices.rest.entidade.Categoria;
import webservices.rest.entidade.Produto;
import webservices.rest.util.HibernateUtil;

/**
 *
 * @author Administrador
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CategoriaService {

    @PersistenceContext
    private EntityManager em;
    private Session sessao;
    private Transaction transacao;

    public List<Categoria> getCategorias() {
        try {
            List<Categoria> categorias = null;
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            this.transacao = this.sessao.beginTransaction();
            Criteria filtro = this.sessao.createCriteria(Categoria.class);
            categorias = filtro.list();
            this.transacao.commit();
            return categorias;
        } catch (HibernateException e) {
            System.out.println("Não foi possível fazer a operação: " + e.getMessage());
            if (this.transacao.isActive()) {
                this.transacao.rollback();
            }
        } finally {
            try {
                if (this.sessao.isOpen()) {
                    this.sessao.close();
                }
            } catch (Throwable e) {
                System.out.println("Não foi possível fechar a sessão. " + e.getMessage());
                return null;
            }
        }
        return null;
    }
}

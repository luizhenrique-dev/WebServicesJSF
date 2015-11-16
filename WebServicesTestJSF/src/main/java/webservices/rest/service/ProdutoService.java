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
import webservices.rest.entidade.Produto;
import webservices.rest.util.HibernateUtil;

/**
 *
 * @author Administrador
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ProdutoService {

    @PersistenceContext
    private EntityManager em;
    private Session sessao;
    private Transaction transacao;

    public List<Produto> getProdutosByDescricao(String descricao) {
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            this.transacao = this.sessao.beginTransaction();
            String hql = "from Produto p where p.descricao = :descricao";
            org.hibernate.Query query = this.sessao.createQuery(hql);
            query.setString("descricao", descricao);
            List<Produto> lista = query.list();
            this.transacao.commit();
            return lista;
        } catch (HibernateException e) {
            System.out.println("Não foi possível fazer a operação: " + e.getMessage());
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
    
    public List<Produto> getProdutosByCategoriaDescricao(String descricao, String categoria) {
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            this.transacao = this.sessao.beginTransaction();
            String hql = "from Produto p where p.descricao = :descricao and p.categoria.nome =:categoria";
            org.hibernate.Query query = this.sessao.createQuery(hql);
            query.setString("descricao", descricao);
            query.setString("categoria", categoria);
            List<Produto> lista = query.list();
            this.transacao.commit();
            return lista;
        } catch (HibernateException e) {
            System.out.println("Não foi possível fazer a operação: " + e.getMessage());
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

    public List<Produto> getProdutosByNome(String nome) {
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            this.transacao = this.sessao.beginTransaction();
            String hql = "from Produto p where p.nome = :nome";
            org.hibernate.Query query = this.sessao.createQuery(hql);
            query.setString("nome", nome);
            List<Produto> lista = query.list();
            return lista;
        } catch (HibernateException e) {
            System.out.println("Não foi possível fazer a operação: " + e.getMessage());
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

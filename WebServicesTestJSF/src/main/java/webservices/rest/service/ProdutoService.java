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
import webservices.rest.entidade.Produto;
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
    
    public List<Produto> getProdutosByDescricao(String descricao){
       Query query = em.createQuery("select prod from Produto prod where prod.descricao like ?1");
       query.setParameter(1, "%"+descricao+"%");
       return query.getResultList();
    }
    
    public List<Produto> getProdutosByNome(String nome){
       Query query = em.createQuery("select prod from Produto prod where prod.nome like ?1");
       query.setParameter(1, "%"+nome+"%");
       return query.getResultList();
    }
}

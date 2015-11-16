/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices.rest.service.ws;

import java.util.List;
import javax.ejb.EJB;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import webservices.rest.entidade.Produto;
import webservices.rest.service.ProdutoService;

/**
 * REST Web Service
 *
 * @author Administrador
 */
@Path("produtoService")
public class ProdutoWebService {

    @Context
    private UriInfo context;
    @EJB
    private ProdutoService produtoService;

    public ProdutoWebService() {
    }

    @GET
    @Produces("application/json")
    @Path("{categoria}/{descricao}")
    public List<Produto> getProdutosByDescricao(@NotNull @PathParam("categoria") String categoria, @NotNull @PathParam("descricao") String descricao) {
        if (categoria != null && !categoria.equalsIgnoreCase("todas")) {
            return produtoService.getProdutosByCategoriaDescricao(descricao, categoria);
        } else {
            return produtoService.getProdutosByDescricao(descricao);
        }
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}

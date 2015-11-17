package webservices.rest.control;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import webservices.rest.entidade.Categoria;
import webservices.rest.service.CategoriaService;


@SessionScoped
@Named
public class ProdutoControl implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private CategoriaService categoriaService;
	
	public List<SelectItem> getCategorias() {
		List<SelectItem> toReturn = new LinkedList<SelectItem>();
		toReturn.add(new SelectItem("todas","Todas categorias"));
		for (Categoria cate : categoriaService.getCategorias()) {
			toReturn.add(new SelectItem(cate.getNome(), cate.getNome()));
		}
		return toReturn;
	}
	

}

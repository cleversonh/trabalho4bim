package controller;

import javax.inject.Inject;
import javax.ws.rs.Path;

import br.unvel.model.Carrinho;
import br.unvel.model.Jogo;
import br.unvel.model.Venda;
import br.unvel.rest.JogoEndpoint;
import br.unvel.rest.VendaEndpoint;

public class CarrinhoControle {

	@Inject
	private Carrinho carrinho;
	
	@Inject
	private JogoEndpoint je;
	
	@Path("/adicionar/{id:[0-9][0-9]*}")
	public void adicionarProduto(long id){
		
		Jogo jogo = je.findById(id).readEntity(Jogo.class);
		carrinho.addJogos(jogo);
		
	}
	
	public void limpar(){
		carrinho.limpar();
	}
	
	public void finalizar(){
		for(Jogo jogo : carrinho.getJogos().values()){
			Venda venda = new Venda();
			venda.setJogo(jogo.getNome());
			venda.setValor(jogo.getValor());
			venda.setDescricao(jogo.getDescricao());
			VendaEndpoint ve = new VendaEndpoint();
			ve.create(venda);
		}
		
		
	}
	
}

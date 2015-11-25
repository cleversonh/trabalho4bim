package controller;

import javax.inject.Inject;
import javax.ws.rs.Path;

import br.univel.rest.JogoEndpoint;
import br.univel.rest.VendaEndpoint;
import br.unvel.model.Carrinho;
import br.unvel.model.Jogo;
import br.unvel.model.Venda;

public class CarrinhoControle {

	@Inject
	private Carrinho carrinho;
	
	@Inject
	private JogoEndpoint je;
	
	@Path("/adicionar/{id:[0-9][0-9]*}")
	public void adicionarJogo(long id){
		
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
	
	@Path("/excluir/{id:[0-9][0-9]*}")
	public void excluirJogo(long id){
		
		Jogo jogo = je.findById(id).readEntity(Jogo.class);
		carrinho.execluirJogos(jogo);
	}
	
	@Path("/excluir/{id:[0-9][0-9]*}")
	public void diminirJogo(long id){
		
		Jogo jogo = je.findById(id).readEntity(Jogo.class);
		carrinho.diminirJogos(jogo);
	}
	
}

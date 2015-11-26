package br.univel.model;

import java.util.HashMap;

public class Carrinho {

	private HashMap<Long, Jogo> jogos = new HashMap<Long, Jogo>();
	private HashMap<Long, Integer> quantidades = new HashMap<Long, Integer>();

	public void addJogos(Jogo jogo) {
		for (long i : jogos.keySet()) {
			if (jogo.getId() == i) {
				Integer quant = quantidades.get(i);
				quantidades.put(i, ++quant);
				return;
			}
		}
		jogos.put(jogo.getId(), jogo);
		quantidades.put(jogo.getId(), 1);

	}

	public void limpar() {

		jogos.clear();
		quantidades.clear();

	}

	public HashMap<Long, Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(HashMap<Long, Jogo> jogos) {
		this.jogos = jogos;
	}

	public HashMap<Long, Integer> getQuantidades() {
		return quantidades;
	}

	public void setQuantidades(HashMap<Long, Integer> quantidades) {
		this.quantidades = quantidades;
	}

	public void execluirJogos(Jogo jogo) {
		
		jogos.remove(jogo.getId());
		quantidades.remove(jogo.getId());
		
			
	}

	public void diminirJogos(Jogo jogo) {
		
		int quant = quantidades.get(jogo.getId());
		if (quant > 1){
			quantidades.put(jogo.getId(), --quant);
		}else{
			execluirJogos(jogo);
		}
		
	}

}

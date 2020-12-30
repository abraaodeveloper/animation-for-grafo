/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaçao_grafo;

import java.util.ArrayList;
import java.util.List;

public class Floresta {
	
	List<List<Vertice>> conjuntos;
	
	public Floresta(){
		this.conjuntos = new ArrayList<List<Vertice>>();
	}
	
	public void adicionarArvore(ArrayList<Vertice> arvore) {
		conjuntos.add(arvore);
	}
	
	public void unir(Vertice u, Vertice v) {
		List<Vertice> arvore1 = obterArvore(u);
		List<Vertice> arvore2 = obterArvore(v);
		arvore1.addAll(arvore2);
		
		removerArvore(arvore2);
	}
	
	public void removerArvore(List<Vertice> arvore) {
		for (int i = 0; i < conjuntos.size(); i++) {
			if(!diferentes(arvore, conjuntos.get(i))) {
				conjuntos.remove(i);
				break;
			} 
		}
	}
	
	public static boolean diferentes(List<Vertice> arvore1, List<Vertice> arvore2) {
		if (arvore1 == null || arvore2 == null 
				|| arvore1.size() != arvore2.size()) {
			return true;
		} else {
			for(Vertice v : arvore1) {
				if (!arvore2.contains(v)) {
					return true;
				}
			}
			return false;
		}
	}
	
	public List<Vertice> obterArvore(Vertice v) {
		for(List<Vertice> vertices : conjuntos) {
			if(vertices.contains(v)) {
				return vertices;
			}
		}
		return null;
	}

}
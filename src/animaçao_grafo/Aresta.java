package animaçao_grafo;

public class Aresta implements Comparable<Aresta>{
	
	private int custo;
	
	private Vertice origem;
	
	private Vertice destino;

	public Vertice getOrigem() {
		return origem;
	}

	public void setOrigem(Vertice origem) {
		this.origem = origem;
	}

	public Vertice getDestino() {
		return destino;
	}

	public void setDestino(Vertice destino) {
		this.destino = destino;
	}
	
	public int getCusto() {
		return custo;
	}

	public void setCusto(int custo) {
		this.custo = custo;
	}

	public String toString() {
		String retorno = "(" + origem.getId() + "," 
				+ destino.getId() + "," + custo + ")";
		return retorno;
	}
	
	public int compareTo(Aresta outro) {
		if (this.custo < outro.getCusto()) {
			return -1;
		} else if (this.custo > outro.getCusto()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public Vertice outro(Vertice v) {
		if (origem.equals(v)) {
			return destino;
		} else if (destino.equals(v)) {
			return origem;
		} else {
			return null;
		}
	}

}

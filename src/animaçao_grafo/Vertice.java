package animaçao_grafo;

public class Vertice implements Comparable<Vertice>{
	
	public static final int BRANCO = 0;
	public static final int CINZA = 1;
	public static final int PRETO = 2;
	
	private int id;
	
	private int cor;
	
	private Vertice predecessor;
	
	private int descoberta;
	
	private int finalizacao;
	
	private int custoMinimo;
	
	public Vertice(int id) {
		this.id = id;
	}

	public int getCor() {
		return cor;
	}

	public void setCor(int cor) {
		this.cor = cor;
	}

	public Vertice getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Vertice predecessor) {
		this.predecessor = predecessor;
	}

	public int getDescoberta() {
		return descoberta;
	}

	public void setDescoberta(int descoberta) {
		this.descoberta = descoberta;
	}

	public int getFinalizacao() {
		return finalizacao;
	}

	public void setFinalizacao(int finalizacao) {
		this.finalizacao = finalizacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustoMinimo() {
		return custoMinimo;
	}

	public void setCustoMinimo(int custoMinimo) {
		this.custoMinimo = custoMinimo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertice other = (Vertice) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public String toString() {
		String idPred = "-";
		if(predecessor != null) {
			idPred = String.valueOf(predecessor.getId());
		}
		
		String sCor = "";
		switch(cor) {
			case Vertice.BRANCO:
				sCor = "B";
				break;
			case Vertice.CINZA:
				sCor = "C";
				break;
			case Vertice.PRETO:
				sCor = "P";
				break;
		}
		
		String retorno = id 
				+ "(c:" + sCor 
				+ ",p:" + idPred
				+ ",d:" + descoberta 
				+ ",f:" + finalizacao 
				+ ",cm:" + custoMinimo + ")";
		
		return retorno;
	}
	
	public int compareTo(Vertice outro) {
		if (this.custoMinimo < outro.getCustoMinimo()) {
			return -1;
		} else if (this.custoMinimo > outro.getCustoMinimo()) {
			return 1;
		} else {
			return 0;
		}
	}

}

package animaçao_grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grafo {

    private List<Vertice> vertices;

    private List<Aresta> arestas;

    private List<Vertice> corte;

    public Grafo() {
        vertices = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();
    }

    public int pesoNaoDirecionado(Vertice u, Vertice v) {
        Aresta a = obterArestaNaoDirecionada(u, v);
        if (a != null) {
            return a.getCusto();
        } else {
            return -1;
        }
    }

    public Aresta obterAresta(Vertice u, Vertice v) {
        for (Aresta a : arestas) {
            if (a.getOrigem().equals(u) && a.getDestino().equals(v)) {
                return a;
            }
        }

        return null;
    }

    public ArrayList<Aresta> obterArestas(Vertice u) {
        ArrayList<Aresta> retorno = new ArrayList<Aresta>();
        for (Aresta a : arestas) {
            if ((a.getOrigem().equals(u) || a.getDestino().equals(u))) {
                retorno.add(a);
            }
        }

        return retorno;
    }

    public Aresta obterArestaNaoDirecionada(Vertice u, Vertice v) {
        for (Aresta a : arestas) {
            if ((a.getOrigem().equals(u) && a.getDestino().equals(v))
                    || (a.getDestino().equals(u) && a.getOrigem().equals(v))) {
                return a;
            }
        }

        return null;
    }

    public void adicionarVertice(int id) {
        for (Vertice v : vertices) {
            if (v.getId() == id) {
                throw new VerticeException("Vertice já existe: " + id);
            }
        }

        vertices.add(new Vertice(id));
    }

    public Vertice obterVertice(int id) {
        Vertice retorno = null;
        for (Vertice v : vertices) {
            if (v.getId() == id) {
                retorno = v;
                break;
            }
        }

        return retorno;
    }

    public void adicionarAresta(int origem, int destino) {
        Vertice vOrigem = obterVertice(origem);
        Vertice vDestino = obterVertice(destino);
        checaVertices(origem, destino, vOrigem, vDestino);

        Aresta aresta = new Aresta();
        aresta.setOrigem(vOrigem);
        aresta.setDestino(vDestino);
        arestas.add(aresta);
    }

    private void checaVertices(int origem, int destino, Vertice vOrigem, Vertice vDestino) {
        if (origem < 0 || vOrigem == null) {
            throw new VerticeException("Vertice origem invalido: " + origem);
        }

        if (destino < 0 || vDestino == null) {
            throw new VerticeException("Vertice destino invalido: " + destino);
        }

        for (Aresta aresta : arestas) {
            if (aresta.getOrigem().getId() == origem
                    && aresta.getDestino().getId() == destino) {
                throw new ArestaException("Aresta já existe: (" + origem + "," + destino + ")");
            }
        }
    }

    public void adicionarAresta(int origem, int destino, int custo) {
        Vertice vOrigem = obterVertice(origem);
        Vertice vDestino = obterVertice(destino);
        checaVertices(origem, destino, vOrigem, vDestino);

        Aresta aresta = new Aresta();
        aresta.setOrigem(vOrigem);
        aresta.setDestino(vDestino);
        aresta.setCusto(custo);
        arestas.add(aresta);
    }

    public List<Vertice> adjacentesNaoDirecionados(Vertice vertice) {
        ArrayList<Vertice> adjacentes = new ArrayList<Vertice>();

        for (Aresta a : arestas) {
            Vertice origem = a.getOrigem();
            Vertice destino = a.getDestino();
            Vertice novo = null;

            if (vertice.equals(origem)) {
                novo = destino;
            } else if (vertice.equals(destino)) {
                novo = origem;
            }

            if (vertice.equals(origem) || vertice.equals(destino)
                    && !adjacentes.contains(novo)) {
                adjacentes.add(novo);
            }
        }

        return adjacentes;
    }

    public List<Vertice> adjacentes(Vertice v) {
        ArrayList<Vertice> retorno = new ArrayList<Vertice>();

        for (Aresta a : arestas) {
            if (a.getOrigem().equals(v)) {
                retorno.add(a.getDestino());
            }
        }
        return retorno;
    }

    public List<Vertice> getVertices() {
        Collections.sort(vertices);
        return vertices;
    }

    public void setVertices(List<Vertice> vertices) {
        this.vertices = vertices;
    }

    public List<Aresta> getArestas() {
        Collections.sort(arestas);
        return arestas;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("G = { \nV{");
        for (int i = 0; i < vertices.size(); i++) {
            builder.append(vertices.get(i).toString());
            if (i < vertices.size() - 1) {
                builder.append(", ");
            }
        }

        builder.append("}\n");
        builder.append("A{");
        for (int i = 0; i < arestas.size(); i++) {
            builder.append(arestas.get(i).toString());
            if (i < arestas.size() - 1) {
                builder.append(", ");
            }
        }
        builder.append("}\n}");

        return builder.toString();
    }

    public ArrayList<Aresta> obterAgmNaoDirecionadaPrim() {
        ArrayList<Aresta> arestas = new ArrayList<Aresta>();
        for (Vertice v : vertices) {
            Aresta a = obterArestaNaoDirecionada(v, v.getPredecessor());
            if (a != null) {
                arestas.add(a);
            }
        }
        return arestas;
    }

    public List<Vertice> getCorte() {
        return corte;
    }

    public void setCorte(List<Vertice> corte) {
        this.corte = corte;
    }

    public Aresta obterArestaSegura() {
        Aresta segura = null;
        for (Aresta a : arestas) {
            if ((corte.contains(a.getOrigem()) && !corte.contains(a.getDestino()))
                    || (corte.contains(a.getDestino()) && !corte.contains(a.getOrigem()))) {
                if (segura == null || a.getCusto() < segura.getCusto()) {
                    segura = a;
                }
            }
        }
        return segura;
    }

    public void imprimirCaminho(Vertice origem, Vertice destino) {
        if (origem.equals(destino)) {
            System.out.println(origem.getId());
        } else if (destino.getPredecessor() == null) {
            System.out.println("Não existe caminho de " + origem.getId() + " até " + destino.getId());
        } else {
            imprimirCaminho(origem, destino.getPredecessor());
            System.out.println(destino.getId());
        }
    }
}

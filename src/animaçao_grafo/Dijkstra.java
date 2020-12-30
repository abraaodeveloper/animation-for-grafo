package animaçao_grafo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.PriorityQueue;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import objetos.LinhaPeso;
import objetos.Ponto;

public class Dijkstra extends JFrame {

    Grafo grafo = new Grafo();

    int teclada = 0, contId = 0, colisao = 0, idAnteriorC = 0, contT = 130;
    ArrayList<Ponto> vertices = new ArrayList<>();
    ArrayList<LinhaPeso> arestas = new ArrayList<>();
    ArrayList<LinhaPeso> resultado = new ArrayList<>();
    boolean podeCriarLinha = false, pegaAnt = false;
    Ponto pontoAnterior = new Ponto();

    public Dijkstra() {
        configuraçaoTela();
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent eve) {

            }
            @Override
            public void mouseEntered(MouseEvent arg0) {
            }
            @Override
            public void mouseExited(MouseEvent arg0) {
            }
            @Override
            public void mousePressed(MouseEvent arg0) {
            }
            @Override
            public void mouseReleased(MouseEvent eve) {
                if (teclada == 86) {//se a tecla "v" for pressionada vai ser adionado vertices
                    vertices.add(new Ponto(eve.getPoint().x - 25, eve.getPoint().y - 25, contId));
                    grafo.adicionarVertice(contId);
                    repaint();
                    contId++;
                }
                if (teclada == 65) {//se a tecla "a" for pressionada vai ser adicionado arestas
                    pontoAnterior.setX(eve.getPoint().x - 25);
                    pontoAnterior.setY(eve.getPoint().y - 25);
                    pontoAnterior.setId(contId);
                    for (int i = 0; i < vertices.size(); i++) {
                        //comparando vertices guardadas com ponto atual
                        if (dist(new Ponto(eve.getPoint().x - 25, eve.getPoint().y - 25, contId), vertices.get(i))
                                < 100) {
                            colisao++;

                            if (colisao == 1) {
                                idAnteriorC = vertices.get(i).getId();
                            }
                            //break;
                            if (colisao == 2) {//gera nova linha com peso
                              String novoPeso = "0";
                              novoPeso = JOptionPane.showInputDialog(null, "Digite o peso: ");
                                Random gerador = new Random();
                                int pesoGerado =  gerador.nextInt(20);
                                arestas.add(new LinhaPeso(idAnteriorC, vertices.get(i).getId(),Integer.parseInt(novoPeso)) );//Integer.parseInt(novoPeso))
                                grafo.adicionarAresta(idAnteriorC, vertices.get(i).getId(), Integer.parseInt(novoPeso));
                                colisao = 0;
                                repaint();
                            }
                        }  //restas.add(contId,vertices.get(i).getId(),Integer.parseInt(novoPeso)); 
                    }
                }
                if (teclada == 69) {//se a tecla "e" for pressionada vai ser executada a busca

                }
            }
        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                teclada = e.getKeyCode();
                System.out.println("codigo tecla pressionada: " + e.getKeyCode());
                if (e.getKeyCode() == 69) {
                    //String raiz = "0";
                    //raiz = JOptionPane.showInputDialog(null, "Digite a raiz: ");
                    try {
                        executar(grafo, 0);
                    } catch (Exception erro) {
                        System.err.println("Não foi posivel buscar! " + erro);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    public int dist(Ponto p1, Ponto p2) {
        int retorno = (int) Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
        return retorno;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke(4)); //4 pixels de largura
        g2d.setBackground(Color.BLUE);
        g2d.setColor(Color.red);

        g.drawString("Presione V para adicionar vertices", 10, 50);
        g.drawString("Pressione A para adicionar arestas", 10, 80);
        g.drawString("Presione E para buscar caminhos", 10, 110);

        for (int k = 0; k < arestas.size(); k++) {

            g2d.drawLine(vertices.get(arestas.get(k).getId1()).getX() + 25, vertices.get(arestas.get(k).getId1()).getY() + 25,
                    vertices.get(arestas.get(k).getId2()).getX() + 25, vertices.get(arestas.get(k).getId2()).getY() + 25);
            /* int x = (vertices.get(arestas.get(k).getId1()).getX() + 25) - (vertices.get(arestas.get(k).getId2()).getX() + 25);//meio da linha
            int y = (vertices.get(arestas.get(k).getId1()).getY() + 25) - (vertices.get(arestas.get(k).getId2()).getY() + 25);//meio da linha
            if (x < 0) {
                x = x * -1;
            }
            if (y < 0) {
                y = y * -1;
            }*/
            String idP = "(" + arestas.get(k).getId1() + ", " + arestas.get(k).getId2() + ", " + Integer.toString(arestas.get(k).getPeso()) + ")";
            g2d.drawString(idP, 10, contT);
            contT += 15;
        }
        contT = 130;
        for (int i = 0; i < vertices.size(); i++) {
            g2d.drawOval(vertices.get(i).getX(), vertices.get(i).getY(), 50, 50);
            g2d.drawString(Integer.toString(i), vertices.get(i).getX() + 25 * 2, vertices.get(i).getY() + 25 * 2);
        }

        g2d.setColor(Color.BLUE);
        for (int u = 0; u < resultado.size(); u++) {
            g2d.drawLine(vertices.get(resultado.get(u).getId1()).getX() + 25, vertices.get(resultado.get(u).getId1()).getY() + 25,
                    vertices.get(resultado.get(u).getId2()).getX() + 25, vertices.get(resultado.get(u).getId2()).getY() + 25);
        }
        g2d.dispose();
    }

    public void configuraçaoTela() {
        setSize(700, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
    }

    public void executar(Grafo g, int raiz) {
        for (Vertice v : g.getVertices()) {
            v.setCustoMinimo(Integer.MAX_VALUE);
            v.setPredecessor(null);
        }

        Vertice vRaiz = g.obterVertice(raiz);
        vRaiz.setCustoMinimo(0);

        PriorityQueue<Vertice> fila = new PriorityQueue<Vertice>();
        fila.addAll(g.getVertices());

        Vertice u = null;
        while (!fila.isEmpty()) {
            u = fila.remove();
            for (Vertice v : g.adjacentes(u)) {
                if (u.getCustoMinimo() + g.obterAresta(u, v).getCusto() < v.getCustoMinimo()) {
                    v.setCustoMinimo(u.getCustoMinimo() + g.obterAresta(u, v).getCusto());
                    v.setPredecessor(u);
                    for (int i = 0; i < vertices.size(); i++) {
                        if (v.getId() == vertices.get(i).getId()) {
                            resultado.add(new LinhaPeso(v.getPredecessor().getId(),v.getId()));

                            // System.out.println(v.getId());
                           // resultado.add(new LinhaPeso(u.getDescoberta(),u.getId(),u.getId()));
                            //resultado.add(new LinhaPeso(v.getId(), v.getPredecessor().getId()));
                            //resultado.add(new LinhaPeso(g.obterArestaSegura().getOrigem().getId(),g.obterArestaSegura().getDestino().getId(),v.getPredecessor().getId()));
                            /*if (cont == 0) {
                                resultado.add(new LinhaPeso(raiz,v.getId()));
                            } else {
                                resultado.add(new LinhaPeso(v.getPredecessor().getId(),v.getId()));
                            }*/
                            repaint();
                        }
                    }
                    //System.out.println(v.getCustoMinimo());
                }
            }
        }
    }

}

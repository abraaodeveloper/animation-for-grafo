/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author abraao
 */
public class LinhaPeso {

    private int id1;
    private int id2;
    private int peso;

    public LinhaPeso(int id1, int id2, int peso) {
        this.id1 = id1;
        this.id2 = id2;
        this.peso = peso;
    }

    public LinhaPeso(int id1, int id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

}

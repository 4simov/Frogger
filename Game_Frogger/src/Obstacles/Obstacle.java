/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obstacles;

/**
 *
 * @author coco
 */
public class Obstacle {

    private char type = '9';
    private int ligne;
    private int colonne;
    private int vitesse;
    private boolean hostile;
    private int sens;

    public Obstacle(int x, int y, int vitesse, int sens) {
        this.ligne = x;
        this.colonne = y;
        this.vitesse = vitesse;
        this.sens = sens;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public boolean isHostile() {
        return hostile;
    }

    public void setHostile(boolean hostile) {
        this.hostile = hostile;
    }

    public int getSens() {
        return sens;
    }

    public void setSens(int sens) {
        this.sens = sens;
    }

    public void deplacement() {
        this.ligne += sens;
    }
}

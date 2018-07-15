/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import Obstacles.Obstacle;
import Obstacles.Voiture;
import java.util.ArrayList;

/**
 *
 * @author coco
 */
public class Map extends Thread {

    private int colonne_max;
    private int ligne_max;
    private char map[][];
    private int regulateur = 0;
    private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

    public Map(int x, int y) {
        this.ligne_max = x;
        this.colonne_max = y;
        map = new char[ligne_max][colonne_max];
        this.init();
    }

    public int getColonne_max() {
        return colonne_max;
    }

    public void setColonne_max(int colonne_max) {
        this.colonne_max = colonne_max;
    }

    public int getLigne_max() {
        return ligne_max;
    }

    public void setLigne_max(int ligne_max) {
        this.ligne_max = ligne_max;
    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public int getRegulateur() {
        return regulateur;
    }

    public void setRegulateur(int regulateur) {
        this.regulateur = regulateur;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    /**
     * Sert à générer le décor du jeu avec son eau ( hostile envers le joueur et
     * le sol )
     */
    public void init() {
        /*
        for (int i = 0; i < ligne_max; i++) {
            for (int j = 0; j < colonne_max; j++) {
                map[i][j] = '-';
            }
        }*/
        char[][] mapcopie = {
            {'-', 'q', 'q', '-', 'q', 'q', '-', 'q', 'q', '-', 'q', 'q', '-'},
            {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
            {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
            {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
            {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
            {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
            {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},};
        map = mapcopie;
    }

    ;

    public void affiche() {
        System.out.println("_______________________________");
        for (int i = 0; i < ligne_max; i++) {
            for (int j = 0; j < colonne_max; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println("_______________________________");
    }

    public Obstacle addObstacle(Obstacle o) {
        obstacles.add(o);
        return o;
    }

    /**
     * Cette fonction va être chargée de la gestion des hangements de position
     * des différents obstacles stockés dans la liste -obstacle-
     */
    public void mAj() {
        int i;
        int j;
        char sol = 'e';
        for (i = 0; i < obstacles.size(); i++) {

            //changement de l'élément sol en fonction de la ligne béton/eau/trottoir
            if (obstacles.get(i).getColonne() > 0 && obstacles.get(i).getColonne() < 7) {
                sol = 'e';
            } else if (obstacles.get(i).getColonne() == 7) {
                sol = 't';
            } else if (obstacles.get(i).getColonne() == 12) {
                sol = 't';
            } else {
                sol = '-';
            }
            //deplacemet
            if (regulateur % obstacles.get(i).getVitesse() == 0) {
                obstacles.get(i).deplacement();
            }
            if (obstacles.get(i).getLigne() > 0 || obstacles.get(i).getLigne() < colonne_max) {//évite un débordement du tableau
                map[obstacles.get(i).getColonne()][obstacles.get(i).getLigne() - obstacles.get(i).getSens()] = sol;
            }
            if (obstacles.get(i).getLigne() > -1 && obstacles.get(i).getLigne() < colonne_max) {//évite un débordement du tableau
                map[obstacles.get(i).getColonne()][obstacles.get(i).getLigne()] = obstacles.get(i).getType();
            } else {// repositionne l'obstacle à l'autre bord du tableau
                //obstacles.remove(i);
                if (obstacles.get(i).getSens() == 1) {
                    obstacles.get(i).setLigne(0);
                } else {
                    obstacles.get(i).setLigne(colonne_max - 1);
                }
            }
        }
        if (regulateur < 10000) {
            regulateur++;
        } else {
            regulateur = 0;
        }
        try {

            Thread.sleep(100);
        } catch (InterruptedException e) {

            System.out.println("erreur dans la pause");
        }
    }

    public void lvl1() {
        obstacles.clear();
        addObstacle(new Voiture(0, 11, 1, 1));
        addObstacle(new Voiture(6, 11, 1, 1));
        addObstacle(new Voiture(8, 11, 1, 1));
        addObstacle(new Voiture(2, 2, 1, 1));
        addObstacle(new Voiture(1, 4, 1, -1));
    }

    public void run() {
        lvl1();
        int i = 0;

        for (i = 0; i < 400; i++) {
            affiche();
            mAj();
        }
    }
}

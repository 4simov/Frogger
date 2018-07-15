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
public class Voiture extends Obstacle {

    public Voiture(int x, int y, int v, int s) {
        super(x, y, v, s);
        super.setHostile(true);
        super.setType('/');
    }

}

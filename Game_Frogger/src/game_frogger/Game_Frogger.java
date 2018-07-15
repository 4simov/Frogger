/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_frogger;

import Map.Map;
import Obstacles.Voiture;

/**
 *
 * @author coco
 */
public class Game_Frogger {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Map lvl1 = new Map(13, 13);
        lvl1.run();
    }

}

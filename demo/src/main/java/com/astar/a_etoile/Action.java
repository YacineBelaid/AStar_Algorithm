
package com.astar.a_etoile;

/**
 * Classe abstraite représentant une action.
 * Une action peut être exécutée dans un état du monde (voir méthode Monde::executer).
 */
public class Action {
    
    public Action(int cout){
        this.cout = cout;
    }
    protected int cout;
    
}

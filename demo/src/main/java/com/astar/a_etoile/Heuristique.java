
package com.astar.a_etoile;

/**
 *
 * Interface générique d'une heuristique.
 */
public interface Heuristique {
    
    /** Retourne une estimation du coût restant pour atteindre le but b à partir de l'état du monde e.
     *  Pour être admissible, cette fonction ne doit jamais surestimer le coût restant.
     */
    public int estimerCoutRestant(Etat e, But b);
    
}


package com.astar.a_etoile;

/**
 * Heuristique par défaut retournant zéro (0) (ne donnant aucune information).
 */
public class HeuristiqueNulle implements Heuristique{

    @Override
    public int estimerCoutRestant(Etat e, But b) {
        return 0;
    }
    
    
}

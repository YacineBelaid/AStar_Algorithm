
package com.astar.a_etoile;

/**
 * Classe abstraite représentant un but.
 * Un but peut être satisfait dans un état du monde.
 */
public interface But {
    /** Retourne vrai ssi l'état e satisfait ce but. */
    public boolean butSatisfait(Etat e);
}

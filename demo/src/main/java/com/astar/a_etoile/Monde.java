
package com.astar.a_etoile;

import java.util.List;

/**
 *  Interface d'un monde (environnement ou application).
 *  
 */
public interface Monde {
    /** Retourne la liste des actions exécutables dans l'état de ce monde. */
    public List<Action> getActions(Etat e);
    
    /** Exécute une action dans un état du monde et retourne un nouvel état résultat. */
    public Etat executer(Etat e, Action a);
}

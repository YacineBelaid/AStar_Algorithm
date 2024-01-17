
package com.astar.a_etoile;

/**
 *  Classe abstraite représentant un état du monde.
 *  Une instance contient quelques variables spécifiques à l'implémentation de A*,
 *  comme les valeurs f=g+h, parent et actionDepuisParent.
 */
public abstract class Etat implements Comparable<Etat> {
       
    // Variables internes pour l'algorithme A*.
    /** État précédent permettant d'atteindre cet état. */
    protected Etat            parent;
    /** Action à partir de parent permettant d'atteindre cet état. */
    protected Action          actionDepuisParent;
    /** f=g+h. */
    protected double          f;
    /** Meilleur coût trouvé pour atteindre cet été à partir de l'état initial. */    
    protected double          g;
    /** Estimation du coût restant pour atteindre le but. */
    protected double          h;

}

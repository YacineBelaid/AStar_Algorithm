package com.astar.sokoban;

import java.io.*;
import java.util.*;
import com.astar.a_etoile.*;;
/**
 * Point d'entrée de la demo B (jeu sokoban).
 * Attention : 
 *  - Ne déplacez pas ce fichier source. Il doit être dans le package racine.
 */
public class Sokoban {
    
    public static void main(String args[]) throws IOException {
        Reader reader = args.length>=1 ? new FileReader(args[0]) : new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        Probleme probleme = Probleme.charger(br);
        List<Action> plan = AEtoile.genererPlan(probleme.grille, probleme.etatInitial, probleme.but, probleme.heuristique);
        if(plan==null)
            System.out.println("<Aucune solution>");
        else
            for(Action action : plan){
                System.out.print(action + " ");
            }
    }
}

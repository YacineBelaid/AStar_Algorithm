package com.astar.a_etoile;
import java.util.*;
import javax.swing.plaf.synth.SynthDesktopIconUI;
import com.astar.npuzzle.PuzzleEtat;
import com.astar.sokoban.EtatSokoban;

public class AEtoile {

    public static List<Action> genererPlan(Monde monde, Etat etatInitial, But but, Heuristique heuristique){
        long starttime = System.currentTimeMillis();
        
        //modifier pour que floor soit le f le plus petit
        TreeSet<Etat> ouvert = new TreeSet<>();
        TreeSet<Etat> ferme = new TreeSet<>();

        //premier etat initialisation
        etatInitial.g = 0;
        etatInitial.h = heuristique.estimerCoutRestant(etatInitial, but);
        etatInitial.f = etatInitial.h;
        etatInitial.parent = null;

        ouvert.add(etatInitial);

        int iteration = 0;
        Etat etatCourrant = null;

        while(!ouvert.isEmpty()){

            Etat EtatMinimum = null;
            for (Etat e : ouvert) {
                if (EtatMinimum == null || e.f < EtatMinimum.f) {
                    EtatMinimum = e;
                }
            }

            iteration++;
            etatCourrant = EtatMinimum;

            if (but.butSatisfait(etatCourrant)) {
                break;
            }
                
            ouvert.remove(etatCourrant);
            ferme.add(etatCourrant);

            for(Action action : monde.getActions(etatCourrant)){

                Etat etatSubsequent = monde.executer(etatCourrant, action);

                if (!ferme.contains(etatSubsequent)) {

                    etatSubsequent.g = etatCourrant.g + action.cout;
                    etatSubsequent.h = heuristique.estimerCoutRestant(etatSubsequent, but);
                    etatSubsequent.f = etatSubsequent.g + etatSubsequent.h;
                    etatSubsequent.parent = etatCourrant;
                    etatSubsequent.actionDepuisParent = action;
                    
                    if (ouvert.contains(etatSubsequent)) {
                        for(Etat etat : ouvert){
                            if (etat.equals(etatSubsequent)){
                                if (etat.f > etatSubsequent.f){
                                    ouvert.remove(etat);
                                    ouvert.add(etatSubsequent);
                                }
                                break;
                            }
                        }
                    } else {
                        ouvert.add(etatSubsequent);
                    }
                }
            }
        }   

        

        long lastDuration = System.currentTimeMillis() - starttime;
        // Les lignes écrites débutant par un dièse '#' seront ignorées par le valideur de solution.
        System.out.println("# Nombre d'etats generes : " + (ouvert.size() + ferme.size()));
        System.out.println("# Nombre d'etats visites : " + ferme.size());
        System.out.println("# Duree : " + lastDuration + " ms");
    
        if (!but.butSatisfait(etatCourrant)) {
            return null;
        }

        List<Action> plan = new ArrayList<>();
        while (etatCourrant != null && etatCourrant.actionDepuisParent != null) {
            plan.add(0, etatCourrant.actionDepuisParent);
            etatCourrant = etatCourrant.parent;
        }
        
        System.out.println("# Cout/longueur du plan: " + plan.size());

        return plan;
    } 
}
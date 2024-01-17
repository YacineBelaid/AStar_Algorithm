package com.astar.sokoban;

import java.util.ArrayList;

import com.astar.a_etoile.But;
import com.astar.a_etoile.Etat;
import com.astar.a_etoile.Heuristique;
import com.astar.sokoban.EtatSokoban.Position;

public class SokobanHeuristique implements com.astar.a_etoile.Heuristique {

    @Override
    public int estimerCoutRestant(Etat e, But b) {
        EtatSokoban etat = (EtatSokoban) e;
        EtatSokoban but = ((ButSokoban) b).but;
        int heuristique = 0;

        //on pourrait ajouter un poid a chacun des calculs
        //heuristique += distanceAgentBlocs(etat.blocs, etat.agent);
        heuristique += distanceBlocsObjectifs(etat.blocs, but.blocs);
        heuristique += distanceAgentProchainBloc(etat.agent, etat.blocs, but.blocs);
        //heuristique += nombreContournements(etat.blocs, but.blocs);
        
        return heuristique;
    }
    
    public int distanceManhattan(int i, int j, int x, int y){
        return (Math.abs(i-x) + Math.abs(j-y));
    }
    public int distanceManhattan(Position p1, Position p2){
        return distanceManhattan(p1.x, p1.y, p2.x, p2.y);
    }

    /*public int nombreContournements(ArrayList<Position> blocs, ArrayList<Position> objectifs){
        int heuristique = 0;

        for (Position bloc : blocs){
            if (!estSurObjectif(bloc, objectifs)){
              //...  
            }
        }

        return heuristique;
    }*/

    public int distanceAgentProchainBloc(Position agent, ArrayList<Position> blocs, ArrayList<Position> objectifs){
        int heuristique = 0;
        
        for (Position bloc : blocs){
            //if (!estSurObjectif(bloc, objectifs) && heuristique > distanceManhattan(bloc, agent)){
            if (heuristique > distanceManhattan(bloc, agent)){
                heuristique = distanceManhattan(bloc, agent);
            };

        }

        //-1 parce que l'agent ne peut pas etre sur un bloc
        return heuristique - 1;
    }

    //affectation simple, premier arrivé, premier servi
    public int distanceBlocsObjectifs(ArrayList<Position> blocs, ArrayList<Position> objectifs){
        int heuristique = 0;

        // Créer une copie des objectifs pour pouvoir les supprimer au fur et à mesure qu'ils sont attribués
        ArrayList<Position> objectifsRestants = new ArrayList<>(objectifs);
    
        for (Position bloc : blocs){
            Position objectifProche = null;
            int minDistance = Integer.MAX_VALUE;
    
            // Trouver l'objectif le plus proche qui n'a pas encore été attribué
            for (Position objectif : objectifsRestants){
                int distance = distanceManhattan(bloc, objectif);
                if (distance < minDistance){
                    minDistance = distance;
                    objectifProche = objectif;
                }
            }
    
            // Supprimer l'objectif qui a été attribué
            objectifsRestants.remove(objectifProche);
    
            heuristique += minDistance;
        }
    
        return heuristique;
    }

    public boolean estSurObjectif(Position bloc, ArrayList<Position> objectifs){
        for (Position objectif : objectifs) {
            if (bloc.equals(objectif)) {
                return true;
            }
        }
        return false;
    }

    public int distanceAgentBlocs(ArrayList<Position> blocs, Position agent){
        int heuristique = 0;

        for(Position bloc : blocs){
            //if (estSurObjectif(bloc, blocs)) {
                heuristique += distanceManhattan(bloc, agent);
            //}
        }

        //moins le nombre de blocs parce qu'un agent ne peut pas etre sur un bloc
        return heuristique - blocs.size();
    }
}

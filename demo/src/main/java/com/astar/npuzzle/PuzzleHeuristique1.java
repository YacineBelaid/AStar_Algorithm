
package com.astar.npuzzle;
import com.astar.a_etoile.But;
import com.astar.a_etoile.Etat;

public class PuzzleHeuristique1 implements com.astar.a_etoile.Heuristique {
    
    @Override
    public int estimerCoutRestant(Etat e, But but) {

        int[][] etat = ((PuzzleEtat) e).cases;
        int[][] grilleBut = ((PuzzleBut) but).but.cases;

        int heuristique = 0;

        for(int y = 0; y < grilleBut.length; y++){
            for (int x = 0; x < grilleBut[y].length; x++){
                if (etat[y][x] != grilleBut[y][x] && etat[y][x] != 0) {
                    //valeur = etat[y][x]
                    heuristique += calculerDistanceManhattan(grilleBut, etat[y][x], x, y);
                }
            }
        } 

        return heuristique;
    }
    
    private int calculerDistanceManhattan(int[][] grilleBut, int valeur, int longitude, int lattitude){
        for (int y = 0; y < grilleBut.length; y++){
            for (int x = 0; x < grilleBut[y].length; x++){
                if (grilleBut[y][x] == valeur){
                    return (Math.abs(lattitude - y) + Math.abs(longitude - x));
                }
            }
        }

        System.out.println("erreur dans calcul de manhattan");
        return 0;
    }
}

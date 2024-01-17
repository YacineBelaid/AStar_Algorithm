
package com.astar.sokoban;

import java.util.ArrayList;

import com.astar.sokoban.EtatSokoban.Position;

/**
 * Représente un but.
 */
public class ButSokoban implements com.astar.a_etoile.But {
    EtatSokoban but;

    public ButSokoban(char[][] grille){
        this.but = new EtatSokoban();
        ArrayList<Position> objectifs = new ArrayList<Position>();

        for(int i = 0; i< grille.length; i++){
            for (int j = 0; j < grille[i].length;j++){
                if (grille[i][j] == '.'){
                    objectifs.add(new Position(i, j));
                }
            }
        }

        this.but.blocs = objectifs;
    }
    public ButSokoban(EtatSokoban but){
        this.but = but;
    }
    
    @Override
    public boolean butSatisfait(com.astar.a_etoile.Etat e) {
        ArrayList<Position> blocs = ((EtatSokoban) e).blocs;

        for (Position objectifs  : but.blocs){
            boolean objectifAccompli = false;
            for (Position bloc : blocs){
                if(objectifs.equals(bloc)){
                    objectifAccompli = true;
                    break;
                }
            }

            if(!objectifAccompli){
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString(){
        return but.blocs.toString();
    }
}


package com.astar.npuzzle;

import com.astar.a_etoile.Etat;

public class PuzzleBut implements com.astar.a_etoile.But {

    public PuzzleEtat but;
    
    public PuzzleBut(PuzzleEtat b){
        but = b;
    }
    
    @Override
    public boolean butSatisfait(Etat e) {
        return but.equals(e);
    }
    
}

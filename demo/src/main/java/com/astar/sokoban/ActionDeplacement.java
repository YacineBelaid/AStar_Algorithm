
package com.astar.sokoban;

import com.astar.a_etoile.Action;

public class ActionDeplacement extends Action {
    public char direction;

    public ActionDeplacement(char dir) {
        super(1);
        direction = dir;
    }
    
    @Override
    public String toString(){
        return "" + direction;
    }
}

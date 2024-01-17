
package com.astar.sokoban;

import java.util.*;

import com.astar.a_etoile.Action;
import com.astar.sokoban.EtatSokoban.Position;

/**
 * Dans le jeu de sokoban, le «Monde» est une «Grille».
 */
public class Grille implements com.astar.a_etoile.Monde, com.astar.a_etoile.But {
    
    // À compléter.
    
    // Mettre la représentation d'une grille ici.
    // Indice : tableau pour stocker les obstacles et les buts.
    protected char[][] grille;

    public Grille(){};
    
    public Grille(char[][] grille){
        this.grille = grille;

        for (int i = 0; i < this.grille.length; i++) {
            for (int j = 0; j < this.grille[i].length; j++) {
                char c = this.grille[i][j];
                if (c == '$' || c == '+' || c == '*' || c == '@') {
                    this.grille[i][j] = ' ';
                } else {
                    this.grille[i][j] = c;
                }
            }
        }
    }

    private boolean estDeplacementPossible(int i, int j){
        return (estDansLimite(i,j) && estCaseLibre(i, j));
    }
    private boolean estCaseLibre(int i, int j){
        return (grille[i][j] == ' ' || grille[i][j] == '.');
    }
    private boolean estDansLimite(int i, int j){
        return (i < grille.length && i >= 0) && 
            (j < grille[i].length && j >= 0);
    }
    private boolean estMur(int i, int j){
        return grille[i][j] == '#';
    }
    private boolean estBloc(ArrayList<Position> blocs, int i, int j){
        for(Position bloc : blocs){
            if (bloc.x == i && bloc.y == j){
                return true;
            }
        }
        return false;
    }
    private boolean estObjectif(int i, int j){
        return grille[i][j] == '.';
    }
    
    //////////////////////////////////////////////////////////////////////
    //prendre en consid/ration les deadlocks
    @Override
    public List<com.astar.a_etoile.Action> getActions(com.astar.a_etoile.Etat e) {
        LinkedList<com.astar.a_etoile.Action> actions = new LinkedList<com.astar.a_etoile.Action>();

        Position agent = ((EtatSokoban) e).agent;
        ArrayList<Position> blocs = ((EtatSokoban) e).blocs;

        if (//agent se deplace Nord sans pousser bloc
            (estDeplacementPossible(agent.x-1, agent.y) && !estBloc(blocs, agent.x-1, agent.y)) ||
            //agent se deplace Nord en poussant bloc
            ((estDeplacementPossible(agent.x-2, agent.y) && estBloc(blocs, agent.x-1, agent.y) && !estBloc(blocs, agent.x-2, agent.y)) &&
            //ne cree pas un deadLock
            (estObjectif(agent.x-2,agent.y) || ((!estMur(agent.x-3, agent.y) || (!estMur(agent.x-2, agent.y-1) && !estMur(agent.x-2,agent.y+1))))))
            //verifier s'il s'agit de l'objectif //pas necessairement
            //verifier une pair E-W ou N-S est libre 
            ){
            actions.add(new ActionDeplacement('N'));
        }
        if (//agent se deplace Sud sans pousser bloc
            (estDeplacementPossible(agent.x+1, agent.y) && !estBloc(blocs, agent.x+1, agent.y)) ||
            //agent se deplace Sud en poussant bloc
            ((estDeplacementPossible(agent.x+2, agent.y) && estBloc(blocs, agent.x+1, agent.y) && !estBloc(blocs, agent.x+2, agent.y)) &&
            //pas deadlock
            (estObjectif(agent.x+2, agent.y) || (!estMur(agent.x+3, agent.y) || (!estMur(agent.x+2, agent.y-1) && !estMur(agent.x+2, agent.y+1)))))
            ){
            actions.add(new ActionDeplacement('S'));
        }
        if (//agent se deplace Ouest sans pousser bloc
            (estDeplacementPossible(agent.x, agent.y-1) && !estBloc(blocs, agent.x, agent.y-1)) ||
            //agent se deplace Ouest en poussant bloc
            ((estDeplacementPossible(agent.x, agent.y-2) && estBloc(blocs, agent.x, agent.y-1) && !estBloc(blocs, agent.x, agent.y-2)) &&
            //pas deadlock
            (estObjectif(agent.x, agent.y-2) || (!estMur(agent.x, agent.y-3) || (!estMur(agent.x+1, agent.y-2) && !estMur(agent.x-1, agent.y-2)))))
            ){
            actions.add(new ActionDeplacement('W'));
        }
        if (//agent se deplace Est sans pousser bloc
            (estDeplacementPossible(agent.x, agent.y+1) && !estBloc(blocs, agent.x, agent.y+1)) ||
            //agent se deplace Est en poussant bloc
            ((estDeplacementPossible(agent.x, agent.y+2) && estBloc(blocs, agent.x, agent.y+1) && !estBloc(blocs, agent.x, agent.y+2)) &&
            //pas deadlock
            (estObjectif(agent.x, agent.y+2) || (!estMur(agent.x, agent.y+3) || (!estMur(agent.x+1, agent.y+2) && !estMur(agent.x-1, agent.y+2)))))
            ){
            actions.add(new ActionDeplacement('E'));
        }

        return actions;
    }

    @Override
    public com.astar.a_etoile.Etat executer(com.astar.a_etoile.Etat e, com.astar.a_etoile.Action a) {
        ActionDeplacement action = (ActionDeplacement) a;
        EtatSokoban etat = (EtatSokoban) e;
        EtatSokoban etatSubsequent = etat.clone();

        //patch
        etatSubsequent.setParent(etat);

        switch(action.direction){
            case('W'):
                etatSubsequent.deplacementOuest();
                break;
            case('E'):
                etatSubsequent.deplacementEst();
                break;
            case('N'):
                etatSubsequent.deplacementNord();
                break;
            case('S'):
                etatSubsequent.deplacementSud();
                break;
            default:
                throw new Error("Erreur dans Grille.executer()!");
        }

        return etatSubsequent;
    }
    
    /** Retourne */
    @Override
    public boolean butSatisfait(com.astar.a_etoile.Etat e){
        EtatSokoban etat = (EtatSokoban) e;

        for(Position bloc :  etat.blocs){
            if (this.grille[bloc.x][bloc.y] != '.'){
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString(){
        String string = "";

        for (int i = 0; i < grille.length; i++){
            for (int j = 0; j < grille[i].length; j++) {
                string += grille[i][j];
            }
            string += "\n";
        }

        return string;
    }
}
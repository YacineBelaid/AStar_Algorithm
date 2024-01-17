
package com.astar.sokoban;

import java.util.ArrayList;
import java.util.Collections;

import com.astar.a_etoile.Etat;

/**
 * Représente un état d'un monde du jeu Sokoban.
 */

public class EtatSokoban extends Etat {

    //voir si meilleur manière
    protected Position agent;
    protected ArrayList<Position> blocs;


    public EtatSokoban(){
        this.blocs = new ArrayList<>();
        this.agent = new Position();
    };
    public EtatSokoban(char[][] grille){
        this.blocs = new ArrayList<>();

        for(int i = 0; i<grille.length; i++){
            for (int j = 0; j < grille[i].length; j++){
                if (grille[i][j] == '@'){
                    this.agent = new Position(i, j);
                }
                if (grille[i][j] == '$'){
                    this.blocs.add(new Position(i, j));
                }
            }
        }
    }
    
    public EtatSokoban(EtatSokoban e){
        this.agent = new Position(e.agent.x, e.agent.y);
        this.blocs = new ArrayList<Position>();
        for (Position bloc : e.blocs){
            this.blocs.add(new Position(bloc.x, bloc.y));
        }
    }

    @Override
    public EtatSokoban clone() {
        return new EtatSokoban(this);
    }

    //patch
    public void setParent(EtatSokoban e){
        this.parent = e;
    }

    //deplacement
    public void deplacementOuest(){
        this.agent.y--;
        for(Position bloc : this.blocs){
            if (this.agent.equals(bloc)){
                bloc.y--;
                break;
            }
        }
    };
    public void deplacementEst(){
        this.agent.y++;
        for(Position bloc : this.blocs){
            if (this.agent.equals(bloc)){
                bloc.y++;
                break;
            }
        }
    };
    public void deplacementNord(){
        this.agent.x--;
        for(Position bloc : this.blocs){
            if (this.agent.equals(bloc)){
                bloc.x--;
                break;
            }
        }
    };
    public void deplacementSud(){
        this.agent.x++;
        for(Position bloc : this.blocs){
            if (this.agent.equals(bloc)){
                bloc.x++;
                break;
            }
        }
    };

    //pour TreeSet
    @Override
    public int compareTo(Etat o) {
        EtatSokoban es = (EtatSokoban) o;

        int agentComparison = this.agent.compareTo(es.agent);
        if (agentComparison != 0) {
            return agentComparison;
        }

        ArrayList<Position> listeOrdonnee1 = new ArrayList<>(this.blocs);
        ArrayList<Position> listeOrdonnee2 = new ArrayList<>(es.blocs);
        Collections.sort(listeOrdonnee1);
        Collections.sort(listeOrdonnee2);

        for (int i = 0; i < listeOrdonnee1.size(); i++) {
            int comparaisonBlock = listeOrdonnee1.get(i).compareTo(listeOrdonnee2.get(i));
            if (comparaisonBlock != 0) {
                return comparaisonBlock;
            }
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj){
        EtatSokoban e = (EtatSokoban) obj;

        if (this.agent.equals(e.agent)){

            ArrayList<Position> listeOrdonnee1 = new ArrayList<>(this.blocs);
            ArrayList<Position> listeOrdonnee2 = new ArrayList<>(e.blocs);
            Collections.sort(listeOrdonnee1);
            Collections.sort(listeOrdonnee2);
        
            return listeOrdonnee1.equals(listeOrdonnee2);
        }

        return false;
    }
    @Override
    public String toString(){
        String string = "Agent " + this.agent.toString() + " Blocs: ";
        for(Position bloc : this.blocs){
            string+= bloc.toString() + " ";
        }

        return string;
    }

    //position des blocks
    public static class Position implements Comparable<Position> {
        protected int x;
        protected int y;

        public Position(){}
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj){
            Position p = (Position) obj;
            return (this.x == p.x && this.y == p.y);
        }
        @Override
        public int compareTo(Position p){
            if (this.x < p.x){
                return -1;
            } else if (this.x > p.x) {
                return 1;
            } else {
                if (this.y < p.y){
                    return -1;
                } else if (this.y > p.y) {
                    return 1;
                }
            }
            return 0;
        }  
        @Override
        public int hashCode() {
            int result = Integer.hashCode(x);
            result = 31 * result + Integer.hashCode(y);
            return result;
        }
        @Override
        public String toString(){
            return "(" + x + ","+ y + ")";
        }
    }
}

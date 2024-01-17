
package com.astar.npuzzle;
import com.astar.a_etoile.Etat;
import java.util.StringTokenizer;

public class PuzzleEtat extends com.astar.a_etoile.Etat {
    protected int[][]   cases;
    protected int       xVide, yVide;
    
    
    public PuzzleEtat(int[][] initcases){
        cases = new int[initcases.length][initcases[0].length];
        for(int x=0;x<cases.length;x++)
            for(int y=0;y<cases.length;y++){
                cases[y][x] = initcases[y][x];
                if(cases[y][x]==0){
                    xVide = x;
                    yVide = y;
                }
            }
    }
    
    public PuzzleEtat(PuzzleEtat noeud){
        cases = new int[noeud.cases.length][noeud.cases[0].length];
        for(int x=0;x<cases.length;x++)
            for(int y=0;y<cases.length;y++)
                cases[y][x] = noeud.cases[y][x];
        xVide = noeud.xVide;
        yVide = noeud.yVide;
    }
    
    @Override
    public PuzzleEtat clone(){
        return new PuzzleEtat(this);
    }
    
    public void deplaceEst(){
        cases[yVide][xVide] = cases[yVide][++xVide];
        cases[yVide][xVide] = 0;
    }
    public void deplaceOuest(){
        cases[yVide][xVide] = cases[yVide][--xVide];
        cases[yVide][xVide] = 0;
    }
    public void deplaceNord(){
        cases[yVide][xVide] = cases[--yVide][xVide];
        cases[yVide][xVide] = 0;
    }
    public void deplaceSud(){
        cases[yVide][xVide] = cases[++yVide][xVide];
        cases[yVide][xVide] = 0;
    }

    @Override
    public int compareTo(Etat obj) {
    	PuzzleEtat pz = (PuzzleEtat) obj;
        for(int y=0;y<cases.length;y++)
            for(int x=0;x<cases[0].length;x++){
                if(cases[y][x] < pz.cases[y][x])
                   return -1;
                if(cases[y][x] > pz.cases[y][x])
                   return 1;
            }
        return 0;
    }
    @Override
    public int hashCode() {
        int code=0, m=1;
        for(int x=0;x<cases.length;x++)
            for(int y=0;y<cases.length;y++)
                code += cases[y][x] * m++;
        return code;
    }    
    
    @Override
    public boolean equals(Object obj) {
        PuzzleEtat pz = (PuzzleEtat) obj;
        for(int y=0;y<cases.length;y++)
            for(int x=0;x<cases[0].length;x++)
                if(cases[y][x]!=pz.cases[y][x])
                    return false;
        return true;
    }
    
    
    static public PuzzleEtat parsePuzzle(String puzzlestring){
        StringTokenizer tokens = new StringTokenizer(puzzlestring);
        int compte = tokens.countTokens();
        int n = (int) Math.sqrt(compte);
        if(n*n != compte)
            throw new Error("Erreur: le puzzle n'est pas un carré parfait!");
        int[][] cases = new int[n][n];
        for(int y=0;y<n;y++)
            for(int x=0;x<n;x++)
                cases[y][x] = Integer.parseInt(tokens.nextToken());
        return new PuzzleEtat(cases);
    }

    @Override
    public String toString(){
        String r = "Puzzle : g=" + g + "  f=" + f + "  vide=(" + xVide + "," + yVide + ")\n" ;
        for(int y=0;y<cases.length;y++){
            for(int x=0;x<cases.length;x++)
                r += "" + cases[y][x] + " ";
            r += "\n";
        }
        return r;
    }
}
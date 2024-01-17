
package com.astar.npuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SimPuzzle {
    public static void main(String[] args) throws Exception{
                BufferedReader reader;
        if(args.length>1)
            reader = new BufferedReader(new FileReader(args[0]));
        else
            reader = new BufferedReader(new InputStreamReader(System.in));
        
        String ligne = null;
        while((ligne=reader.readLine())!=null && !ligne.isEmpty()){
            PuzzleMonde monde = new PuzzleMonde();
            PuzzleEtat etat = PuzzleEtat.parsePuzzle(ligne);
            ligne = reader.readLine();
            StringTokenizer tokens = new StringTokenizer(ligne);
            while(tokens.hasMoreTokens()){
                String action = tokens.nextToken();
                etat = (PuzzleEtat) monde.executer(etat, new PuzzleAction(action.charAt(0)));
                System.out.println(etat.toString());
                System.out.println("");
            }
        }
    }
}

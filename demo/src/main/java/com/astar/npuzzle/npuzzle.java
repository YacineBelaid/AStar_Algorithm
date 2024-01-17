package com.astar.npuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.List;
import com.astar.a_etoile.*;

public class npuzzle {
    public static void main(String args[]) throws Exception{
        BufferedReader reader;
        if(args.length>=1)
            reader = new BufferedReader(new FileReader(args[0]));
        else
            reader = new BufferedReader(new InputStreamReader(System.in));
        
        String ligne = null;
        while((ligne=reader.readLine())!=null && !ligne.isEmpty()){
            PuzzleMonde monde = new PuzzleMonde();
            PuzzleEtat e1 = PuzzleEtat.parsePuzzle(ligne);
            PuzzleEtat e2 = PuzzleEtat.parsePuzzle(reader.readLine());
            PuzzleBut b = new PuzzleBut(e2);
            PuzzleHeuristique1 h = new PuzzleHeuristique1();
            List<Action> plan = AEtoile.genererPlan(monde, e1, b, h);
            if(plan!=null){
                for(Action a : plan)
                    System.out.print(a + " ");
                System.out.println("");
            }else
                System.out.println("<aucune solution>");
        }
    }
}

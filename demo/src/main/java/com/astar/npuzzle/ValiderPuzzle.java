
package com.astar.npuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class ValiderPuzzle {
    public static void main(String[] args) {
        int nbOK=0, nbOptimal=0;
        try{
            BufferedReader readerProblems = new BufferedReader(new FileReader(args[0]));
            BufferedReader readerResultat = new BufferedReader(new FileReader(args[1]));
            BufferedReader readerSolution = new BufferedReader(new FileReader(args[2]));

            while(readerProblems.ready()){
                String ligne1=null, ligne2=null;
                do{
                    ligne1=readerProblems.readLine();
                }while(ligne1!=null && ligne1.isEmpty());
                if(ligne1==null)
                    break;

                ligne2=readerProblems.readLine();

                PuzzleEtat etatinitial = PuzzleEtat.parsePuzzle(ligne1);
                PuzzleEtat etatfinal = PuzzleEtat.parsePuzzle(ligne2);
                PuzzleMonde monde = new PuzzleMonde();

                String plan = readerResultat.readLine();
                while(plan!=null && plan.startsWith("#"))
                    plan = readerResultat.readLine();

                String solution = readerSolution.readLine();
                while(solution!=null && solution.startsWith("#"))
                    solution = readerSolution.readLine();

                int nbActionsSolution = solution==null || solution.isEmpty() ? 0 : solution.split(" ").length;
                //System.out.println("solution=" + solution);
                //System.out.println("#Longueur solution: " + nbActionsSolution);

                int nbactions=0;
                PuzzleEtat etat = etatinitial.clone();
                if(plan!=null && !plan.isEmpty()){
                    StringTokenizer actions = new StringTokenizer(plan);
                    while(actions.hasMoreTokens()){
                        String action = actions.nextToken();
                        nbactions++;
                        etat = (PuzzleEtat) monde.executer(etat, new PuzzleAction(action.charAt(0)));
                        //System.out.println(etat.toString());
                        //System.out.println("");
                    }
                }

                boolean OK = etat.compareTo(etatfinal)==0;
                boolean optimal = OK && nbactions<=nbActionsSolution;
                if(OK) nbOK++;
                if(optimal) nbOptimal++;
                if(optimal) System.out.print("optimal");
                else if(OK) System.out.print("ok");
                else if(OK) System.out.print("echec");
                System.out.println(" " + nbactions);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(nbOK + " " + nbOptimal);
    }
}


package com.astar.sokoban;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *  Représente un problème chargé d'un fichier test sokoban??.txt.
 */
public class Probleme {
    public Grille grille;
    public EtatSokoban etatInitial;
    public ButSokoban but;
    public SokobanHeuristique heuristique;
    
    private Probleme(){
    }
    
    public static Probleme charger(BufferedReader br) throws IOException{
        // Lire les lignes dans fichiers
        ArrayList<String> lignes = new ArrayList<String>();
        String ligne;
        while((ligne = br.readLine())!=null && !ligne.isEmpty()){
            lignes.add(ligne);
        }
        
        char[][] grilleTemp = new char[lignes.size()][];
        for(int i = 0; i < lignes.size(); i++){
            grilleTemp[i] = lignes.get(i).toCharArray();
        }

        Probleme probleme = new Probleme();
        probleme.heuristique = new SokobanHeuristique();
        probleme.etatInitial = new EtatSokoban(grilleTemp);
        probleme.grille = new Grille(grilleTemp);
        probleme.but = new ButSokoban(grilleTemp);
        
        return probleme;
    }
}
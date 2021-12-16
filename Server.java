package model;

import model.Membre;
import model.Offre;

import java.util.*;

public class Server {
	public static boolean admin=false;
	public static ArrayList<Membre> listM=new ArrayList<>();
    public static ArrayList<Offre> listO=new ArrayList<>();
    public static ArrayList<Enchere> listE=new ArrayList<>();
    
    public static Membre checkMembre(String nom){
        for(Membre mbr:listM){
            if(mbr.getNom().equals(nom)){
                return mbr;
            }
        }
        return null;
    }
    public static Enchere checkEnchere(int id){
        for(Enchere e:listE){
            if(e.getId()==id){
                return e;
            }
        }
        return null;
    }

}

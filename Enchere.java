package model;

import java.util.*;

public class Enchere {

    public static int cptId=1;
    private int id;
    private String desc;
    private float prix;
    private boolean etat;
    private int temps;
    public List<Offre> offres=new ArrayList<>();

    public Enchere(String desc, float prix, int temps) {
        super();
        this.desc = desc;
        this.prix = prix;
        this.id=cptId++;
        this.temps=temps;
        this.etat=true;
    }

    public void close(){
        this.setEtat(false);
        if(this.offres.size()>0){
            this.offres.get(this.offres.size()-1).setGagnant(true);
        }
    }

    public int getTemps() {
        return temps;
    }
    public void setTemps(int temps) {
        this.temps = temps;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public float getPrix() {
        return prix;
    }
    public void setPrix(float prix) {
        this.prix = prix;
    }
    public boolean getEtat() {
        return etat;
    }
    public void setEtat(boolean etat) {
        this.etat = etat;
    }


    public float meilleureO() {
        if(offres.size()>0){
            return offres.get(offres.size()-1).getPrix();
        }
        return prix;
    }
}

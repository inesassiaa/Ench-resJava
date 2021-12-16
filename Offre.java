package model;

import model.Membre;

public class Offre {

	private static int cptId=1;
	private int id;
	private Membre m;
	private Enchere e;
	private float prix;
	private boolean gagnant=false;
	public Offre(Membre m, Enchere e, float prix) {
		super();
		this.id=cptId++;
		this.m = m;
		this.e = e;
		this.prix = prix;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Membre getM() {
		return m;
	}
	public void setM(Membre m) {
		this.m = m;
	}
	public Enchere getE() {
		return e;
	}
	public void setE(Enchere e) {
		this.e = e;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public boolean isGagnant() {
		return gagnant;
	}
	public void setGagnant(boolean gagnant) {
		this.gagnant = gagnant;
	}
	
	
	

}

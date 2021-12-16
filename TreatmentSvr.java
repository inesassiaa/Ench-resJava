package server;

import java.io.*;
import java.net.*;
import model.*;

public class TreatmentSvr extends Thread {
	private Socket s;
	private boolean isAdmin=false;
	private Membre mbr = null;

	public TreatmentSvr(Socket s) {
		super();
		this.s = s;
	}

	@Override
	public void run() {
		try {
			InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader in_sc = new BufferedReader(in);

            OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
            PrintWriter out_sc = new PrintWriter(new BufferedWriter(out), true);
            
            while(true) {
                String m = in_sc.readLine();
                if(m.startsWith("LOGIN ") && m.length()>6) {
                	String nom=m.substring(6);
                	if(Server.checkMembre(nom)==null) {
            			this.mbr=new Membre(nom);
            			Server.listM.add(mbr);
                		out_sc.println("Account successfully created with id: "+mbr.getId());
                		break;
	            	}else {
	            		out_sc.println("Name "+nom+" already in use");
	            	}
                }else if(m.equals("ADMIN")){
                	if(Server.admin==false) {
                		Server.admin=true;
                		out_sc.println("Bienvenu cher Admin");
                		this.isAdmin=true;
                		break;
                	}else {
                		out_sc.println("Admin déja connecté");
                	}
                }else {
            		out_sc.println("Tapez LOGIN (suivi de votre nom) ou ADMIN d'abord");
                }
            }
            if(this.isAdmin) {
            	while(true) {
	            	String m = in_sc.readLine();
	            	if(m.startsWith("ENCHERE ")) {
	            		String cd=m.substring(8);
	            		String t[]=cd.split("##");
						Enchere e = new Enchere(t[0], Float.parseFloat(t[1]), Integer.parseInt(t[2]));
	            		Server.listE.add(e);
	            		ChronoEnchere c = new ChronoEnchere(e);
						c.start();
	     				out_sc.println("Nouvelle enchere ajoutée ");

	            	}else {
						out_sc.println("Commande non reconnue ");
					}
	            }
            	
            }else {
	            while(true) {
	            	String m = in_sc.readLine();
	            	if(m.equals("ENCHERES")) {
	         			String s="";
	         			for(Enchere e: Server.listE) {
	         				s+=e.getId()+"#"+e.getDesc()+"#"+e.getPrix()+"#"+e.getEtat()+"#"+e.meilleureO()+"///";
	         			}
	         			if(s.length()>0) {
	         				out_sc.println(s);
	         			}
	         			else {
	         				out_sc.println("Aucune Enchere dispo");
	         			}
	            		
	            	}else if (m.startsWith("OFFRE ")) {
						String cd=m.substring(6);
						String t[]=cd.split("##");
						Enchere e=null;
						for (Enchere e1:Server.listE) {
							if(e1.getId()==Integer.parseInt(t[0])){
								e=e1;
								break;
							}
						}
						if(e!=null || e.getEtat()==false){
							float p=e.meilleureO();
							if(Float.parseFloat(t[1])>p){
								Offre o = new Offre(this.mbr, e, Float.parseFloat(t[1]));
								Server.listO.add(o);
								this.mbr.offres.add(o);
								o.getE().offres.add(o);
								out_sc.println("Nouvelle Offre ajoutée ");
							}else {
								out_sc.println("Offre invalide ");
							}
						}else {
							out_sc.println("Enchere introuvable ou déja fermée");
						}
					}else if(m.equals("ENCHEREC")) {
						String s="";
						for(Enchere e: Server.listE) {
							if(e.getEtat()==true) {
								s += e.getId() + "#" + e.getDesc() + "#" + e.getPrix() + "#" + e.getEtat() + "#" + e.meilleureO() + "///";
							}
						}
						if(s.length()>0) {
							out_sc.println(s);
						}
						else {
							out_sc.println("Aucune Enchere en cours");
						}
					}else if(m.equals("OFFREG")) {
						String s="";
						for(Offre o: this.mbr.offres) {
							if(o.isGagnant()==true) {
								s += o.getId() + "#" +o.getE().getId()+"#"+o.getPrix()+"///";
							}
						}
						if(s.length()>0) {
							out_sc.println(s);
						}
						else {
							out_sc.println("Aucune offre gagnante");
						}
					}else if(m.equals("OFFRES")) {
						String s="";
						for(Offre o: this.mbr.offres) {
							s += o.getId() + "#" +o.getE().getId()+"#"+o.getPrix()+"///";
						}
						if(s.length()>0) {
							out_sc.println(s);
						}
						else {
							out_sc.println("Aucune offre dispo");
						}
					}else {
						out_sc.println("Commande non reconnue");
					}
							          	
	            }
            }
			
			
			
			
		}catch(

	Exception e)
	{

	}
}

}

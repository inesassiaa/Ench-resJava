package server;

import model.*;

public class ChronoEnchere extends Thread{
    private Enchere e;

    public ChronoEnchere(Enchere e) {
        this.e = e;
    }

    @Override
    public void run() {
        try {
            sleep(e.getTemps()*(long)60000);
        }catch (Exception e){

        }
        this.e.close();
    }
}

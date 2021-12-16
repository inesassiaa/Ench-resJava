package server;

import java.io.*;
import java.net.*;

public class ProcessServer {
    public static void main(String[] args) throws IOException{
        ServerSocket sc =new ServerSocket(5000);
        while(true) {
            Socket s= sc.accept();
            TreatmentSvr t=new TreatmentSvr(s);
            t.start();
        }
    }
}

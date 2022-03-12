package com.example.einzelbeispiel;

import java.io.*;
import java.net.*;

public class TCPClient implements Runnable{
    private String matnr = "";
    private String response = "Error";

    public void setMatnr(String s){
        this.matnr = s;
    }
    public String getResponse(){
        return this.response;
    }

    @Override
    public void run(){
        Socket s = null;
        try {
            s = new Socket("se2-isys.aau.at", 53212);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
            dos.writeBytes(matnr + '\n');
            response = b.readLine();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package echoserver;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoThread extends Thread {

    private Socket s;
    public int flag=0;

    public EchoThread(Socket s) {

        this.s = s;
    }

    public void run() {
        
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
            while (true) {
                String str=in.readLine();
                switch (str) {
                    case "maiuscole: on":
                        flag=1;
                        out.println("Comando ricevuto: "+str);
                        break;
                    case "maiuscole: off":
                        flag=0;
                        out.println("Comando ricevuto: "+str);
                        break;
                    case "fine":
                        s.close();
                        break;
                    default:
                        if(flag==0){
                            out.println(str);
                        }
                        else{
                            out.println(str.toUpperCase());
                        }
                        break;
                }
            }
        } catch (IOException ex) {
        }
        
        
    }
}

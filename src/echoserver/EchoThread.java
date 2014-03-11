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
                    case "maiuscolo on":
                        flag=1;
                        str=str.toUpperCase();
                        out.println(str);
                        break;
                    case "maiuscolo off":
                        flag=0;
                        out.println(str);
                        break;
                    case "fine":
                        out.close();
                        break;
                    case "FINE":
                        out.close();
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
            Logger.getLogger(EchoThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}

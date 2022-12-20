import java.util.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.StringBuffer;
public class Client_Side {
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    public Client_Side(){
        try{
            System.out.println("Sending request to server ");
            socket = new Socket("10.2.87.78",7777);
            System.out.println("Connection Established");
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            startReading();
            startWriting();
        }catch (Exception e){

        }
    }
    public void startReading(){
        Runnable r1 = ()-> {
            System.out.println("Reader Started");
            try {
            while (true) {

                    String msg = br.readLine();
                    if(msg.equals("exit")){
                        System.out.println("Server terminated the chat");
                        socket.close();
                        break;
                    }
                    System.out.println("Server : "+msg);
                }
            }catch (Exception e) {
              //e.printStackTrace();
                System.out.println("connection is closed ");
            }
        };
        new Thread(r1).start();
    }
    public void startWriting(){
        Runnable r2 = ()-> {
            System.out.println("Writer started ");
            try {
            while (!socket.isClosed()) {
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String content = br1.readLine();
                    out.println(content);
                    out.flush();
                if(content.equals("exit")){
                    socket.close();
                    break;
                }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(r2).start();
    }

    public static void main(String[] args){
        System.out.println("This is Client side : ");
        new Client_Side();
    }
}

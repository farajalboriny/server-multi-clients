import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    private static ObjectInputStream input;
    private static Socket connection;
    private static final String serverIP = "127.0.0.1";
    private static final int PORT = 1235;

    public void connect() {
        try {
            connection = new Socket(InetAddress.getByName(serverIP), PORT);
            System.out.println("Successfully connected to: " + connection.getInetAddress().getAddress());
            input = new ObjectInputStream(connection.getInputStream());
            System.out.println("input streams are ready");
            Scanner s=new Scanner(input);
            String Messege = new String(input.readAllBytes(),"UTF-8");
            System.out.println(Messege);
            System.out.println("Do you want to close connection (Y/N)");
            Scanner s2=new Scanner(System.in);
            String check=s2.next();
            if(check.equalsIgnoreCase("Y")){
                closeConnection();
            }
            else{
                connect();}

        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void closeConnection(){
       try {
           input.close();
           connection.close();
       }
       catch(Exception e){
           System.out.println(e.getMessage());
       }
    }

    public static void main(String args[]){
        Client obj=new Client();
        obj.connect();
    }

}

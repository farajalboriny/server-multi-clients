import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Stack;

public class Server  {
    private static ObjectOutputStream output;
    private static ServerSocket sock;
    private static Socket connection;
    private static final int PORT = 1235;
    private static Stack<Socket> clients =new Stack<Socket>();

public static Thread t=new Thread(){
public  void run()
{
    try {
        sock = new ServerSocket(PORT, 10);
        System.out.println("Waiting for conncection");
        while(true){
            connection= sock.accept();
            System.out.println("Connected To: " + connection.getInetAddress().getAddress());
            clients.push(connection);
            System.out.println("waiting for new connection");
        }

    }
    catch (Exception e){
        System.out.println(e.getStackTrace());
    }
}};

 public static Thread t2=new Thread(){
  public void run() {
      while(true){
          try {
          Scanner S = new Scanner(System.in);
          String Messege = S.next();
          System.out.println(clients.size());
          while(!clients.isEmpty()) {
              output = new ObjectOutputStream(clients.peek().getOutputStream());
              output.flush();
              byte[] ptext = Messege.getBytes("UTF-8");
              output.write(ptext);
              System.out.println("Messege Sent");
              output.close();
              clients.pop();
          }
      }
      catch(Exception e){
          System.out.println(e.getMessage());
      }

          Scanner s2=new Scanner(System.in);
          System.out.println("Do you want to ShutDown server ? (Y/N)");
          String check=s2.next();
          if(check.equalsIgnoreCase("Y"))
              System.exit(0);
      }

  }
};

public static void main(String args[]) {
    t.start();
    t2.start();
   }

}

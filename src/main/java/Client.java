import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 3306);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF("Client message sent to server");
            dout.flush();
//            Connection connection = DriverManager.getConnection("jdbc:h2:mem:test");
            dout.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

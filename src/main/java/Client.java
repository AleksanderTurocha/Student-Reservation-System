import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        // Connect to the server
        Socket socket = new Socket("localhost", 3307);

        // Read from and write to the socket as needed
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Student Dormitory Reservation System");
        while (true) {
            System.out.println("Menu:");
            System.out.println("1 - Create new Student");
//            System.out.println("2 - Create new Room");
//            System.out.println("3 - Create new Dormitory");
//            System.out.println("4 - Display students");
//            System.out.println("5 - Display rooms");
//            System.out.println("6 - Display dormitories");
//            System.out.println("7 - Find student");
//            System.out.println("8 - Assign student to room and dormitory");
//            System.out.println("9 - Delete student");
            System.out.println("0 - Exit program");


            int choice = scanner.nextInt();
            scanner.nextLine();
            // Creating new student
            if (choice == 1) {
                System.out.println("Create new student:");
                System.out.println("Input first name: ");

                String firstName = scanner.nextLine();

                System.out.println("Input last name: ");
                String lastName = scanner.nextLine();
                System.out.println("wysylam do serwera");
                System.out.println("1;" + firstName + ";" + lastName);
                out.println("1;" + firstName + ";" + lastName);

                String response = in.readLine();
                System.out.println(response + "<-wiadomosc od serwera");
            }
//            // Creating new room
            else if (choice == 2) {
                System.out.println("wybor drigi pisze client");
                out.println("2");
//                System.out.println("Input door number: ");
//                long doorNumber = scanner.nextLong();
//
//                out.println("Created new room: " + doorNumber);
//
//                String response = in.readLine();
//                System.out.println(response);
            }
//            // Creating new dormitory
            else if (choice == 3) {
                System.out.println("Input dormitory name:");
                String name = scanner.nextLine();
                System.out.println("Input dormitory standard:");
                String standard = scanner.nextLine();

                out.println("Created new dormitory " + name + " - " + standard);

                String response = in.readLine();
                System.out.println(response);
            }
            // Display students
            else if (choice == 4) {
                out.println("Displaying students");

                DataInputStream din = new DataInputStream(socket.getInputStream());
                String response = din.readUTF();
                System.out.println("Server response:\n" + response);
            }
            // Display rooms
            else if (choice == 5) {
                out.println("Displaying rooms");

                DataInputStream din = new DataInputStream(socket.getInputStream());
                String response = din.readUTF();
                System.out.println("Server response:\n" + response);
            }
            // Display dormitories
            else if (choice == 6) {
                out.println("Displaying dormitories");

                DataInputStream din = new DataInputStream(socket.getInputStream());
                String response = din.readUTF();
                System.out.println("Server response:\n" + response);
            }
            // Find student
            else if (choice == 7) {
                System.out.println("Input first name: ");
                String name = scanner.nextLine();
                System.out.println("Input last name:");
                String lastName = scanner.nextLine();

                out.println("7;" + name + ";" + lastName);

                DataInputStream din=new DataInputStream(socket.getInputStream());
                String responseData=din.readUTF();
                System.out.println("Server response:\n"+responseData);

                String response = in.readLine();
                System.out.println(response + "<-randomowa wiadomosc od serwera");
            }
            // Assign student to room and dormitory
            else if (choice == 8) {
                //TODO wnetrze
            }
            // Delete student
            else if (choice == 9) {
                //TODO wnetrze
            }
            // Exit program
            else if (choice == 0) {
                System.out.println("Exiting system");
                out.println("uzytkownik opuscil polaczenie");
                socket.close();
                break;
            } else {
                System.out.println("Unknown option, please choose another");
            }
        }

//        socket.close();
    }
}

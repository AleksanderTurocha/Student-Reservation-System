import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.List;

import model.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import static java.lang.Integer.parseInt;

public class Server {
    public static void main(String[] args) throws IOException {
        // Create a server socket and bind it to a port

        ServerSocket serverSocket = new ServerSocket(3307);

        System.out.println("Server listening on port 3307");

        // Listen for incoming connections
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("New client connected");

            // Create a new thread for each incoming connection
            new Thread(() -> handleRequest(socket)).start();
        }

    }

    private static void handleRequest(Socket socket) {
        try {
            while(true) {
                SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession();

                // Read from and write to the socket as needed
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Read the request from the client
                String request = in.readLine();

                String[] words = request.split(";");

                // Create new student
                if (words[0].equals("1")) {
                    Transaction transaction = session.beginTransaction();
                    Student student = new Student();
                    student.setFirstName(words[1]);
                    student.setLastName(words[2]);
                    session.save(student);

                    transaction.commit();
                    session.close();
                    System.out.println("Sesja zamknieta, student zapisany");
                    out.println("Created new student");
                }
                // Create new room
//                else if (words[0].equals("2"))
//                {
//                    session.beginTransaction();
//
//                    Room room = new Room();
//                    room.setDoorNumber(Long.getLong(words[1]));
//
//                    session.save(room);
//
//                    session.getTransaction().commit();
//                    session.close();
//
//                    out.println("Created new room");
//                }
                // Create new dormitory
//                else if(words[0].equals("3"))
//                {
//                    session.beginTransaction();
//
//                    Dormitory dormitory = new Dormitory();
//                    dormitory.setName(words[1]);
//                    dormitory.setStandard(words[2]);
//                    session.save(dormitory);
//
//                    session.getTransaction().commit();
//                    session.close();
//
//                    out.println("Created new dormitory");
//                }
//                // Display students
//                else if (words[0].equals("4"))
//                {
//                    DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
//
//                    String hql = "from Student";
//                    Query query = session.createQuery(hql);
//                    List<Student> studentList = query.list();
//                    String response = "All students:\n";
//                    for(Student student : studentList){
//                        response += student.getFirstName() + " " + student.getLastName() + "\n";
//                    }
//
//                    dout.writeUTF(response);
//                    dout.flush();
//                }
//                // Display rooms
//                else if(words[0].equals("5"))
//                {
//                    DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
//
//                    String hql = "from Room";
//                    Query query = session.createQuery(hql);
//                    List<Room> roomList = query.list();
//                    String response = "All rooms:\n";
//                    for(Room room : roomList){
//                        response += room.getDoorNumber() + "\n";
//                    }
//
//                    dout.writeUTF(response);
//                    dout.flush();
//                }
//                // Display dormitories
//                else if (words[0].equals("6"))
//                {
//                    DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
//
//                    String hql = "from Dormitory";
//                    Query query = session.createQuery(hql);
//                    List<Dormitory> dormitoryList = query.list();
//                    String response = "All dormitories:\n";
//                    for(Dormitory dormitory : dormitoryList){
//                        response += dormitory.getName() + " - standard: " + dormitory.getStandard() + "\n";
//                    }
//
//                    dout.writeUTF(response);
//                    dout.flush();
//                }
//                // Find student
//                else if (words[0].equals("7"))
//                {
//                    DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
//
//                    // TODO jak to rozpisac
//                    //7;"Kuba";"Halucha"
//                    String hql = "Select s.firstName from Student as s where s.firstName= :firstName AND s.lastName=:lastName";
//
//                    query.setParameter("name", words[1]);
//
//                    List<Student> studentList = query.list();
//                    String response = "";
//
//                    if(studentList.isEmpty())
//                        response = "There is no such a student";
//                    else {
//                        System.out.println("Student found:");
//                        for (Student student : studentList) {
//                            response += student.getId() + " - " + student.getFirstName() + " " + student.getLastName() + "\n";
//                        }
//                    }
//
//                    dout.writeUTF(response);
//                    dout.flush();
//                }
//                // TODO assign student to dormitory and room?
//                else if(words[0].equals("8"))
//                {
//
//                }
//                // Delete student
//                else if(words[0].equals("9"))
//                {
//
//                }
//                // Exit program
//                else if(words[0].equals("0"))
//                {
//
//                }
//                else
//                {
//                    System.out.println("Unknown option, please choose another");
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

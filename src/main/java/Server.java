import model.Room;
import model.Student;
import model.Dormitory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

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
            while (true) {
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

                    out.println("Created new student");
                } else if (words[0].equals("0")){ //musi byc do zamknicia
                    System.out.println("Sesja zamknieta, student zapisany");
                    socket.close();
                }
                // Create new room
                else if (words[0].equals("2"))
                {
                    session.beginTransaction();

                    Room room = new Room();
                    room.setDoorNumber(Long.valueOf(words[1]));

                    session.save(room);

                    session.getTransaction().commit();
                    session.close();

                    out.println("Created new room");
                }
                // Create new dormitory
                else if(words[0].equals("3"))
                {
                    session.beginTransaction();

                    Dormitory dormitory = new Dormitory();
                    dormitory.setName(words[1]);
                    dormitory.setStandard(words[2]);

                    session.save(dormitory);

                    session.getTransaction().commit();
                    session.close();

                    out.println("Created new dormitory");
                }
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
                else if (words[0].equals("7"))
                {
                    DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
                    StringBuilder response = new StringBuilder();
//                    Transaction transaction = session.beginTransaction();
                    session.createQuery("Select s from Student as s where s.firstName= :firstName AND s.lastName=:lastName", Student.class)
                            .setParameter("firstName", words[1])
                            .setParameter("lastName", words[2])
                            .getResultList()
                            .forEach(student->response.append(student));

//                    transaction.commit();
                    dout.writeUTF(response.toString());
                    dout.flush();
                    session.close();
                    out.println("Zwrocilem wyszukanego studenta");
                }
               // assign student to room
                else if(words[0].equals("8"))
                {
                    session.beginTransaction();
                    Optional<Room> optionalRoom = session.createQuery("Select r from Room as r where r.doorNumber=:doorNumber", Room.class)
                            .setParameter("doorNumber", words[3])
                            .getResultList()
                            .stream()
                            .findFirst();
                    Optional<Student> optionalStudent = session.createQuery("Select s from Student as s where s.firstName= :firstName AND s.lastName=:lastName", Student.class)
                            .setParameter("firstName", words[1])
                            .setParameter("lastName", words[2])
                            .getResultList()
                            .stream()
                            .findFirst();
                    if(optionalStudent.isPresent()){
                        Student student = optionalStudent.get();
                        if(optionalRoom.isPresent()){
                            Room room = optionalRoom.get();
                            student.setRoom(room);
                            session.save(student);
                            session.save(room);
                        } else {
                            //jesli pokoj nie istnieje
                        }
                    }else {
                        //jesli nie istnieje
                    }

                    session.getTransaction().commit();
                    session.close();

                    System.out.println("student przypisany");
                    session.close();
                }
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
            e.printStackTrace();}
//        } finally {
//            try {
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}

import model.Dormitory;
import model.Room;
import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
         StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

//        boolean shouldProgramContinue = true;
//
//        while(shouldProgramContinue) {
//            try (Scanner userInput = new Scanner(System.in)) {
//                displayMenu();
//
//                int userChoice = userInput.nextInt();
//
//                switch (userChoice) {
//                    case 1:
//                        createNewStudent();
//                        break;
//                    case 2:
//                        createNewRoom();
//                        break;
//                    case 3:
//                        createNewDormitory();
//                        break;
//                    case 4:
//                        displayStudents();
//                        break;
//                    case 5:
//                        displayRooms();
//                        break;
//                    case 6:
//                        displayDormitories();
//                        break;
//                    case 7:
//                        findStudent();
//                        break;
//                    case 8:
//                        System.out.println("Thank you for using our program");
//                        shouldProgramContinue = false;
//                        break;
//                    default:
//                        System.out.println("Unknown operation, please choose one of the available options");
//                }
//            }
//        }

        Room room1 = new Room();
        room1.setDoorNumber(107l);

        Room room2 = new Room();
        room2.setDoorNumber(307l);

        Room room3 = new Room();
        room3.setDoorNumber(130l);
        List<Room> roomsDormitory1 = new ArrayList<>();
        roomsDormitory1.add(room1);
        roomsDormitory1.add(room2);
        roomsDormitory1.add(room3);

        Dormitory dormitory1 = new Dormitory();
        dormitory1.setId(1L);
        dormitory1.setName("Zascianek");
        dormitory1.setStandard("Basic");
        dormitory1.setRooms(roomsDormitory1);

//        Dormitory dormitory2 = new Dormitory();
//        dormitory2.setId(2L);
//        dormitory2.setName("Olimp");
//        dormitory2.setStandard("Comfort");


        Student student1 = new Student();
        student1.setId(1L);
        student1.setFirstName("Szymon");
        student1.setLastName("Talar");
        student1.setDormitory(dormitory1);
        student1.setRoom(room1);

        Student student2 = new Student();
        student2.setId(2L);
        student2.setFirstName("Jaroslaw");
        student2.setLastName("K");
        student2.setDormitory(dormitory1);
        student2.setRoom(room2);

        Student student3 = new Student();
        student3.setId(3L);
        student3.setFirstName("Aleksander");
        student3.setLastName("Turocha");
        student3.setDormitory(dormitory1);
        student3.setRoom(room3);

//        Student student4 = new Student();
//        student4.setId(4L);
//        student4.setFirstName("Jakub");
//        student4.setLastName("Halucha");
//        student4.setDormitory(dormitory2);

        session.save(room1);
        session.save(room2);
        session.save(room3);
        session.save(dormitory1);
//        session.save(dormitory2);
        session.save(student1);
        session.save(student2);
        session.save(student3);
//        session.save(student4);

//        Query select_student_from_student = session.createQuery("select Student from Student");
//        List list = select_student_from_student.list();
//        list.forEach(System.out::println);


        transaction.commit();
        Transaction transaction1 = session.beginTransaction();
        List<String> select_student_from_student = session.createQuery("Select student.firstName from Student as student", String.class).getResultList();
        select_student_from_student.forEach(System.out::println);

        System.out.println("All students ***************");
        List<Student> resultList = session.createQuery("select student from Student as student", Student.class).getResultList();
        resultList.forEach(System.out::println);
        System.out.println("Szukam talara ***************");
        session.createQuery("Select s from Student as s where s.firstName= :firstName AND s.lastName=:lastName", Student.class)
                .setParameter("firstName", "Szymon")
                .setParameter("lastName", "Talar")
                .getResultList()
                .stream()
                .forEach(System.out::println);

        transaction1.commit();
        session.close();
        factory.close();

        System.out.println("Session closed");
    }

    private static void addStudentToDormitory() {

    }

    private static void findStudent() {

    }

    private static void displayDormitories() {

    }

    private static void displayRooms() {

    }

    private static void displayStudents() {
    }

    private static void createNewDormitory() {
    }

    private static void createNewRoom() {
    }

    private static void createNewStudent() {
    }

    private static void displayMenu() {
        System.out.println("Welcome to Student Dormitory Reservation System");
        System.out.println("Menu:");
        System.out.println("1 - Create new Student");
        System.out.println("2 - Create new Room");
        System.out.println("3 - Create new Dormitory");
        System.out.println("4 - Display students");
        System.out.println("5 - Display rooms");
        System.out.println("6 - Display dormitories");
        System.out.println("7 - Find student");
        System.out.println("8 - Exit program");
    }
}

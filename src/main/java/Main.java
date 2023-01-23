import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private static SessionFactory sessionFactory;
    public static void main(String[] args) {

        LogManager.getLogManager().reset();
        Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
        globalLogger.setLevel(java.util.logging.Level.OFF);
        Scanner sc = new Scanner(System.in);

        try {
            int opc =1;
            while (opc != 0) {
                System.out.println("-----Menu-----");
                System.out.println("Opc 1 borrar Usuario");
                System.out.println("Opc 2 guardar Usuario");
                System.out.println("Opc 3 actualizar Usuario");
                System.out.println("Opc 4 leer Usuario");
                System.out.println("Opc 5 borrar Post");
                System.out.println("Opc 6 guardar Post");
                System.out.println("Opc 7 actualizar Post");
                System.out.println("Opc 8 leer Post");
                System.out.println("Opc 9 borrar Likes");
                System.out.println("Opc 10 guardar Likes");
                System.out.println("Opc 11 actualizar Likes");
                System.out.println("Opc 12 leer Likes");
                opc = sc.nextInt();
                switch (opc) {
                    case 1:
                        setUp();
                        borrarUsuarios(1);
                        break;
                    case 2:
                        setUp();
                        guardarUsuarios("david", "peleas", "DavidP1996erea", "githubP", "david.perea@iesnervion.es" );
                        break;
                    case 3:
                        setUp();
                        actualizarUsuarios("david", "perea", "DavidP1996erea", "githubP", "david.perea@iesnervion.es");
                        break;
                    case 4:
                        setUp();
                        leerUsuarios(12);
                        break;
                    case 5:
                        setUp();
                        borrarPost(1);
                        break;
                    case 6:
                        setUp();
                        guardarPost(Calendar.getInstance().getTime() , Calendar.getInstance().getTime(), 1);
                        break;
                    case 7:
                        setUp();
                        actualizarPost(Calendar.getInstance().getTime() , Calendar.getInstance().getTime(), 1);
                        break;
                    case 8:
                        setUp();
                        leerPost(12);
                        break;
                    case 9:
                        setUp();
                        borrarLikes(1);
                        break;
                    case 10:
                        setUp();
                        guardarLikes(1,1);
                        break;
                    case 11:
                        setUp();
                        actualizarLikes(1,1);
                        break;
                    case 12:
                        setUp();
                        leerLikes(12);
                        break;
                }

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    protected static void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    private static void guardarUsuarios(String nombre, String apellidos, String username, String password, String email) {
        Usuarios persona = new Usuarios(nombre, apellidos, username, password, email);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(persona);
        transaction.commit();
        System.out.println(id);
        sessionFactory.close();
    }
    private static void borrarUsuarios(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Usuarios persona = session.get(Usuarios.class, id);
        session.delete(persona);
        transaction.commit();
        sessionFactory.close();
    }
    private static void actualizarUsuarios(String nombre, String apellidos, String username, String password, String email){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Usuarios persona = session.get(Usuarios.class, 1);
        persona.setNombre(nombre);
        persona.setApellidos(apellidos);
        persona.setUsername(username);
        persona.setUsername(password);
        persona.setEmail(email);
        session.saveOrUpdate(persona);
        transaction.commit();
        sessionFactory.close();
    }
    private static void leerUsuarios(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Usuarios persona = session.load(Usuarios.class, id);
        System.out.println(persona.getNombre()+ " "+ persona.getApellidos()+ " "+ persona.getUsername()+ " "+ persona.getPassword()+ " "+ persona.getEmail());
        transaction.commit();
        sessionFactory.close();
    }


    private static void guardarPost(Date create_at, Date updated_at, int idUsuario) {
        Post persona = new Post(create_at, updated_at);
        Session session = sessionFactory.openSession();
        persona.setIdUsuario(session.load(Usuarios.class, idUsuario));
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(persona);
        transaction.commit();
        System.out.println(id);
        sessionFactory.close();
    }
    private static void borrarPost(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Post persona = session.get(Post.class, id);
        session.delete(persona);
        transaction.commit();
        sessionFactory.close();
    }
    private static void actualizarPost(Date create_at, Date updated_at, int idUsuario){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Post persona = session.get(Post.class, 2);
        persona.setCreate_at(create_at);
        persona.setUpdated_at(updated_at);
        persona.setIdUsuario(session.load(Usuarios.class, idUsuario));
        session.saveOrUpdate(persona);
        transaction.commit();
        sessionFactory.close();
    }
    private static void leerPost(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Post persona = session.load(Post.class, id);
        System.out.println(persona.getCreate_at()+ " "+ persona.getUpdated_at()+ " "+ persona.getIdUsuario());
        transaction.commit();
        sessionFactory.close();
    }

    private static void guardarLikes(int idUsuario, int idPost) {
        Likes persona = new Likes();
        Session session = sessionFactory.openSession();
        persona.setIdUsuario(session.load(Usuarios.class, idUsuario ));
        persona.setIdPost(session.load(Post.class, idPost ));
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(persona);
        transaction.commit();
        System.out.println(id);
        sessionFactory.close();
    }
    private static void borrarLikes(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Likes persona = session.get(Likes.class, id);
        session.delete(persona);
        transaction.commit();
        sessionFactory.close();
    }
    private static void actualizarLikes(int idUsuario, int idPost){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Likes persona = session.get(Likes.class, 2);
        persona.setIdUsuario(session.load(Usuarios.class, idUsuario ));
        persona.setIdPost(session.load(Post.class, idPost ));
        session.saveOrUpdate(persona);
        transaction.commit();
        sessionFactory.close();
    }
    private static void leerLikes(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Likes persona = session.load(Likes.class, id);
        System.out.println(persona.getIdPost()+ " "+ persona.getIdUsuario());
        transaction.commit();
        sessionFactory.close();
    }

}


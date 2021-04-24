package user;


import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        var faker = new FakeUser(new Locale("hu"));
        try (Handle handle = jdbi.open()){
            UserDao dao = handle.attach(UserDao.class);
            dao.createTable();
            User user1 = faker.createFake();
            User user2 = faker.createFake();
            User user3 = faker.createFake();
            User user4 = faker.createFake();
            dao.insertUser(user1);
            dao.insertUser(user2);
            dao.insertUser(user3);
            dao.insertUser(user4);
            dao.getAllUsers().forEach(System.out::println);
            System.out.println();
            dao.deleteUser(user1);
            dao.getAllUsers().forEach(System.out::println);
            System.out.println();
            System.out.println(dao.getUserFromId(3).get());
            System.out.println();
            System.out.println(dao.getUserFromUsername(user3.getUsername()).get());
        }

        }
    }

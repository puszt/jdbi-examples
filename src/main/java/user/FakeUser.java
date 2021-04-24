package user;

import com.github.javafaker.Faker;
import static org.apache.commons.codec.digest.DigestUtils.md5Hex;
import java.time.ZoneId;
import java.util.Locale;



public class FakeUser {

    private final Faker faker;

    public FakeUser(Locale locale){
        this.faker = new Faker(locale);
    }

    public User createFake(){
        User user = User.builder()
                .username(faker.name().username())
                .password(md5Hex(faker.internet().password()))
                .name(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .gender(faker.options().option(User.Gender.MALE, User.Gender.FEMALE))
                //A toLocalDate nem működött magában, ezért kell az 'Instant' objektumot elkészíteni
                .birthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .enabled(faker.bool().bool())
                .build();

        return user;
    }
}
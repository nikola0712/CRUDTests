package utility;

import com.github.javafaker.Faker;

public class UserGenerate {

    public Faker FAKER = new Faker();
    public String generateNewName() {

        return FAKER.name().name();
    }

    public String generateNewEmail() {

        return FAKER.bothify("????##@gmail.com");
    }
}

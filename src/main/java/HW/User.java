package HW;



/*
 * {
 *   "name":"John",
 *   "surname":"Doe",
 *   "gender":"male",
 *   "region":"United States"
 * }
 */


public class User {

    private String name;
    private String surname;
    private String gender;
    private String region;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}

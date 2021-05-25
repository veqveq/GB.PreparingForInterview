package ru.veqveq.lesson1;

public class Exercise1 {
    public static void main(String[] args) {
        Person testPerson = new Person.Builder()
                .addFirstName("G")
                .addMiddleName("G")
                .addLastName("Allin")
                .addAge(36)
                .addCountry("USA")
                .addGender("God")
                .build();
        System.out.println(testPerson);
    }
}

class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String phone;
    private int age;
    private String gender;

    public static class Builder {

        private final Person person;

        public Builder() {
            this.person = new Person();
        }

        public Builder addFirstName(String firstName) {
            this.person.firstName = firstName;
            return this;
        }

        public Builder addLastName(String lastName) {
            this.person.lastName = lastName;
            return this;
        }

        public Builder addMiddleName(String middleName) {
            this.person.middleName = middleName;
            return this;
        }

        public Builder addCountry(String country) {
            this.person.country = country;
            return this;
        }

        public Builder addPhone(String phone) {
            this.person.phone = phone;
            return this;
        }

        public Builder addAge(int age) {
            this.person.age = age;
            return this;
        }

        public Builder addGender(String gender) {
            this.person.gender = gender;
            return this;
        }

        public Person build() {
            return person;
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class Person {
    private final String surname;
    private final String firstName;
    private final String middleName;
    private final String birthDate;
    private final long phoneNumber;
    private final char gender;

    public Person(String surname, String firstName, String middleName, String birthDate, long phoneNumber, char gender) {
        this.surname = surname;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getFullName() {
        return surname + " " + firstName + " " + middleName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public char getGender() {
        return gender;
    }
}

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите данные в формате: Фамилия Имя Отчество дата_рождения номер_телефона пол");

            String input = scanner.nextLine();
            String[] parts = input.split("\\s+");

            if (parts.length != 6) {
                System.out.println("Ошибка: Введено неверное количество данных.");
                return;
            }

            String surname = parts[0];
            String firstName = parts[1];
            String middleName = parts[2];
            String birthDate = parts[3];
            long phoneNumber;
            try {
                phoneNumber = Long.parseLong(parts[4]);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Неверный формат номера телефона.");
                return;
            }
            char gender;
            if (parts[5].length() != 1 || (parts[5].charAt(0) != 'f' && parts[5].charAt(0) != 'm')) {
                System.out.println("Ошибка: Неверный формат пола.");
                return;
            } else {
                gender = parts[5].charAt(0);
            }

            Person person = new Person(surname, firstName, middleName, birthDate, phoneNumber, gender);

            String filename = surname + ".txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
                writer.write(person.getFullName() + " " + person.getBirthDate() + " " + person.getPhoneNumber() + " " + person.getGender() + "\n");
                System.out.println("Данные успешно записаны в файл " + filename);
            } catch (IOException e) {
                System.out.println("Ошибка при записи в файл: " + e.getMessage());
                logger.log(Level.SEVERE, "Ошибка при записи в файл", e);
            }
        }
    }
}

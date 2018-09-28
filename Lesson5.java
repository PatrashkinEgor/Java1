/**
 * Java. Level 1. Lesson 5. Homework
 *
 * @author Egor Patrashkin
 * @version dated Sep 28, 2018
 */
import java.util.Scanner;

public class Lesson5 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int numberOfEmployees;
        System.out.println("Enter the number of employees");
        numberOfEmployees = sc.nextInt();
        Person[] persArray = new Person[numberOfEmployees];
        for (int i = 0; i < numberOfEmployees ; i++) {
            System.out.print("Enter the information about employee №" + (i+1) +": ");
            persArray[i] = new Person();
        }
        System.out.print("Enter the age of the selection: ");
        byte borderAge = sc.nextByte();
        for (Person i: persArray) {
            if (i.getAge()>borderAge){
                System.out.println("Name: " + i.getName());
                System.out.println("Age: " + i.getAge());
                System.out.println("Function: " + i.getFunction());
                System.out.println("Salary: " + i.getSalary());
                System.out.println("Phone: " + i.getPhone());
                System.out.println("Email: " + i.getEmail());
                System.out.println();
            }
        }
    }




}

class Person{
    private String  name;
    private String  function;
    private String  email;
    private long    phone;
    private int     salary;
    private byte    age;

    Person(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("Enter the name: ");
            String name = sc.nextLine();
            if (isStringContainsANumber(name)) {
                    System.out.println( "Incorrect input format!");
            } else {
                this.name = name;
                break;
            }
        }
        while(true) {
            System.out.print("Enter the function: ");
            String function = sc.nextLine();
            if (isStringContainsANumber(function)||function.isEmpty()) {
                System.out.println( "Incorrect input format!");
            } else {
                this.function = function;
                break;
            }
        }
        while(true) {
            System.out.print("Enter the email: ");
            String email = sc.nextLine();
            if (!email.contains("@")||email.isEmpty()) {
                System.out.println( "Incorrect input format!");
            } else {
                this.email = email;
                break;
            }
        }
        while(true) {
            System.out.print("Enter the phone number:  ");
            long phone = sc.nextLong();
            if ((phone<1000)||(phone>99999999999l)) { //Принимаем что телефонный номер должен содержать от 4 до 11 разрядов
                // Похорошему надо ловить эксепшены при выходе за формат, но я еще не умею.
                System.out.println( "Incorrect input format!");
            } else {
                this.phone = phone;
                break;
            }
        }
        while(true) {
            System.out.print("Enter the salary: ");
            int salary = sc.nextInt();
            if (salary<100) { //Принимаем что зарплата не может быть меньше 100 условных едениц
                System.out.println( "Incorrect input format!");
            } else {
                this.salary = salary;
                break;
            }
        }
        while(true) {
            System.out.print("Enter the age: ");
            byte age = sc.nextByte();
            if ((age<14)||(age>100)) { //Принимаем что возраст не может быть больше 100 лет и меньше 14.
                System.out.println( "Incorrect input format!");
            } else {
                this.age = age;
                break;
            }
        }
    }

    public byte getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getFunction() {
        return function;
    }

    public String getEmail() {
        return email;
    }

    public int getSalary() {
        return salary;
    }

    public long getPhone() {
        return phone;
    }

    private boolean  isStringContainsANumber(String st){
        if (st.contains("0")||st.contains("1")||st.contains("2")||st.contains("3")||
                  st.contains("4")||st.contains("5")||st.contains("6")||st.contains("7")||
                  st.contains("8")||st.contains("9")){
            return true;
        } else {
            return false;
        }

      }

}

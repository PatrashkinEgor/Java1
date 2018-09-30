/**
 * Java. Level 1. Lesson 5. Homework
 *
 * @author Egor Patrashkin
 * @version dated Sep 28, 2018
 */




/**Исходное задание:
 * Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст;
 * Конструктор класса должен заполнять эти поля при создании объекта;
 * Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль;
 * Создать массив из 5 сотрудников
 * С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
 *
 * Модернизация:
 * Создается массив из N сотрудников, N запрашивается у пользователя;
 * Данные о сотрудниках заполняются из консоли;
 * Добавлена проверка на правильность заполнения полей;
 * Возраст отбора сотрудников задается пользователем.
 * */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Lesson5 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int numberOfEmployees;
        System.out.println("Enter the number of employees");
        numberOfEmployees = sc.nextInt();
        Person[] persArray = new Person[numberOfEmployees];

        for (int i = 0; i < numberOfEmployees ; i++) {  //Создаем массив из объектов класса Person
            System.out.print("Enter the information about employee №" + (i+1) +": ");
            persArray[i] = new Person();
        }


        byte borderAge;
        while(true){    //Получаем возраст отбора
            try{
                System.out.print("Enter the age of the selection: ");
                borderAge = sc.nextByte();
                break;
            } catch (InputMismatchException ex){
                System.out.println( "Incorrect input format!");
                sc.next();
            }
        }

        for (Person i: persArray) { //Выводим массив
            if (i.getAge()>borderAge){
                i.printInfo();
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

    /**
     * Метод, который выводит информацию об объекте в консоль
     * */
    public void printInfo(){
        System.out.println(); //Разделитель для удобного чтения списка
        System.out.println("Name: " + this.getName());
        System.out.println("Age: " + this.getAge());
        System.out.println("Function: " + this.getFunction());
        System.out.println("Salary: " + this.getSalary());
        System.out.println("Phone: " + this.getPhone());
        System.out.println("Email: " + this.getEmail());
        System.out.println(); //Разделитель для удобного чтения списка
    }

    /**
     * Конструктор класса с вводом с консоли
     * */
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

            try {
                long phone = sc.nextLong();
                if ((phone < 1000) || (phone > 99999999999l)) { //Принимаем что телефонный номер должен содержать от 4 до 11 разрядов
                    System.out.println("Incorrect input format!");
                } else {
                    this.phone = phone;
                    break;
                }
            } catch (InputMismatchException ex){
                System.out.println( "Incorrect input format!");
                sc.next();
            }
        }
        while(true) {
            System.out.print("Enter the salary: ");
            try {
                int salary = sc.nextInt();
                if (salary < 100) { //Принимаем что зарплата не может быть меньше 100 условных едениц
                    System.out.println("Incorrect input format!");
                } else {
                    this.salary = salary;
                    break;
                }
            } catch (InputMismatchException ex){
                System.out.println( "Incorrect input format!");
                sc.next();
            }
        }
        while(true) {
            System.out.print("Enter the age: ");
            try {
                byte age = sc.nextByte();
                if ((age < 14) || (age > 100)) { //Принимаем что возраст не может быть больше 100 лет и меньше 14.
                    System.out.println("Incorrect input format!");
                } else {
                    this.age = age;
                    break;
                }
            } catch (InputMismatchException ex){
                System.out.println( "Incorrect input format!");
                sc.next();
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

    /**
     * Метод проверяющий наличие цифры в строке(сделано "в лоб")
     */
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

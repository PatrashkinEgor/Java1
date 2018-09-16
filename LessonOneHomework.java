/**
 * Created by Patrashkin Egor for geekbrains Java level 1 lesson 1 on 13.09.2018.
 */

public class LessonOneHomework {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        //2. Создать переменные всех пройденных типов данных, и инициализировать их значения;
        byte a = 10;
        short b = 2404;
        int c = 200000;
        long d = 15000L;
        float e = 120.0f;
        double f = 15.72775;
        boolean g = true;
        char h = 'A';


    }

//3. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,где a, b, c, d – входные параметры этого метода;


    public static int calculation(int a, int b,int c, int d) {
        return a*(b+(c/d)); // возвращаем результат
    }

//4. Написать метод, принимающий на вход два числа, и проверяющий что их сумма лежит в пределах от 10 до 20(включительно), если да – вернуть true, в противном случае – false;

    public static boolean comparison(int a, int b) {
        int c = a + b;
        if((a>=10)&(a<=20)) {
            return true;
        } else {
            return false;
        }
    }

    //5. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль положительное ли число передали, или отрицательное; Замечание: ноль считаем положительным числом.

    public static void positiveOrNegative(int a) {

        System.out.print("This number is");
        if(a<0) {
            System.out.println(" negative");
        } else {
            System.out.println(" positive");
        }
    }


    //6. Написать метод, которому в качестве параметра передается целое число, метод должен вернуть true, если число отрицательное;

    public static boolean isThisNumberNegative(int a) {

        if(a<0) {
            return true;
        }
        else {
            return false;
        }
    }

    //7. Написать метод, которому в качестве параметра передается строка, обозначающая имя, метод должен вывести в консоль сообщение «Привет, указанное_имя!»;

    public static void helloName(String name) {

        System.out.println("Привет, " + name);
    }

    //8. * Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль. Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
    public static void isThisYearIsALeapYear(int year) {

        System.out.print("This year is ");
        if(((year%4 == 0)&&(year%100 != 0))||(year%400 == 0))
        {
            System.out.println("a leap year");
        }
        else {
            System.out.println("not a leap year");
        }
    }

}






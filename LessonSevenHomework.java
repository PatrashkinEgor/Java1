import java.io.IOException;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;




/**
 * Java. Level 1. Lesson 7. Homework
 *
 * @author Egor Patrashkin
 * @version dated Oct 04, 2018
 */

/**
 * Задание:
 * 1. Расширить задачу про котов и тарелки с едой
 * 2. Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды (например, в миске 10 еды, а кот пытается покушать 15-20)
 * 3. Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны). Если коту удалось покушать (хватило еды), сытость = true
 * 4. Считаем, что если коту мало еды в тарелке, то он ее просто не трогает, то есть не может быть наполовину сыт (это сделано для упрощения логики программы)
 * 5. Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки и потом вывести информацию о сытости котов в консоль
 * 6. Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку
 * 7. ***** Решить задачу с котами в Swing, самостоятельно изучив методичку №8
 */

public class LessonSevenHomework {

    public static Cat[] cats = new Cat[5];
    public static Plate plate = new Plate(50);

    public static void main(String[] args) {
        //Test.testString(120000);
        //Test.testStringBuilder(120000);
        //Test.testBufferedReader(
        //    "C:\\Program Files\\AVAST Software\\Avast\\AvastUI.exe");
        //Test.testFileReader(
        //    "C:\\Program Files\\AVAST Software\\Avast\\AvastUI.exe");
        //Singleton s1 = new Singleton();
        //Singleton s2 = new Singleton();
        //Singleton s1 = Singleton.getInstance();
        //Singleton s2 = Singleton.getInstance();
        //System.out.println(s1 + " : " + s2);
        final int maxHungryValue = 10;
        Random random = new Random();   // создание объекта класса Random

        MyWindow myWindow = new MyWindow();


        cats[0].setName("Alpha");
        cats[1].setName("Bravo");
        cats[2].setName("Charlie");
        cats[3].setName("Delta");
        cats[4].setName("Echo");
        plate.setFood(50);
        System.out.println(plate);

        for (Cat cat: cats) {
            cat.eat(plate);
        }
        System.out.println(plate);

    }

    public static void feedTheCats(Cat [] cats, Plate plate){
        for (Cat cat: cats) {
            cat.eat(plate);
        }
    }
}

class Cat {
    private String name;
    private int appetite;
    private boolean satiety;
    private final int maxHungryValue = 10;

    /**
     * Конструктор класса Cat, параметр appetite задается произвольно в диапазоне (0;10).
     * Параметр сытость по умолчанию False.
     * @param name Имя Кота
     */

    Cat(String name) {
        Random random = new Random();   // создание объекта класса Random
        this.name = name;
        this.appetite = (int)(maxHungryValue * random.nextFloat());
        this.satiety = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name  + " usually eats "+ this.appetite+". Now he is " + (this.satiety? "satisfied":"Hungry");
    }


    void eat(Plate plate) {
        this.satiety = plate.decreaseFood(appetite);
    }
}

class Plate {
    private int food;

    Plate(int food) {
        this.food = food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    /** Метод уменьшения еды(используется чтобы кормить котов)
     * Задача: Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды
     * (например, в миске 10 еды, а кот пытается покушать 15-20)
     * @param food уоличество еды требуемой для кормежки
     * @return если еды ПОЛНОСТЬЮ хватает возвращает true, если нет то false.
     */

    boolean decreaseFood(int food) {
        if (food>this.food){
          return false;
        } else{
            this.food -= food;
            return true;
        }
    }

    /**
     * Задание: Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку.
     * При передаче отрицательного значения добавки ничего не делаем.
     * @param addition размер добавки.
     */
    void addFood(int addition){
        this.food += addition>0? addition: 0;
    }


    @Override
    public String toString() {
        return "Food: " + food;
    }
}


class Test {

    /**
     * Testing class StringBuilder (mutable)
     */
    static void testStringBuilder(int cycles) {
        System.out.print("Testing StringBuilder... ");
        long t1 = System.currentTimeMillis();

        StringBuilder a = new StringBuilder("");
        for (int i = 0; i < cycles; i++)
            a.append("w");

        long t2 = System.currentTimeMillis();
        System.out.println("It took " + (t2 - t1) + " mc");
    }
}




class MyWindow extends JFrame {
    public MyWindow() {
        setTitle("Test Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
        JButton feedTheCatsButton = new JButton();
        JButton addFoodButton = new JButton();


        setLayout(null); // выбор компоновщика элементов
        add(feedTheCatsButton);
        add(addFoodButton);
        feedTheCatsButton.setBounds(300, 50, 40, 20);
        addFoodButton.setBounds(40, 50, 40, 20);

        feedTheCatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LessonSevenHomework.feedTheCats(LessonSevenHomework.cats, LessonSevenHomework.plate);
                System.out.println("1");
            }
        });
        addFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LessonSevenHomework.plate.addFood(50);
                System.out.println("2");
            }
        });
        setVisible(true);
    }
}


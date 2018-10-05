import java.io.IOException;

//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


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


    public static void main(String[] args) {
        Cat[] cats = new Cat[5];
        Plate plate = new Plate(0);
        //MyWindow myWindow = new MyWindow();
        cats[0] = new Cat("Alpha");
        cats[1] = new Cat("Bravo");
        cats[2] = new Cat("Charlie");
        cats[3] = new Cat("Delta");
        cats[4] = new Cat("Echo");
        System.out.println("Feed the cats.");

        System.out.println(plate);

        while (true){
            boolean satiety = true;
            for (Cat cat: cats) {
                cat.eat(plate);
                satiety = satiety & cat.getSatiety();
                System.out.println(cat);
            }
            System.out.println(plate);
            if (satiety){
                break;
            } else {
                System.out.println("Not enough food on the plate.");
                int addiction;
                Scanner sc = new Scanner(System.in);
                while(true){    //Получаем размер добавки
                    try{
                        System.out.print("Enter the addiction:");
                        addiction = sc.nextInt();
                        break;
                    } catch (InputMismatchException ex){
                        System.out.println( "Incorrect input format!");
                        sc.next();
                    }
                }
                plate.addFood(addiction);
            }
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


    @Override
    public String toString() {
        return this.name  + " usually eats "+ this.appetite+". Now he is " + (this.satiety? "satisfied":"Hungry");
    }
    boolean getSatiety(){
        return this.satiety;
    }


    void eat(Plate plate) {
        if (!this.getSatiety()){            //если кот сыт, то он не ест.
            this.satiety = plate.decreaseFood(appetite);
        }

    }
}

class Plate {
    private int food;

    Plate(int food) {
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


/**
 * Эксперименты со Swing
 */

/*


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
                LessonSevenHomework.needToFeed = 1;
                System.out.println("1");
            }
        });
        addFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LessonSevenHomework.needToAddFood = 1;
                System.out.println("2");
            }
        });
        setVisible(true);
    }
}

*/
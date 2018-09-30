/**
 * Java. Level 1. Lesson 6. Homework
 *
 * @author Egor Patrashkin
 * @version dated Sep 30, 2018
 */

/** Исходное задание
 * 1 Создать классы Собака и Кот с наследованием от класса Животное.
 * 2 Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве
 * параметра каждому методу передается величина, означающая или длину препятствия (для
 * бега и плавания), или высоту (для прыжков).
 * 3 У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; прыжок: кот
 * 2 м., собака 0.5 м.; плавание: кот не умеет плавать, собака 10 м.).
 * 4 При попытке животного выполнить одно из этих действий, оно должно сообщить результат в
 * консоль. (Например, dog1.run(150); -> результат: run: true)
 * 5 * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег
 * может быть 400 м., у другой 600 м.
 */



import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class LessonSixHomework {
    public static void main(String[] args) {
        Animal[] animals = {new Cat(50, 5, 2.0f),
                new Dog( 1000, 50, 0.5f)};

        for (Animal animal : animals)
            System.out.println(animal.toString());         //Выводим справку о параметрах животных
        Scanner sc = new Scanner(System.in);
        int type;

        //Проверка возможностей
        // Выбираем действие
        while(true) {
            try {
                System.out.println("Select action: 0 – Run / 1 – Swim / 2 - Jump");
                type = sc.nextInt();                       // считываем выбор пользователя
                if ((type>=0)&&(type<3)){
                    break;
                } else {
                    System.out.println("Incorrect input format!");
                }
            }   catch (InputMismatchException ex) {         // Ловим исключение
                System.out.println("Incorrect input format!");
                sc.next();
            }
        }

         /* Для соответствующих типов действия вводим параметр "расстояния" и выводим результат
         * функции animal.carryOutAnAction для каждого животного*/
         
        if ((type == 1) ||(type == 0)) {
            // (Плавание и бег - дистанция целочисленная)
            int distance = 0;
            while(true) {
                try {
                    System.out.println("Enter the distance:");
                    distance = sc.nextInt();
                    break;
                } catch (InputMismatchException ex) {
                    System.out.println("Incorrect input format!");
                    sc.next();
                }
            }
            for (Animal animal : animals)
                System.out.println(animal.getClass().getName() + " answer: " + animal.carryOutAnAction(type, distance));
        }else if (type == 2){
            // (Прыжок - высота с плавающей точкой)
            float height = 0;
            while(true) {
                try {
                    System.out.println("Enter the height:");
                    height = sc.nextFloat();
                    break;
                } catch (InputMismatchException ex) {
                    System.out.println("Incorrect input format!");
                    sc.next();
                }
            }
            for (Animal animal : animals)
                System.out.println(animal.getClass().getName() + " answer: " + animal.carryOutAnAction(type, height));
        }
    }
}

class Cat extends Animal {
    Cat(int maxRunDistance, int maxSwimDistance, float maxJumpHeight) {
        super(maxRunDistance,  maxSwimDistance,  maxJumpHeight);
    }

    /**
     *  Переопределение метода carryOutAnAction(int type, int distance).
     * @param type  Тип действия. 0 – Run / 1 – Swim / 2 - Jump
     * @param distance Расстояние. Для бега и плавания int.
     * @return false если расстояние бега больше соответствующего ограничения, true если это не так.
     * В случае плавания(1) возвращает false(По легенде котики не плавают)
     */
    public boolean carryOutAnAction(int type, int distance){//Использовал полиморфизм, Еще был вариант в конструкторе
        // класса всегда прописывать 0 в maxSwimDistance, но показалось что это костыльно.
        if (type == 0){
            return distance>this.maxRunDistance? false: true;
        } else {return false;}                              //По легенде котики не плавают!
    }

}

class Dog extends Animal {
    Dog(int maxRunDistance, int maxSwimDistance, float maxJumpHeight) {
        super(maxRunDistance, maxSwimDistance, maxJumpHeight);
    }

}



abstract class Animal {
    protected int maxRunDistance;
    protected int maxSwimDistance;
    protected float maxJumpHeight;

    /**
     * Конструктор класса Animal позволяет проинициализировать поля класса с произвольным отклонением
     * @param maxRunDistance Максимально возможное рассотяние бега.
     * @param maxSwimDistance Максимально возможное расстояние плавания.
     * @param maxJumpHigh Максимально возможная высота прыжка
     */
    Animal(int maxRunDistance, int maxSwimDistance, float maxJumpHigh) {
        Random random = new Random();   // создание объекта класса Random
        this.maxRunDistance = (int)(maxRunDistance * random.nextFloat());
        this.maxSwimDistance = (int)(maxSwimDistance * random.nextFloat());
        this.maxJumpHeight = maxJumpHigh * random.nextFloat();
    }

    /**
     * Переопределение метода toString() кдасса Object
     * @return В начале строки передается имя класса..
     */
    @Override
    public String toString() {
        return getClass().getName() + " max parameters: { Run - "  + maxRunDistance + ", Swim - " + maxSwimDistance +
                ", Jump - " + maxJumpHeight + "}";
    }

    /**
     * Метод carryOutAnAction позволяет оценить возможность выполнения действия животным
     * @param type  Тип действия. 0 – Run / 1 – Swim / 2 - Jump
     * @param distance Расстояние. Для бега и плавания int.
     * @return false если расстояние больше соответствующего ограничения, true если это не так.
     */

    public boolean carryOutAnAction(int type, int distance){
        if (type == 0){
            return distance>this.maxRunDistance? false: true;
        } else {return distance>this.maxSwimDistance? false: true;}
    }

    /**
     * Перегрузка метода carryOutAnAction для случая с прыжком второй параметр передается float-ом.
     * @param type Тип действия. 0 – Run / 1 – Swim / 2 - Jump
     * @param height Высота. В данном случае float.
     * @return false если высота больше соответствующего ограничения, true если это не так.
     */
    public boolean carryOutAnAction(int type, float height){
        return height>this.maxJumpHeight? false:true;
    }

}
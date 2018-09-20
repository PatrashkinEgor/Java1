/**
 * Java. Level 1. Lesson 3. Homework
 *
 * @author Egor Patrashkin
 * @version dated Sep 20, 2018
 */


import java.util.Scanner;
import java.util.Random;

public class LessonThreeHomework {
    public static void main(String[] args) {
        System.out.println("Привет! Сыграем?");


        chooseAGame();//Выполнил оба задания(надеюсь это не возбраняется). В основном методе вызывается метод выбора игры.
    }

    /*
     * Метод выбора игры.
     *
     */
    public static void chooseAGame(){
        System.out.println("Выбери игру: 1 – Угадай число / 0 – Угадай плод");
        Scanner sc = new Scanner(System.in);        // создание объекта класса Scanner
        int a = sc.nextInt();                       // считываем выбор пользователя
        if (a==1){
            System.out.println("Игра - угадай число.");
            guessTheNumber(3, 9);//
        }  else {
            System.out.println("Игра - угадай полод.");
            guessTheFruit(3);
        }
    }

    /*
    * 1.Написать программу, которая загадывает случайное число от 0 до 9, и пользователю дается 3
    * попытки угадать это число. При каждой попытке компьютер должен сообщить больше ли
    * указанное пользователем число, чем загаданное, или меньше. После победы или проигрыша
    * выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
    * Немного модернизировал задание, метод получает на вход количество попыток и максимальное значение диапазона.
    */

    public static void guessTheNumber(int attempts, int max) {
        int number = getRandomValue(max);       //Вызов метода возвращающего произвольное значение(Загадываем число)
        System.out.println("Угадай число от 0 до "+ max +". Количество попыток: " + attempts);
        Scanner sc = new Scanner(System.in);    // создание объекта класса Scanner
        for (int i = attempts; i > 0; i--) {
            System.out.print("Введи свой вариант и нажми Enter: ");
            int a = sc.nextInt();               // чтение варианта ответа пользователя
            if (a == number) {
                System.out.println("Молодец!");
                break;
            } else  if (i != 1){
                System.out.print("Неверно, попробуй еще раз! Загаданное число ");
                String str = a<number?"больше": "меньше";
                System.out.print(str);
                System.out.println(". Осталось попыток: " + (i - 1));
            } else {
                System.out.println("Видимо, не судьба...");
            }
        }
        System.out.println("Повторить игру? 2 – да / 1 – выбрать другую игру/ 0 – закончить"); // Модернизированное меню
        // позволяет переключиться на другую игру

        int a = sc.nextInt();             // считываем выбор пользователя
        while ((a>2)||(a<0)){             // если введенное целое число вне диапазона комманд, запрашиваем новую команду
            System.out.println("Неизвестная команда! Повторите ввод.");
            a = sc.nextInt();
        }
        switch (a) {
            case 2:
                guessTheNumber(attempts, max); // Рекурсивный вызов(новая попытка игры)
                break;
            case 1:
                chooseAGame();
                break;
            case 0:
                System.out.println("До свидания!");
                break;
        }
    }
    /*
     * Метод возвращающий произвольное значение.
     *
     */
    public static int getRandomValue(int a) {
        Random random = new Random();   // создание объекта класса Random
        int rnd = random.nextInt(a);
        return rnd;

    }

    /*
     * 2. Создать массив из слов String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado",
     * "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut",
     * "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
     * При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя, сравнивает его с загаданным
     * словом и сообщает правильно ли ответил пользователь. Если слово не угадано, компьютер показывает буквы которые
     * стоят на своих местах.
     *
     */

    public static void guessTheFruit (int attempts) {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
                "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
                "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        int number = getRandomValue(words.length); //Вызов метода возвращающего произвольное значение(Загадываем плод)
        System.out.println("Я загадал один из этих плодов:");
        /*
        * Печать списка фозможных плодов
         */
        for (int i = 0; i <words.length ; i++) {
            System.out.print(createFifteenSymbolString(words[i])); // Печатаем слово, приведя строку к 15 символам.
            if ((i+1)%5 == 0) {
                System.out.print("\n");         //после каждого 5-го слова переводим строку для улучшения читаемости
            }
        }
        System.out.print("\n");
        System.out.println("Угадай плод. Количество попыток: " + attempts);
        Scanner sc = new Scanner(System.in);    // создание объекта класса Scanner
        for (int i = attempts; i > 0; i--) {
            System.out.print("Введи свой вариант и нажми Enter: ");
            String guessed = sc.next();         // чтение ответа пользователя

            if (guessed.equals(words[number])){ // сравнение ответа и загаданного
                System.out.print("Молодец!");
                break;
            } else if (i != 1){                 // если еще остались попытки выводим подсказку
                StringBuilder hint = new StringBuilder("###############");
                for (int j = 0; j < Math.min(words[number].length(),guessed.length()); j++) {
                    if (guessed.charAt(j)==words[number].charAt(j)){
                        hint.setCharAt(j, guessed.charAt(j));
                    }
                }
                System.out.println("Неверно, попробуй еще раз!");
                System.out.println(hint);
                System.out.println("Осталось попыток: " + (i - 1));
            } else {                            // если попыток не осталось - заканчиваем игру
                System.out.println("Видимо, не судьба...");
            }
        }
        System.out.println("Повторить игру? 2 – да / 1 – выбрать другую игру/ 0 – закончить");
        int a = sc.nextInt();
        while ((a>2)||(a<0)){
            System.out.println("Неизвестная команда! Повторите ввод.");
            a = sc.nextInt();
        }

        switch (a) {
            case 2:
                guessTheFruit(attempts);
                break;
            case 1:
                chooseAGame();
                break;
            case 0:
                System.out.println("До свидания!");
                break;
        }
    }
    /*
     * Метод приводящий строку  меньше 15 символов к размеру 15 символов. Дополнительные знаки заполняются пробелами.
     * Данный метод необходим для улучшения читаемости списка возможных плодов, при выводе в консоль.
     */
    public static StringBuilder createFifteenSymbolString (String str){
        StringBuilder newStr = new StringBuilder("               ");
        for (int i = 0; i <str.length() ; i++) {
            newStr.setCharAt(i, str.charAt(i));
        }
        return newStr;
    }


}

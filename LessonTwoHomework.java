/**
 * Java. Level 1. Lesson 2. Homework
 *
 * @author Egor Patrashkin
 * @version dated Sep 16, 2018
 */

import java.util.Arrays;

public class LessonTwoHomework {
    public static void main(String[] args) {

        // tests for task №1
        int[] firstArr = {1,0,1,1,0,0,1,1,0,0,0,1};
        invertArray(firstArr);

        /*
        * 2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
        *
        */
        int[] secondArr = new int[8];
        for (int i = 1; i <secondArr.length; i++) {
            secondArr[i]= secondArr[i-1]+3;
        }
        System.out.println(Arrays.toString(secondArr));


        // tests for task №3
        int[] thirdArr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        multLessThenSix(thirdArr);

        // tests for task №4
        createSquareArray(1);
        System.out.print('\n');
        createSquareArray(2);
        System.out.print('\n');
        createSquareArray(5);
        System.out.print('\n');
        createSquareArray(10);
        System.out.print('\n');

        // tests for task №5

        int[] fifthArr = {1, 101, 3, 2, 11, 4, 5, 2, 4, 8, 9, -1};
        findMinMax(secondArr);
        findMinMax(thirdArr);
        findMinMax(fifthArr);

        // tests for task №6
        System.out.println(findTheEquilibriumPoint(firstArr));
        System.out.println(findTheEquilibriumPoint(secondArr));
        System.out.println(findTheEquilibriumPoint(thirdArr));
        System.out.println(findTheEquilibriumPoint(fifthArr));


        // tests for task №7
        rollTheArray(fifthArr,-20);
        rollTheArray(fifthArr,-5);
        rollTheArray(fifthArr,-1);
        rollTheArray(fifthArr,0);
        rollTheArray(fifthArr,2);
        rollTheArray(fifthArr,8);
        rollTheArray(fifthArr,16);


    }

    /*
    * 1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
    * С помощью цикла и условия заменить 0 на 1, 1 на 0;
    *
    */

    public static void invertArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                arr[i]--;
            } else {
                arr[i]++;
            }
        }

        System.out.println(Arrays.toString(arr));
    }

    /*
     * 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
     *
     */
    public static void multLessThenSix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] = arr[i] * 6;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
    /*
    * 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью
    * цикла(-ов) заполнить его диагональные элементы единицами;
     */
    public static void createSquareArray(int size) {

        int[][] fourthArr = new int[size][size];

        for (int i = 0; i <size ; i++) {
            for (int j = 0; j <size ; j++) {
                if ((i==j)||(i+j == size-1)){
                    fourthArr[i][j] =1;

                }
                System.out.print(fourthArr[i][j]);
            }
            System.out.print('\n');
        }
    }


    /*
     * 5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
     */
    public static void findMinMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        System.out.println("Минимальное значение в массиве: " + min + ";");
        System.out.println("Максимальное значение в массиве: " + max + ".");
    }


    /*
    **6. Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true
    * если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры:
    * checkBalance([1, 1, 1, || 2, 1]) → true, checkBalance ([2, 1, 1, 2, 1]) → false,
    * checkBalance ([10, || 10]) → true, граница показана символами ||, эти символы в массив не входят.
    */
    public static boolean findTheEquilibriumPoint(int[] arr) {
        int leftSum = 0;
        for (int i = 0; i < arr.length; i++) {
            leftSum = leftSum + arr[i];
            int rightSum = 0;
            for (int j = i + 1; j < arr.length; j++) {
                rightSum = rightSum + arr[j];
            }
            if (leftSum == rightSum) {
                return true;
            }
        }
        return false;
    }

    /*
    *7. **** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным,
    * или отрицательным), при этом метод должен сместить все элементымассива на n позиций. Для усложнения задачи нельзя
    * пользоваться вспомогательными массивами.
    */
    public static void rollTheArray(int[] arr, int roll) {
        if (roll == 0){
            System.out.println(Arrays.toString(arr));
            return;
        }

        roll = roll % arr.length ;

        if (roll < 0) {
            for (int i = 0; i > roll; i--) {
                int temp = arr[0];
                for (int j = 0; j < arr.length-1; j++) {
                    arr[j] = arr[j + 1];
                }
                arr[arr.length-1] = temp;
            }

        } else{
            for (int i = 0; i < roll; i++) {

                int temp = arr[arr.length-1];
                for (int j = (arr.length-1); j > 0 ; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[0] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

}

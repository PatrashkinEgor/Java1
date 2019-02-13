/**
 * Java. Level 1. Lesson 4. Homework
 *
 * @author Egor Patrashkin
 * @version dated Sep 28, 2018
 */


import java.util.Random;
import java.util.Scanner;

/**
 * Задание:
 * 1. Полностью разобраться с кодом;
 * 2. Переделать проверку победы, чтобы она не была реализована просто набором условий, например, с использованием циклов.
 * 3. * Попробовать переписать логику проверки победы, чтобы она работала для поля любого размера и любого количества фишек.;
 * 4. *** Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока.
 */
class LessonFourHomework {

    final int SIZE = 10;
    final int WINLINE = 3;              //Размер необходимой последовательности
    final char DOT_X = 'x';
    final char DOT_O = 'o';
	final char DOT_FUF = 'o';
    final char DOT_EMPTY = '.';
    char[][] map = new char[SIZE][SIZE];
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public static void main(String[] args) {
        new LessonFourHomework().game();
    }

    void game() {
        initMap();
        while (true) {

            if (humanTurn()) {
                System.out.println("YOU WON!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Sorry, DRAW...");
                break;
            }
            if (aiTurn()) {
                System.out.println("AI WON!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Sorry, DRAW...");
                break;
            }
        }
        System.out.println("GAME OVER!");
        printMap();
    }

    void initMap() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                map[i][j] = DOT_EMPTY;
    }

    void printMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    boolean humanTurn() {       //Теперь проверка на победу происходит во время хода игрока, по этому метод возвращает
        // результат хода(выиграл или нет)
        int x, y;
        do {
            System.out.println("Enter X and Y (1.." + SIZE +"):");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
        return checkWin(x,y, DOT_X);
    }

    boolean aiTurn() {
        int x, y;
        //Сначала выберем точку "по умолчанию" чтобы компилятор не ругался на возможно не проинициализированные x и y
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        //Пробегаем по полю в поисках точки которая позволит X победить, если есть, то блокируем ее.
        //Если не найдем такую точку, то О встанет в произвольную точку выбранную выше.
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i,j)){
                    if (checkWin(i,j, DOT_X)){
                        x = i;
                        y = j;
                        break;
                    }
                }
            }
        }
        map[y][x] = DOT_O;
        printMap();
        return checkWin(x,y, DOT_O);
    }

    boolean checkWin(int x, int y,char dot) {
        // Проверяем наоичие таких же точек по соседству в разных направлениях и считаем длину непрерывной линии.
        // Если больше уставки, то выиграл.
        if ((getNumberOfNeighbors(x,y, -1,0,dot) + 1 + getNumberOfNeighbors(x,y, 1,0,dot))>= WINLINE){
            return true;
        }
        if ((getNumberOfNeighbors(x,y, 0,-1,dot) + 1 + getNumberOfNeighbors(x,y, 0,1,dot))>= WINLINE){
            return true;
        }
        if ((getNumberOfNeighbors(x,y, 1,1,dot) + 1 + getNumberOfNeighbors(x,y, -1,-1,dot))>= WINLINE){
            return true;
        }
        if ((getNumberOfNeighbors(x,y, 1,-1,dot) + 1 + getNumberOfNeighbors(x,y, -1,1,dot))>= WINLINE){
            return true;
        }
        return false;
        /*// check horizontals
        if (map[0][0] == dot && map[0][1] == dot && map[0][2] == dot) return true;
        if (map[1][0] == dot && map[1][1] == dot && map[1][2] == dot) return true;
        if (map[2][0] == dot && map[2][1] == dot && map[2][2] == dot) return true;
        // check verticals
        if (map[0][0] == dot && map[1][0] == dot && map[2][0] == dot) return true;
        if (map[0][1] == dot && map[1][1] == dot && map[2][1] == dot) return true;
        if (map[0][2] == dot && map[1][2] == dot && map[2][2] == dot) return true;
        // check diagonals
        if (map[0][0] == dot && map[1][1] == dot && map[2][2] == dot) return true;
        if (map[2][0] == dot && map[1][1] == dot && map[0][2] == dot) return true;
        return false;
        */
    }

    /**
     * Метод возвращает длину линии из точек типа dot в заданном направлении из заданных координат
     * @param x начальная координата точки по x
     * @param y начальная координата точки по y
     * @param vx направление поиска по x
     * @param vy направление поиска по y
     * @param dot Тип точки(X или O)
     * @return
     */
    int getNumberOfNeighbors(int x, int y, int vx, int vy, char dot) {
        int i = 0;
        x = x+vx;
        y = y+vy;
        while ((x< SIZE)&&(x>=0)&&(y<SIZE)&&(y>=0)&&(map[y][x]==dot)){
            x = x+vx;
            y = y+vy;
            i++;
        }
        return i;
    }


    boolean isMapFull() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (map[i][j] == DOT_EMPTY)
                    return false;
        return true;
    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE)
            return false;
        return map[y][x] == DOT_EMPTY;
    }
}
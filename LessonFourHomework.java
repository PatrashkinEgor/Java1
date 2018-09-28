/**
 * Java. Level 1. Lesson 4. Homework
 *
 * @author Egor Patrashkin
 * @version dated Sep 24, 2018
 */


import java.util.Random;
import java.util.Scanner;

class TicTacToe {

    final int SIZE = 10;
    final int WINLINE = 2;      //Размер необходимой последовательности
    final char DOT_X = 'x';
    final char DOT_O = 'o';
    final char DOT_EMPTY = '.';
    char[][] map = new char[SIZE][SIZE];
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public static void main(String[] args) {
        new TicTacToe().game();
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
            System.out.println("Enter X and Y (1..3):");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
        return checkWin(x,y, DOT_X);
    }

    boolean aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        map[y][x] = DOT_O;
        printMap();
        return checkWin(x,y, DOT_O);
    }

    boolean checkWin(int x, int y,char dot) {

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
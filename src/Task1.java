import java.util.Scanner;

public class Task1 {
    //1.Реализуйте метод, который находит из трех чисел то, которое делится на 2 остальных; или возвращает -1, если такого нет
    public static void main(String[] args) {
        int enter1 = inputUser();
        int enter2 = inputUser();
        int enter3 = inputUser();
        int m = divisionNum(enter1, enter2, enter3);
        System.out.println(m);
    }

    private static int inputUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число");
        return scanner.nextInt();
    }

    public static int divisionNum(int x, int y, int b) {
        int res;
        if (x % y == 0 && x % b == 0) {
            res = x;
            System.out.println("x = " + res + " Это число делится на эти 2 числа: " + y + " и " + b);
        } else if (y % x == 0 && y % b == 0) {
            res = y;
            System.out.println("y = " + res + " Это число делится на эти 2 числа: " + x + " и " + b);
        } else if (b % y == 0 && b % x == 0) {
            res = b;
            System.out.println("b = " + res + " Это число делится на эти 2 числа: " + x + " и " + y);
        } else {
            res = -1;
            System.out.println("Нет чисел для деления = " + res);
        }
        return res;
    }
}
import java.util.Arrays;

public class Task2 {
    //2.Реализуйте метод, который из двух массивов строк собирает третий, в котором чередуются элементы первых двух
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {10, 20, 30, 40, 50};
        int[] arr = arrayResult(arr1, arr2);
        System.out.println(Arrays.toString(arr));

    }

    private static int[] arrayResult(int[] arr1, int[] arr2) {
        int[] arr3 = new int[arr1.length + arr2.length];
        int count = 0;
        for (int i = 0; i < arr1.length; i++) {
            arr3[i] = arr1[i];
            count++;
        }
        for (int j : arr2) {
            arr3[count++] = j;

        }

        return arr3;
    }


}
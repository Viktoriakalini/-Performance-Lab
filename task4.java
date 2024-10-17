import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Укажите путь к файлу с массивом чисел.");
            return;
        }

        String filePath = args[0];
        List<Integer> nums = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextInt()) {
                nums.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
            return;
        }

        if (nums.isEmpty()) {
            System.out.println("Файл пустой.");
            return;
        }

        Collections.sort(nums);
        int n = nums.size();
        int median = nums.get(n / 2);  

        int totalMoves = 0;
        for (int num : nums) {
            totalMoves += Math.abs(num - median);  
        }

        System.out.println(totalMoves);
    }
}

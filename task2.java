import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Укажите два аргумента: путь к файлу с окружностью и путь к файлу с точками.");
            return;
        }

        String circleFilePath = args[0];
        String pointsFilePath = args[1];

        double centerX = 0, centerY = 0, radius = 0;

        try (Scanner circleScanner = new Scanner(new File(circleFilePath))) {
            if (circleScanner.hasNextDouble()) {
                centerX = circleScanner.nextDouble();
            }
            if (circleScanner.hasNextDouble()) {
                centerY = circleScanner.nextDouble();
            }
            if (circleScanner.hasNextDouble()) {
                radius = circleScanner.nextDouble();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл с окружностью не найден.");
            return;
        }

        try (Scanner pointsScanner = new Scanner(new File(pointsFilePath))) {
            while (pointsScanner.hasNextDouble()) {
                double pointX = pointsScanner.nextDouble();
                if (!pointsScanner.hasNextDouble()) break;
                double pointY = pointsScanner.nextDouble();

                double distance = Math.sqrt(Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2));

                if (distance < radius) {
                    System.out.println(1);  
                } else if (distance == radius) {
                    System.out.println(0);  
                } else {
                    System.out.println(2);  
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл с точками не найден.");
        }
    }
}

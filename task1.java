public class task1 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Укажите два аргумента: n (размер массива) и m (длина интервала).");
            return;
        }

        int n = Integer.parseInt(args[0]); 
        int m = Integer.parseInt(args[1]);
        int[] circularArray = new int[n];
        for (int i = 0; i < n; i++) {
            circularArray[i] = i + 1;
        }

        StringBuilder path = new StringBuilder();
        int startIndex = 0;

        while (path.length() < n) {
            path.append(circularArray[startIndex]); 
            startIndex = (startIndex + m - 1) % n; 
        }

        System.out.println(path.toString());
    }
}

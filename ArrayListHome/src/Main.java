import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("strings.txt"))) {
            ArrayList<String> strings = new ArrayList<>();

            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }

            System.out.println(strings);
        }

        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 8, 4, 5, 6, 3, 4, 9, 10, 12));

        for (int i = 0; i < numbers1.size(); i++) {
            if (numbers1.get(i) % 2 == 0) {
                numbers1.remove(i);
                i--;
            }
        }

        System.out.println(numbers1);

        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(1, 2, 1, 4, 5, 5, 3, 4, 9, 2, 1, 0, 0));
        ArrayList<Integer> numbers3 = new ArrayList<>();

        for (Integer i : numbers2) {
            if (!numbers3.contains(i)) {
                numbers3.add(i);
            }
        }

        System.out.println(numbers3);
    }
}
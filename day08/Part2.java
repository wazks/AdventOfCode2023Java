import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

class Part2 {
    static long arrayLcm(int[] arr) {
        long lcm = arr[0];
        for (long num : arr) {
            lcm = lcm(lcm, num);
        }
        return lcm;
    }

    static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static boolean hasEnded(int[] cycleLengths) {
        for (int length : cycleLengths) {
            if (length == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./input.txt");

        Map<String, String[]> nodes = new HashMap<>();
        ArrayList<String> curNodes = new ArrayList<>();

        char[] directions = inputReader.readLine().toCharArray();

        while (true) {
            String line = inputReader.readLine();

            if (line == null)
                break;

            if (line.isEmpty())
                continue;

            if (line.charAt(2) == 'A')
                curNodes.add(line.substring(0, 3));

            nodes.putIfAbsent(line.substring(0, 3),
                    new String[] { line.substring(7, 10), line.substring(12, 15) });
        }

        long count = 0;

        int[] cycleLengths = new int[curNodes.size()];

        while (!hasEnded(cycleLengths)) {
            for (int i = 0; i < cycleLengths.length; i++) {
                if (cycleLengths[i] != 0)
                    continue;

                if (curNodes.get(i).charAt(2) == 'Z') {
                    cycleLengths[i] = (int) count;
                    continue;
                }

                curNodes.set(i,
                        nodes.get(curNodes.get(i))[directions[(int) (count % directions.length)] == 'L' ? 0 : 1]);
            }

            count++;
        }

        System.out.println(arrayLcm(cycleLengths));
    }
}

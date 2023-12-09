import java.util.ArrayList;

class Part2 {
    static boolean isZeroArray(long[] arr) {
        for (long el : arr) {
            if (el != 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./input.txt");

        long sum = 0;
        while (true) {
            String line = inputReader.readLine();
            if (line == null)
                break;

            String[] stringArr = line.split(" ");
            long[] intArr = new long[stringArr.length + 1];
            for (int i = 1; i <= stringArr.length; i++) {
                intArr[i] = Integer.parseInt(stringArr[i - 1]);
            }

            ArrayList<long[]> history = new ArrayList<>();
            history.add(intArr);

            while (true) {
                int curIndex = history.size() - 1;
                long[] curHistory = history.get(curIndex);

                if (isZeroArray(curHistory))
                    break;

                int curSize = curHistory.length;

                long[] newArr = new long[curSize - 1];
                for (int i = 1; i < curSize - 1; i++) {
                    newArr[i] = (curHistory[i + 1] - curHistory[i]);
                }
                history.add(newArr);

            }

            // add zero to the array of zeroes
            history.get(history.size() - 1)[0] = 0;

            // change the placeholder to sum
            for (int i = history.size() - 2; i >= 0; i--) {
                history.get(i)[0] = (history.get(i)[1]
                        - history.get(i + 1)[0]);
            }

            sum += history.get(0)[0];
        }
        System.out.println(sum);
    }
}

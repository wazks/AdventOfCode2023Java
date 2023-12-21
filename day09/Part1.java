import java.util.ArrayList;

class Part1 {
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
            long[] intArr = new long[stringArr.length];
            for (int i = 0; i < stringArr.length; i++) {
                intArr[i] = Integer.parseInt(stringArr[i]);
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
                for (int i = 0; i < curSize - 1; i++) {
                    newArr[i] = (curHistory[i + 1] - curHistory[i]);
                }
                history.add(newArr);

            }

            // add zero to the array of zeroes
            history.get(history.size() - 1)[history.get(history.size() - 1).length - 1] = 0;

            // change the placeholder to sum
            for (int i = history.size() - 2; i >= 0; i--) {
                history.get(i)[history.get(i).length - 1] = (history.get(i)[history.get(i).length - 1]
                        + history.get(i + 1)[history.get(i + 1).length - 1]);
            }

            sum += history.get(0)[history.get(0).length - 1];
        }
        System.out.println(sum);
    }
}

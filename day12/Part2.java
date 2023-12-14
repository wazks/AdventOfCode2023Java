import java.util.Arrays;

class Part2 {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./input.txt");

        Permutations permutations = new Permutations();

        System.out.println("permutations done");

        long sum1 = 0;
        long sum2 = 0;
        long trueSum = 0;
        while (true) {
            String line = inputReader.readLine();
            if (line == null)
                break;

            String[] splitLine = line.split(" ");
            int[] groupLengths = Arrays.stream(splitLine[1].split(",")).mapToInt(Integer::parseInt).toArray();
            int[] newGroupLengths = new int[groupLengths.length * 5];

            for (int i = 0; i < newGroupLengths.length; i++) {
                newGroupLengths[i] = groupLengths[i % groupLengths.length];
            }

            String inputState = "";
            for (int i = 0; i < 4; i++) {
                inputState += splitLine[0] + "?";
            }
            inputState += splitLine[0];

            // Group group = new Group(inputState, newGroupLengths);
            Group group = new Group(splitLine[0], groupLengths);
            sum1 = group.sumOfAllPermutations(permutations, true);
            sum2 = group.sumOfAllPermutations(permutations, false);
            trueSum += sum1 * Math.pow(sum2, 4);
            System.out.println(sum1 * Math.pow(sum2, 4));

        }
        System.out.println(trueSum);
    }
}

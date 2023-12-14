import java.util.Arrays;

class Part1 {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./input.txt");

        Permutations permutations = new Permutations();

        System.out.println("permutations done");

        int sum = 0;
        while (true) {
            String line = inputReader.readLine();
            if (line == null)
                break;

            String[] splitLine = line.split(" ");
            int[] groupLengths = Arrays.stream(splitLine[1].split(",")).mapToInt(Integer::parseInt).toArray();
            Group group = new Group(splitLine[0], groupLengths);
            sum += group.sumOfAllPermutations(permutations);
        }
        System.out.println(sum);
    }
}

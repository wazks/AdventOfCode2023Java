class Day6 {
    static long equation(long t, long d) {
        double firstRoot = 0.5 * (-t + Math.sqrt(Math.pow(t, 2) - 4 * d));
        double secondRoot = 0.5 * (-t - Math.sqrt(Math.pow(t, 2) - 4 * d));

        return (long) Math.floor(firstRoot) - (long) Math.ceil(secondRoot) + 1;

    }

    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./input.txt");

        String[] times = inputReader.readLine().replaceAll("\\s+", " ").split(" ");
        String[] distances = inputReader.readLine().replaceAll("\\s+", " ").split(" ");

        int sum = 1;
        for (int i = 1; i < times.length; i++) {
            sum *= equation(Integer.parseInt(times[i]), Integer.parseInt(distances[i]));
        }
        System.out.println("Part1: " + sum);

        StringBuilder time = new StringBuilder();
        StringBuilder distance = new StringBuilder();
        for (int i = 1; i < times.length; i++) {
            time.append(times[i]);
            distance.append(distances[i]);
        }
        System.out.println("Part2: " + equation(Long.parseLong(time.toString()), Long.parseLong(distance.toString())));
    }
}

class Part1 {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./input.txt");

        Universe universe = new Universe();
        while (true) {
            String line = inputReader.readLine();
            if (line == null)
                break;

            universe.addRow(line);
        }

        universe.expandSpace(2);
        universe.printSumOfShortestPaths();
    }
}

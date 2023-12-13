class Part2 {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./inpute.txt");

        Universe universe = new Universe();
        while (true) {
            String line = inputReader.readLine();
            if (line == null)
                break;

            universe.addRow(line);
        }

        universe.expandSpace(1000000);
        universe.printSumOfShortestPaths();
    }
}

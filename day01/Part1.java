class Part1 {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./input.txt");

        int sum = 0;
        while (true) {
            String line = inputReader.readLine();
            if (line == null)
                break;

            int first = -1;
            int last = -1;
            for (int i = 0; i < line.length(); i++) {

                if (Character.isDigit(line.charAt(i))) {
                    int value = Character.getNumericValue(line.charAt(i));
                    if (first == -1)
                        first = value;
                    else
                        last = value;
                }
            }

            if (last == -1)
                last = first;
            sum += (first * 10 + last);
        }
        System.out.println(sum);
    }
}

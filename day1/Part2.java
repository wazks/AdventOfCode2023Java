class Part2 {

    static int parseNum(String line, int i) {
        if (Character.isDigit(line.charAt(i)))
            return Character.getNumericValue(line.charAt(i));

        String word = line.substring(i, Math.min(i + 5, line.length()));

        if (word.length() > 2)
            switch (word.substring(0, 3)) {
                case "one":
                    return 1;
                case "two":
                    return 2;
                case "six":
                    return 6;
                default:
                    if (word.length() > 3)
                        switch (word.substring(0, 4)) {
                            case "four":
                                return 4;
                            case "five":
                                return 5;
                            case "nine":
                                return 9;
                            default:
                                if (word.length() > 4)
                                    switch (word.substring(0, 5)) {
                                        case "three":
                                            return 3;
                                        case "seven":
                                            return 7;
                                        case "eight":
                                            return 8;
                                    }
                        }
            }

        return -1;
    }

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
                int parsedNum = parseNum(line, i);
                if (parsedNum != -1) {
                    if (first == -1)
                        first = parsedNum;
                    else
                        last = parsedNum;
                }
            }

            if (last == -1)
                last = first;

            sum += (first * 10 + last);
        }
        System.out.println(sum);
    }
}

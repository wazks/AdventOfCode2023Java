class Part1 {
    public static boolean isAdjacent(char c) {
        if (Character.isDigit(c) || c == '.')
            return false;
        else
            return true;
    }

    public static boolean isAdjacentToSymbol(String inputLine, int lineLength, int i) {

        System.out.println(inputLine.charAt(i));

        if (i >= lineLength - 1)
            if (isAdjacent(inputLine.charAt(i - lineLength - 1)))
                return true;
        if (i >= lineLength)
            if (isAdjacent(inputLine.charAt(i - lineLength)))
                return true;
        if (i >= lineLength + 1)
            if (isAdjacent(inputLine.charAt(i - lineLength + 1)))
                return true;
        if (i > 0)
            if (isAdjacent(inputLine.charAt(i - 1)))
                return true;
        if (i < inputLine.length())
            if (isAdjacent(inputLine.charAt(i + 1)))
                return true;
        if (i < inputLine.length() - lineLength - 1)
            if (isAdjacent(inputLine.charAt(i + lineLength - 1)))
                return true;
        if (i < inputLine.length() - lineLength)
            if (isAdjacent(inputLine.charAt(i + lineLength)))
                return true;
        if (i < inputLine.length() - lineLength + 1)
            if (isAdjacent(inputLine.charAt(i + lineLength + 1)))
                return true;

        return false;
    }

    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./input.txt");

        String inputLine = "";
        int numOfLines = 0;
        int lineLength = 0;
        while (true) {
            String line = inputReader.readLine();
            if (line == null)
                break;
            numOfLines++;
            inputLine += line;
        }
        lineLength = inputLine.length() / numOfLines;

        boolean isOnCorrectNum = false;
        String currentNum = "";
        int sum = 0;
        for (int i = 0; i < inputLine.length(); i++) {
            char c = inputLine.charAt(i);

            if (!Character.isDigit(c)) {
                if (currentNum != "" && isOnCorrectNum)
                    sum += Integer.parseInt(currentNum);
                System.out.println(currentNum + " " + isOnCorrectNum);
                currentNum = "";
                isOnCorrectNum = false;

                continue;
            } else {
                currentNum += c;
            }

            if (isOnCorrectNum)
                continue;

            if (isAdjacentToSymbol(inputLine, lineLength, i))
                isOnCorrectNum = true;
            else
                isOnCorrectNum = false;

        }
        System.out.println(sum);
    }
}

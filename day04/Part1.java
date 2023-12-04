import java.util.ArrayList;

class Part1 {

    public static class Scratchcard {
        ArrayList<Integer> winningNumbers = new ArrayList<>();
        ArrayList<Integer> chosenNumbers = new ArrayList<>();
        int wonNumbers = 0;

        public void addWinningNumber(int num) {
            winningNumbers.add(num);
        }

        public void addChosenNumber(int num) {
            chosenNumbers.add(num);
        }

        private void calcNumOfWonNumbers() {
            for (int wnum : winningNumbers) {
                for (int cnum : chosenNumbers) {
                    if (wnum == cnum) {
                        this.wonNumbers++;
                    }
                }
            }
        }

        public int getPoints() {
            calcNumOfWonNumbers();

            if (this.wonNumbers == 0)
                return 0;
            return (int) Math.pow(2, this.wonNumbers - 1);
        }
    }

    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./input.txt");

        int sum = 0;
        while (true) {
            String line = inputReader.readLine();
            if (line == null)
                break;

            Scratchcard scratchcard = new Scratchcard();

            boolean parsing = false;
            boolean chosenNumbers = false;
            char last = ':';
            char secondToLast = ' ';
            int curNum = 0;
            for (int i = 5; i < line.length(); i++) {
                char c = line.charAt(i);
                if (!parsing) {
                    if (c == ':')
                        parsing = true;

                    continue;
                }

                if (c == '|') {
                    secondToLast = last;
                    last = c;

                    chosenNumbers = true;
                    continue;
                }

                if (c == ' ') {
                    if (curNum != 0) {
                        if (chosenNumbers)
                            scratchcard.addChosenNumber(curNum);
                        else
                            scratchcard.addWinningNumber(curNum);
                    }
                    curNum = 0;
                } else if (last == ' ' && secondToLast != last) {
                    curNum += 10 * Character.getNumericValue(c);
                } else {
                    curNum += Character.getNumericValue(c);
                }
                secondToLast = last;
                last = c;
            }
            scratchcard.addChosenNumber(curNum);

            sum += scratchcard.getPoints();
        }
        System.out.println(sum);
    }
}

import java.util.ArrayList;

class Part2 {

    public static class Scratchcard {
        ArrayList<Integer> winningNumbers = new ArrayList<>();
        ArrayList<Integer> chosenNumbers = new ArrayList<>();
        int wonNumbers = 0;
        long count = 1;

        public void addWinningNumber(int num) {
            winningNumbers.add(num);
        }

        public void addChosenNumber(int num) {
            chosenNumbers.add(num);
        }

        public void calcNumOfWonNumbers() {
            for (int wnum : winningNumbers) {
                for (int cnum : chosenNumbers) {
                    if (wnum == cnum) {
                        this.wonNumbers++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./input.txt");

        long sum = 0;

        ArrayList<Scratchcard> scratchcards = new ArrayList<>();

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

            scratchcards.add(scratchcard);
        }

        for (int i = 0; i < scratchcards.size(); i++) {
            scratchcards.get(i).calcNumOfWonNumbers();
            for (int j = 1; j <= scratchcards.get(i).wonNumbers; j++) {
                scratchcards.get(i + j).count += scratchcards.get(i).count;
            }
            sum += scratchcards.get(i).count;
        }

        System.out.println(sum);
    }
}

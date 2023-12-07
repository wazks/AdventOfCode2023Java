import java.util.ArrayList;
import java.util.Collections;

class Part1 {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./input.txt");

        ArrayList<Hand> hands = new ArrayList<>();
        while (true) {
            String line = inputReader.readLine();
            if (line == null)
                break;

            String[] splitLine = line.split(" ");

            // don't forget to init ranks later
            hands.add(new Hand(splitLine[0].toCharArray(), Integer.parseInt(splitLine[1])));
        }
        Collections.sort(hands, (hand1, hand2) -> (hand1.isStronger(hand2) ? 1 : -1));

        int sum = 0;

        for (int i = 0; i < hands.size(); i++) {
            sum += hands.get(i).getWinnings(i + 1);
        }
        System.out.println(sum);
    }
}

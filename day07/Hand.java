import java.util.Arrays;

public class Hand {
    private char[] cards = new char[5];
    private int bid = 0;
    private Type type;

    Hand(char[] cards, int bid) {
        for (int i = 0; i < 5; i++) {
            this.cards[i] = cards[i];
        }
        this.bid = bid;
        calcType();
    }

    private void calcType() {

        char[] sorted = Arrays.copyOf(this.cards, 5);
        Arrays.sort(sorted);

        /*
         * five: four right pairs
         * four: three right pairs (the wrong el is at the end or start)
         * full: three right pairs and either [1] and [2] or [4] and [5] are the same
         * three: two right pairs ([3] is always element of the three, either [2] and
         * [3] or [3] and [4] are the same)
         * two pairs: two right pairs
         * one pair: one right pairs
         * else highcard
         */

        int rightPairs = 0;
        for (int i = 0; i < 4; i++) {
            if (sorted[i] == sorted[i + 1]) {
                rightPairs++;
            }
        }

        switch (rightPairs) {
            case 4:
                this.type = Type.FIVE;
                break;
            case 3:
                if (sorted[1] == sorted[3])
                    this.type = Type.FOUR;
                else
                    this.type = Type.FULL;
                break;
            case 2:
                if (sorted[0] == sorted[2] || sorted[2] == sorted[4] || sorted[1] == sorted[3])
                    this.type = Type.THREE;
                else
                    this.type = Type.TWOPAIRS;
                break;
            case 1:
                this.type = Type.PAIR;
                break;
            default:
                this.type = Type.HIGHCARD;
                break;
        }
    }

    public int getWinnings(int rank) {
        return this.bid * rank;
    }

    public boolean isStronger(Hand otherHand) {
        if (this.type.strength > otherHand.type.strength)
            return true;
        else if (this.type.strength < otherHand.type.strength)
            return false;
        else {

            char[] copyOf1 = Arrays.copyOf(this.cards, 5);
            char[] copyOf2 = Arrays.copyOf(otherHand.cards, 5);

            fixCharCodeOrdering(copyOf1);
            fixCharCodeOrdering(copyOf2);

            for (int i = 0; i < 5; i++) {
                if (copyOf1[i] == copyOf2[i])
                    continue;

                if (copyOf1[i] > copyOf2[i])
                    return true;
                else
                    return false;
            }

            throw new RuntimeException("The hands are exactly the same.");
        }
    }

    private void fixCharCodeOrdering(char[] arr) {
        for (int i = 0; i < 5; i++) {
            switch (arr[i]) {
                case 'A':
                    arr[i] = (char) '9' + 5;
                    break;
                case 'K':
                    arr[i] = (char) '9' + 4;
                    break;
                case 'Q':
                    arr[i] = (char) '9' + 3;
                    break;
                case 'J':
                    arr[i] = (char) '9' + 2;
                    break;
                case 'T':
                    arr[i] = (char) '9' + 1;
                    break;
            }
        }
    }
}
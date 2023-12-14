import java.util.Arrays;

public class Group {
    String state;
    int numOfUnknowns = 0;
    int numOfUnknownHashes = 0;
    int[] groupLengths;

    Group(String state, int[] groupLengths) {
        this.state = state;
        this.groupLengths = groupLengths;

        // char[] stateCharArr = state.toCharArray();
        // for (int i = 0; i < state.length(); i++) {
        //
        // if (stateCharArr[i] == '#') {
        // if (i > 0)
        // if (stateCharArr[i - 1] != '#') {
        // stateCharArr[i - 1] = '.';
        // }
        // if (i < state.length() - 1)
        // if (stateCharArr[i + 1] != '#') {
        // stateCharArr[i + 1] = '.';
        // }
        // }
        // }
        // this.state = new String(stateCharArr);

        System.out.println(this.state);

        getNumOfUnkwnownHashes();
    }

    public void getNumOfUnkwnownHashes() {
        int allHashes = 0;
        for (int n : this.groupLengths)
            allHashes += n;

        int knownHashes = 0;
        for (char spring : state.toCharArray()) {
            if (spring == '?')
                this.numOfUnknowns++;
            if (spring == '#')
                knownHashes++;
        }
        this.numOfUnknownHashes = allHashes - knownHashes;
    }

    public char[] getNthStatePermutation(char[] perm) {
        int curBit = 0;

        char[] stateArr = this.state.toCharArray();
        for (int i = 0; i < this.state.length(); i++) {
            if (stateArr[i] == '?') {
                stateArr[i] = perm[curBit];
                curBit++;
            }
        }

        return stateArr;
    }

    public boolean isValidPermutation(char[] permutation) {
        int curGroup = 0;
        int curGroupLength = 0;

        char[] newPerm = Arrays.copyOf(permutation, permutation.length + 1);
        newPerm[newPerm.length - 1] = '.';

        for (char c : newPerm) {
            if (c == '#') {
                curGroupLength++;
            } else {
                if (curGroupLength > 0) {
                    if (curGroup >= this.groupLengths.length)
                        return false;
                    if (this.groupLengths[curGroup] != curGroupLength)
                        return false;
                    curGroup++;
                    curGroupLength = 0;
                }
            }
        }
        if (curGroup != this.groupLengths.length)
            return false;

        return true;
    }

    public long sumOfAllPermutations(Permutations permutations, boolean changeState) {
        long ored = 0b0;
        long sum = 0;
        for (char[] perm : permutations.possibleBinaryForNUnknowns.get(this.numOfUnknownHashes)) {
            if (perm.length > numOfUnknowns)
                break;

            char[] padded = String.format("%" + this.numOfUnknowns + "s", new String(perm)).replace(" ", ".")
                    .toCharArray();

            StringBuilder binaryString = new StringBuilder();

            char[] statePerm = getNthStatePermutation(padded);
            if (isValidPermutation(getNthStatePermutation(padded))) {
                sum++;
                for (char n : statePerm) {
                    if (n == '.')
                        binaryString.append(0);
                    else
                        binaryString.append(1);
                }
                ored = ored | Integer.parseInt(binaryString.toString(), 2);
            }
        }
        if (changeState) {
            char[] binaryCoded = String.format("%" + this.state.length() + "s", Long.toBinaryString(ored))
                    .replace(" ",
                            "0")
                    .toCharArray();

            char[] newState = new char[this.state.length() + 1];

            System.out.println(binaryCoded);
            if (binaryCoded[0] == '0' || (binaryCoded[0] == '1' && binaryCoded[binaryCoded.length - 1] == '1')) {
                newState[this.state.length()] = '?';
                for (int i = 0; i < this.state.length(); i++) {
                    newState[i] = this.state.charAt(i);
                }
            } else if (binaryCoded[binaryCoded.length - 1] == '0'
                    || (binaryCoded[0] == '0' && binaryCoded[binaryCoded.length - 1] == '0')) {
                newState[0] = '?';
                for (int i = 1; i <= this.state.length(); i++) {
                    newState[i] = this.state.charAt(i - 1);
                }
            }
            numOfUnknowns++;

            this.state = new String(newState);
            return sum;
        } else
            return sum;
    }
}

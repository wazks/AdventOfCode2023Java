import java.util.ArrayList;
import java.util.HashMap;

public class Permutations {
    public HashMap<Integer, ArrayList<char[]>> possibleBinaryForNUnknowns = new HashMap<>();

    public Permutations() {
        getPossibleBinaryNums();
    }

    private void getPossibleBinaryNums() {
        int cur = 1;
        int it = 0;
        for (int n = 0; n < Math.pow(2, 20); n++) {
            char[] binaryString = Integer.toBinaryString(n).toCharArray();
            int numOfUnknowns = 0;
            for (char c : binaryString) {
                if (c == '1')
                    numOfUnknowns++;
            }
            for (int i = 0; i < binaryString.length; i++) {
                if (binaryString[i] == '0')
                    binaryString[i] = '.';
                else
                    binaryString[i] = '#';
            }
            possibleBinaryForNUnknowns.putIfAbsent(numOfUnknowns, new ArrayList<char[]>());
            possibleBinaryForNUnknowns.get(numOfUnknowns).add(binaryString);

            if (n == cur) {
                System.out.println(it);
                cur *= 2;
                it++;
            }
        }

    }

}

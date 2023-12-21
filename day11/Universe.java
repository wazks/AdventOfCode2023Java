import java.util.ArrayList;
import java.util.Arrays;

public class Universe {
    private ArrayList<ArrayList<Character>> universe = new ArrayList<>();
    private int expandFactor;

    private Boolean[] isExpandedRow;
    private Boolean[] isExpandedColumn;

    public void addRow(String line) {
        char[] charArr = line.toCharArray();
        ArrayList<Character> toAdd = new ArrayList<>();

        for (char c : charArr) {
            toAdd.add(c);
        }

        this.universe.add(toAdd);
    };

    public void expandSpace(int n) {
        this.expandFactor = n;

        this.isExpandedRow = new Boolean[this.universe.get(0).size()];
        this.isExpandedColumn = new Boolean[this.universe.get(0).size()];
        Arrays.fill(this.isExpandedRow, Boolean.FALSE);
        Arrays.fill(this.isExpandedColumn, Boolean.FALSE);

        columns: for (int x = 0; x < this.universe.get(0).size(); x++) {
            for (int y = 0; y < universe.size(); y++) {
                if (this.universe.get(y).get(x) == '#')
                    continue columns;
            }
            this.isExpandedColumn[x] = true;
        }

        rows: for (int y = this.universe.size() - 1; y >= 0; y--) {
            ArrayList<Character> row = this.universe.get(y);
            for (char c : row) {
                if (c == '#')
                    continue rows;
            }
            this.isExpandedRow[y] = true;
        }
    }

    public void printUniverse() {
        for (ArrayList<Character> row : this.universe) {
            StringBuilder rowToPrint = new StringBuilder();
            for (Character c : row) {
                rowToPrint.append(c);
            }
            System.out.println(rowToPrint.toString());
        }
    }

    public void printSumOfShortestPaths() {
        long sum = 0;
        for (int y = 0; y < universe.size(); y++) {
            int realX = -1;
            for (int x = 0; x < universe.get(0).size(); x++) {
                if (isExpandedColumn[x])
                    realX += this.expandFactor;
                else
                    realX++;

                if (universe.get(y).get(x) == '#') {

                    int realY1 = y - 1;
                    for (int y1 = y; y1 < universe.size(); y1++) {
                        if (isExpandedRow[y1])
                            realY1 += this.expandFactor;
                        else
                            realY1++;
                        int realX1 = (y1 == y ? realX : -1);
                        for (int x1 = (y1 == y ? x + 1 : 0); x1 < universe.get(0).size(); x1++) {

                            if (isExpandedColumn[x1])
                                realX1 += this.expandFactor;
                            else
                                realX1++;

                            if (universe.get(y1).get(x1) == '#') {
                                sum += (Math.abs(realX1 - realX)
                                        + Math.abs(realY1 - y));
                            }
                        }
                    }
                }
            }
        }
        System.out.println(sum);
    }
}

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

class Part2 {
    static class Gear {
        int position = -1;
        Set<Integer> adjacentNumbers = new HashSet<>();

        Gear(int i) {
            position = i;
        }

        boolean getGearByPos(int i) {
            if (this.position == i)
                return true;
            else
                return false;
        }

        void addNum(int n) {
            adjacentNumbers.add(n);
        }

        boolean isValid() {
            if (adjacentNumbers.size() == 2)
                return true;
            else
                return false;
        }

        int ratio() {
            int result = 1;
            for (int num : adjacentNumbers) {
                result *= num;
            }
            return result;
        }
    }

    public static int adjacentGearPosition(int i, ArrayList<Gear> gears) {
        int adjacentGearPosition = -1;
        for (Gear gear : gears) {
            if (gear.getGearByPos(i))
                adjacentGearPosition = i;
        }
        return adjacentGearPosition;
    }

    public static ArrayList<Integer> getAdjacentGearsPositions(String inputLine, int lineLength, int i,
            ArrayList<Gear> gears) {
        ArrayList<Integer> adjacentGearsPositions = new ArrayList<>();

        if (i >= lineLength - 1)
            adjacentGearsPositions.add(adjacentGearPosition(i - lineLength - 1, gears));
        if (i >= lineLength)
            adjacentGearsPositions.add(adjacentGearPosition(i - lineLength, gears));
        if (i >= lineLength + 1)
            adjacentGearsPositions.add(adjacentGearPosition(i - lineLength + 1, gears));
        if (i > 0)
            adjacentGearsPositions.add(adjacentGearPosition(i - 1, gears));
        if (i < inputLine.length())
            adjacentGearsPositions.add(adjacentGearPosition(i + 1, gears));
        if (i < inputLine.length() - lineLength - 1)
            adjacentGearsPositions.add(adjacentGearPosition(i + lineLength - 1, gears));
        if (i < inputLine.length() - lineLength)
            adjacentGearsPositions.add(adjacentGearPosition(i + lineLength, gears));
        if (i < inputLine.length() - lineLength + 1)
            adjacentGearsPositions.add(adjacentGearPosition(i + lineLength + 1, gears));

        ArrayList<Integer> validGears = new ArrayList<>();
        for (Integer adjacentGear : adjacentGearsPositions) {
            if (adjacentGear != -1) {
                validGears.add(adjacentGear);
            }
        }

        return validGears;
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
        ArrayList<Gear> gears = new ArrayList<>();
        Set<Integer> gearsAdjacentToNum = new HashSet<>();

        for (int i = 0; i < inputLine.length(); i++) {
            if (inputLine.charAt(i) == '*')
                gears.add(new Gear(i));
        }
        for (int i = 0; i < inputLine.length(); i++) {
            char c = inputLine.charAt(i);

            if (!Character.isDigit(c)) {
                if (currentNum != "") {
                    for (int gearPos : gearsAdjacentToNum) {
                        for (Gear gear : gears) {
                            if (gear.getGearByPos(gearPos)) {
                                gear.addNum(Integer.parseInt(currentNum));
                            }

                        }
                    }
                }
                gearsAdjacentToNum = new HashSet<>();
                currentNum = "";

                continue;
            } else {
                currentNum += c;
            }

            ArrayList<Integer> adjacentGearsPositions = new ArrayList<>(
                    getAdjacentGearsPositions(inputLine, lineLength, i, gears));
            for (int gearPos : adjacentGearsPositions) {
                gearsAdjacentToNum.add(gearPos);
            }
        }

        int sum = 0;
        for (Gear gear : gears) {
            if (gear.isValid()) {
                sum += gear.ratio();
            }
        }
        System.out.println(sum);

    }
}

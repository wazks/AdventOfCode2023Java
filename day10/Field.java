import java.util.ArrayList;
import java.util.Arrays;

public class Field {
    public ArrayList<char[]> elements = new ArrayList<>();
    public ArrayList<Boolean[]> elementsInLoop = new ArrayList<>();
    public int loopLength = 0;
    public int[] startingPosition = new int[2];
    public int startingDirection;

    public Tile currentElementType;
    public int[] currentElement = new int[2];
    public int cameFrom;

    public void addToElements(String elements) {
        char[] charArr = elements.toCharArray();
        this.elements.add(charArr);

        this.elementsInLoop.add(new Boolean[charArr.length]);
        Arrays.fill(this.elementsInLoop.get(this.elementsInLoop.size() - 1), Boolean.FALSE);

        for (int x = 0; x < elements.length(); x++) {
            if (charArr[x] == 'S') {
                this.startingPosition[0] = x;
                this.startingPosition[1] = this.elements.size() - 1;
                elementsInLoop.get(startingPosition[1])[startingPosition[0]] = true;
                break;

            }
        }

    }

    public char getElement(int x, int y) {
        return this.elements.get(y)[x];
    }

    public void calculateLoop() {
        while (currentElementType != Tile.START) {
            loopLength++;

            if (cameFrom == 2) {
                switch (currentElementType) {
                    case N_S:
                        currentElement[1] -= 1;
                        cameFrom = 2;
                        break;
                    case S_E:
                        currentElement[0] += 1;
                        cameFrom = 3;
                        break;
                    case S_W:
                        currentElement[0] -= 1;
                        cameFrom = 1;
                        break;
                    default:
                        throw new IllegalArgumentException("No such tile." + currentElementType);
                }
            } else if (cameFrom == 0) {
                switch (currentElementType) {
                    case N_S:
                        currentElement[1] += 1;
                        cameFrom = 0;
                        break;
                    case N_E:
                        currentElement[0] += 1;
                        cameFrom = 3;
                        break;
                    case N_W:
                        currentElement[0] -= 1;
                        cameFrom = 1;
                        break;
                    default:
                        throw new IllegalArgumentException("No such tile." + currentElementType);
                }
            } else if (cameFrom == 1) {
                switch (currentElementType) {
                    case N_E:
                        currentElement[1] -= 1;
                        cameFrom = 2;
                        break;
                    case S_E:
                        currentElement[1] += 1;
                        cameFrom = 0;
                        break;
                    case E_W:
                        currentElement[0] -= 1;
                        cameFrom = 1;
                        break;
                    default:
                        throw new IllegalArgumentException("No such tile." + currentElementType);
                }
            } else if (cameFrom == 3) {
                switch (currentElementType) {
                    case N_W:
                        currentElement[1] -= 1;
                        cameFrom = 2;
                        break;
                    case S_W:
                        currentElement[1] += 1;
                        cameFrom = 0;
                        break;
                    case E_W:
                        currentElement[0] += 1;
                        cameFrom = 3;
                        break;
                    default:
                        throw new IllegalArgumentException("No such tile." + currentElementType);
                }
            }

            elementsInLoop.get(currentElement[1])[currentElement[0]] = true;
            currentElementType = Tile.getByChar(getElement(currentElement[0], currentElement[1]));
        }

        if ((startingDirection == 0 && cameFrom == 1) ||
                (startingDirection == 1 && cameFrom == 0))
            elements.get(startingPosition[1])[startingPosition[0]] = 'L';
        else if ((startingDirection == 2 && cameFrom == 3) ||
                (startingDirection == 3 && cameFrom == 2))
            elements.get(startingPosition[1])[startingPosition[0]] = '7';
    }

    public void findConnectingElementToStart() {
        loopLength++;

        int row = startingPosition[1];
        int column = startingPosition[0];

        char upperElement = (row > 0) ? getElement(column, row + 1) : ' ';
        char lowerElement = (row < elements.size()) ? getElement(column, row + 1) : ' ';
        char leftElement = (column > 0) ? getElement(column - 1, row) : ' ';
        char rightElement = (column < elements.get(0).length) ? getElement(column + 1, row) : ' ';

        if (upperElement == '|' || upperElement == '7' || upperElement == 'F') {
            currentElement[0] = column;
            currentElement[1] = row - 1;
            cameFrom = 2;
            startingDirection = 0;
        }

        if (lowerElement == '|' || lowerElement == 'L' || lowerElement == 'J') {
            currentElement[0] = column;
            currentElement[1] = row + 1;
            cameFrom = 0;
            startingDirection = 2;
        }

        if (leftElement == '-' || leftElement == 'L' || leftElement == 'F') {
            currentElement[0] = column - 1;
            currentElement[1] = row;
            cameFrom = 1;
            startingDirection = 3;
        }

        if (rightElement == '-' || rightElement == '7' || rightElement == 'J') {
            currentElement[0] = column + 1;
            currentElement[1] = row;
            cameFrom = 3;
            startingDirection = 1;
        }

        elementsInLoop.get(currentElement[1])[currentElement[0]] = true;
        currentElementType = Tile.getByChar(getElement(currentElement[0], currentElement[1]));
    }
}

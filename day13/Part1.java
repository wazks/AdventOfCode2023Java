import java.util.ArrayList;

public class Part1 {
    public static class Field {
        char[][] pattern;
        char[][] patternRotated;

        public Field(ArrayList<String> stringArr) {
            this.pattern = new char[stringArr.size()][stringArr.get(0).length()];

            for (int i = 0; i < stringArr.size(); i++) {
                for (int j = 0; j < stringArr.get(0).length(); j++) {
                    this.pattern[i][j] = stringArr.get(i).toCharArray()[j];
                }

            }
        }

        private char[][] rotate() {
            patternRotated = new char[this.pattern[0].length][this.pattern.length];
            for (int y = 0; y < this.pattern.length; y++) {
                for (int x = 0; x < this.pattern[0].length; x++) {
                    patternRotated[x][y] = this.pattern[y][x];
                }
            }

            return patternRotated;
        }

        private int findHorizontalReflection() {
            String[] stringArr = new String[this.pattern.length];

            for (int i = 0; i < this.pattern.length; i++) {
                stringArr[i] = new String(this.pattern[i]);
            }

            line: for (int i = 1; i < stringArr.length; i++) {
                for (int j = 0; j < stringArr.length; j++) {
                    if (i + j >= stringArr.length || i - j - 1 < 0)
                        return i * 100;
                    if (!stringArr[i + j].equals(stringArr[i - j - 1]))
                        continue line;
                }
            }
            return 0;
        }

        private int findVerticalReflection() {
            char[][] patternRotated = this.rotate();
            String[] stringArr = new String[patternRotated.length];

            for (int i = 0; i < patternRotated.length; i++) {
                stringArr[i] = new String(patternRotated[i]);
            }
            line: for (int i = 1; i < stringArr.length; i++) {
                for (int j = 0; j < stringArr.length; j++) {
                    if (i + j >= stringArr.length || i - j - 1 < 0)
                        return i;
                    if (!stringArr[i + j].equals(stringArr[i - j - 1]))
                        continue line;
                }
            }
            return 0;
        }

        public int findReflection() {
            int result = 0;
            result = this.findHorizontalReflection();

            if (result == 0)
                result = this.findVerticalReflection();

            if (result == 0)
                System.out.println("oopsie");
            else
                System.out.println("yay");
            return result;
        }
    }

    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./input.txt");

        int sum = 0;

        ArrayList<Field> fieldArray = new ArrayList<>();

        ArrayList<String> stringArr = new ArrayList<>();
        while (true) {
            String line = inputReader.readLine();
            if (line == null || line.isBlank()) {
                fieldArray.add(new Field(stringArr));
                stringArr = new ArrayList<>();

                if (line == null)
                    break;

                continue;
            }

            stringArr.add(line);
        }

        for (Field f : fieldArray) {
            sum += f.findReflection();
        }

        System.out.println(sum);
    }
}

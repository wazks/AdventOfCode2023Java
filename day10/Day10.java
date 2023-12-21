class Day10 {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./input.txt");

        Field field = new Field();

        while (true) {
            String line = inputReader.readLine();
            if (line == null)
                break;

            field.addToElements(line);
        }

        // starting with S, direction doesn't matter
        field.findConnectingElementToStart();

        field.calculateLoop();

        System.out.println(field.loopLength / 2);

        int sum = 0;
        for (int y = 0; y < field.elements.size(); y++) {
            for (int x = 0; x < field.elements.get(0).length; x++) {
                if (field.elementsInLoop.get(y)[x] == true) {
                    continue;
                }

                int boundaryCrossings = 0;

                int i = 0;
                while (true) {
                    i++;

                    if (y + i >= field.elementsInLoop.size() || x + i >= field.elementsInLoop.get(0).length)
                        break;

                    if (field.elementsInLoop.get(y + i)[x + i]) {
                        boundaryCrossings++;

                        char el = field.getElement(x + i, y + i);

                        if (el == 'L' || el == '7')
                            boundaryCrossings++;
                    }
                }

                if (boundaryCrossings % 2 == 1) {
                    sum++;

                }
            }

        }
        System.out.println(sum);
    }

}
class Part1 {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./input.txt");

        // r,g,b
        int[] maxColors = { 12, 13, 14 };

        int sum = 0;
        while (true) {
            String line = inputReader.readLine();
            if (line == null)
                break;

            int id = Integer.parseInt(line.split(":")[0].substring(5));
            String[] sets = line.split(":")[1].split(";");

            boolean isValid = true;
            for (String set : sets) {
                String[] rounds = set.split(",");

                int[] curColors = { 0, 0, 0 };

                for (String round : rounds) {
                    round = round.trim();
                    System.out.println(round);
                    int amount = Integer.parseInt(round.split(" ")[0]);
                    String color = round.split(" ")[1];

                    switch (color) {
                        case "red":
                            curColors[0] += amount;
                            break;
                        case "green":
                            curColors[1] += amount;
                            break;
                        case "blue":
                            curColors[2] += amount;
                            break;
                    }
                }

                for (int i = 0; i < 3; i++) {
                    if (curColors[i] > maxColors[i])
                        isValid = false;
                }
            }
            if (isValid)
                sum += id;
        }
        System.out.println(sum);
    }
}
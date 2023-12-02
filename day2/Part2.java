class Part2 {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./input.txt");

        // r,g,b

        int sum = 0;
        while (true) {
            String line = inputReader.readLine();
            if (line == null)
                break;

            String[] sets = line.split(":")[1].split(";");

            int[] highestColors = { 0, 0, 0 };
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
                    if (curColors[i] > highestColors[i])
                        highestColors[i] = curColors[i];
                }
            }
            int power = highestColors[0] * highestColors[1] * highestColors[2];
            sum += power;
        }
        System.out.println(sum);
    }
}
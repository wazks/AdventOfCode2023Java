import java.util.Arrays;

class Part1 {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./input.txt");

        String[] l = inputReader.readLine().split(" ");
        long[] seeds = new long[l.length - 1];
        for (int i = 1; i < l.length; i++) {
            seeds[i - 1] = Long.parseLong(l[i]);
        }

        Boolean[] changed = new Boolean[seeds.length];
        Arrays.fill(changed, false);
        while (true) {
            String line = inputReader.readLine();
            if (line == null)
                break;
            if (line.isBlank()) {
                Arrays.fill(changed, false);
                continue;
            }
            if (!Character.isDigit(line.charAt(0)))
                continue;
            else {
                String[] data = line.split(" ");
                long dest = Long.parseLong(data[0]);
                long src = Long.parseLong(data[1]);
                long range = Long.parseLong(data[2]);

                for (int i = 0; i < seeds.length; i++) {
                    if (src + range - seeds[i] > 0 && seeds[i] >= src && !changed[i]) {
                        seeds[i] = dest + seeds[i] - src;
                        changed[i] = true;
                    }
                }
            }

        }

        long min = Long.MAX_VALUE;
        for (long seed : seeds)
            if (seed < min)
                min = seed;

        System.out.println(min);
    }
}

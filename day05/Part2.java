import java.util.ArrayList;
import java.util.Arrays;

class Part2 {
    public static void main(String[] a) {
        InputReader inputReader = new InputReader("./input.txt");

        String[] l = inputReader.readLine().split(" ");
        long[] seeds = new long[l.length - 1];
        for (int i = 1; i < l.length; i++) {
            seeds[i - 1] = Long.parseLong(l[i]);
        }

        ArrayList<ArrayList<long[]>> data = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            data.add(new ArrayList<>());
        }

        Boolean[] changed = new Boolean[seeds.length];
        Arrays.fill(changed, false);

        int curMap = 0;
        inputReader.readLine(); // skip empty line
        while (true) {
            String line = inputReader.readLine();
            if (line == null)
                break;
            if (line.isBlank()) {
                Arrays.fill(changed, false);
                curMap++;
                continue;
            }
            if (!Character.isDigit(line.charAt(0)))
                continue;
            else {
                String[] args = line.split(" ");
                long[] toAdd = new long[3];
                for (int i = 0; i < 3; i++) {
                    toAdd[i] = Long.parseLong(args[i]);
                }
                data.get(curMap).add(toAdd);
            }
        }

        long i = 0;
        loop: while (true) {
            long seed = i;
            for (int j = data.size() - 1; j > -1; j--) {
                for (int k = 0; k < data.get(j).size(); k++) {
                    long dest = data.get(j).get(k)[0];
                    long src = data.get(j).get(k)[1];
                    long range = data.get(j).get(k)[2];
                    if (seed >= dest && seed < (dest + range)) {
                        seed = src + (seed - dest);
                        break;
                    }
                }
            }
            for (int s = 0; s < seeds.length - 1; s += 2) {
                if (seed >= seeds[s] && seed < seeds[s] + seeds[s + 1])
                    break loop;
            }

            i++;
        }

        System.out.println(i);
    }
}

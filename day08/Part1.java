import java.util.HashMap;
import java.util.Map;

class Part1 {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader("./input.txt");

        Map<String, String[]> nodes = new HashMap<>();

        char[] directions = inputReader.readLine().toCharArray();

        while (true) {
            String line = inputReader.readLine();

            if (line == null)
                break;

            if (line.isEmpty())
                continue;

            nodes.putIfAbsent(line.substring(0, 3),
                    new String[] { line.substring(7, 10), line.substring(12, 15) });
        }

        String curNode = "AAA";
        int count = 0;
        while (!curNode.equals("ZZZ")) {
            curNode = nodes.get(curNode)[directions[count % directions.length] == 'L' ? 0 : 1];
            count++;
        }
        System.out.println(count);
    }
}

public class PrintLineByBreak {
    static String[] printLineByBreak(String[] input) {
        if (input == null || input.length == 0) {
            return null; // or throw e
        }

        String s = input[0];
        for (int i = 1; i < input.length; i++) {
            s += input[i];
        }

        return s.split("\n");
    }

    public static void main(String[] args) {
        String[] input = new String[] { "one\n", "two\nthree\n", "four", "five", "six\n", "seven" };
        System.out.println(Arrays.toString(printLineByBreak(input))); // [one, two, three, fourfivesix, seven]
    }
}

import java.util.Scanner;

/**
 * Runs the Farz chatbot application.
 */
public class Farz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String banner = " _____              \n"
                + "|  ___|_ _ _ __ ____\n"
                + "| |_ / _` | '__|_  /\n"
                + "|  _| (_| | |   / / \n"
                + "|_|  \\__,_|_|  /___|\n";

        System.out.println("____________________________________________________________");
        System.out.println(banner);
        System.out.println("Hello! I'm Farz.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (input.equals("bye")) {
                break;
            }
            echoCommand(input);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private static void echoCommand(String input) {
        System.out.println(input);
        System.out.println("____________________________________________________________");
    }
}

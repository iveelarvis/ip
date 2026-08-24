import java.util.Scanner;

/**
 * Runs the Farz chatbot application.
 */
public class Farz {
    private static final String[] TASKS = new String[100];
    private static int taskCount = 0;

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
            handleCommand(input);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private static void handleCommand(String input) {
        if (input.equals("list")) {
            listTasks();
        } else {
            addTask(input);
        }
        System.out.println("____________________________________________________________");
    }

    private static void listTasks() {
        for (int i = 0; i < taskCount; ++i) {
            System.out.println((i + 1) + ". " + TASKS[i]);
        }
    }

    private static void addTask(String input) {
        TASKS[taskCount] = input;
        taskCount++;
        System.out.println("added: " + input);
    }
}

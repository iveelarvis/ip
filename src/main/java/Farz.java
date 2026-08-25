import java.util.Scanner;

/**
 * Runs the Farz chatbot application.
 */
public class Farz {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String BANNER = " _____              \n"
            + "|  ___|_ _ _ __ ____\n"
            + "| |_ / _` | '__|_  /\n"
            + "|  _| (_| | |   / / \n"
            + "|_|  \\__,_|_|  /___|\n";
    private static final Task[] TASKS = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printDivider();
        System.out.println(BANNER);
        System.out.println("Hello! I'm Farz.");
        System.out.println("What can I do for you?");
        printDivider();

        boolean shouldContinue = true;
        while (shouldContinue && scanner.hasNextLine()) {
            String rawInput = scanner.nextLine().trim();
            shouldContinue = executeCommand(rawInput);
        }

        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    /**
     * Executes a command entered by the user and indicates whether to accept more commands.
     *
     * @param input Full user input, including any command arguments.
     * @return {@code false} when the user enters {@code bye}; {@code true} otherwise.
     */
    private static boolean executeCommand(String input) {
        String[] commandParts = input.split("\\s+", 2);
        switch (commandParts[0]) {
        case "bye" -> {
            return false;
        }
        case "list" -> listTasks();
        case "mark" -> updateTaskStatus(commandParts[1], true);
        case "unmark" -> updateTaskStatus(commandParts[1], false);
        default -> addTask(input);
        }
        printDivider();
        return true;
    }

    private static void updateTaskStatus(String taskNumber, boolean isDone) {
        int taskIndex = Integer.parseInt(taskNumber) - 1;
        Task task = TASKS[taskIndex];
        if (isDone) {
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + task);
    }

    private static void listTasks() {
        for (int i = 0; i < taskCount; ++i) {
            System.out.println((i + 1) + ". " + TASKS[i]);
        }
    }

    private static void addTask(String input) {
        TASKS[taskCount] = new Task(input);
        taskCount++;
        System.out.println("added: " + input);
    }

    private static void printDivider() {
        System.out.println(DIVIDER);
    }
}

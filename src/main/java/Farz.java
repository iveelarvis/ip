import java.util.ArrayList;
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
    /** Tasks stored for the current application session. */
    private static final ArrayList<Task> TASKS = new ArrayList<>();

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
            try {
                shouldContinue = executeCommand(rawInput);
            } catch (FarzException exception) {
                System.out.println("Oops! " + exception.getMessage());
                printDivider();
            }
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
    private static boolean executeCommand(String input) throws FarzException {
        if (input.isEmpty()) {
            throw new FarzException("Please enter a command.");
        }

        String[] commandParts = input.split("\\s+", 2);
        String command = commandParts[0];
        String arguments = commandParts.length == 2 ? commandParts[1].trim() : "";
        switch (command) {
        case "bye" -> {
            return false;
        }
        case "list" -> listTasks();
        case "mark" -> updateTaskStatus(arguments, true);
        case "unmark" -> updateTaskStatus(arguments, false);
        case "todo" -> addTodo(arguments);
        case "deadline" -> addDeadline(arguments);
        case "event" -> addEvent(arguments);
        default -> throw new FarzException("I don't recognise the command '" + command + "'.");
        }
        printDivider();
        return true;
    }

    private static void updateTaskStatus(String taskNumber, boolean isDone) throws FarzException {
        if (taskNumber.isEmpty()) {
            throw new FarzException("Please specify a task number to "
                    + (isDone ? "mark" : "unmark") + ".");
        }

        final int taskIndex;
        try {
            taskIndex = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException exception) {
            throw new FarzException("The task number must be a whole number.");
        }
        if (taskIndex < 0 || taskIndex >= TASKS.size()) {
            throw new FarzException("Task " + taskNumber + " is not in your list.");
        }
        Task task = TASKS.get(taskIndex);
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
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < TASKS.size(); ++i) {
            System.out.println((i + 1) + "." + TASKS.get(i));
        }
    }

    private static void addTodo(String description) throws FarzException {
        requireDescription(description, "todo");
        storeTask(new Todo(description));
    }

    private static void addDeadline(String arguments) throws FarzException {
        String[] parts = arguments.split("\\s+/by\\s+", 2);
        if (parts.length < 2 || parts[0].isBlank() || parts[1].isBlank()) {
            throw new FarzException("Use: deadline DESCRIPTION /by DATE_OR_TIME.");
        }
        storeTask(new Deadline(parts[0], parts[1]));
    }

    private static void addEvent(String arguments) throws FarzException {
        String[] descriptionAndTimes = arguments.split("\\s+/from\\s+", 2);
        if (descriptionAndTimes.length < 2 || descriptionAndTimes[0].isBlank()) {
            throw new FarzException("Use: event DESCRIPTION /from START /to END.");
        }
        String[] times = descriptionAndTimes[1].split("\\s+/to\\s+", 2);
        if (times.length < 2 || times[0].isBlank() || times[1].isBlank()) {
            throw new FarzException("Use: event DESCRIPTION /from START /to END.");
        }
        storeTask(new Event(descriptionAndTimes[0], times[0], times[1]));
    }

    /**
     * Ensures that a task command includes a non-empty description.
     *
     * @param description Task description supplied by the user.
     * @param taskType Type of task being created, used in the error message.
     * @throws FarzException If the description is empty.
     */
    private static void requireDescription(String description, String taskType) throws FarzException {
        if (description.isBlank()) {
            throw new FarzException("The description of a " + taskType + " cannot be empty.");
        }
    }

    /**
     * Stores and acknowledges a newly parsed task.
     *
     * @param task Task to add to the in-memory list.
     */
    private static void storeTask(Task task) {
        TASKS.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + TASKS.size() + " tasks in the list.");
    }

    private static void printDivider() {
        System.out.println(DIVIDER);
    }
}

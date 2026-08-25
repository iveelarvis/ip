/**
 * Represents a task that can be marked as done or not done.
 */
public abstract class Task {
    protected final String description;
    private boolean isDone;

    /**
     * Creates an incomplete task with the given description.
     *
     * @param description Description of the task.
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon indicating whether this task is done.
     *
     * @return {@code "X"} if done, or a space otherwise.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

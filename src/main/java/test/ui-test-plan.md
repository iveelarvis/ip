# Console UI Test Plan

This file is the source of truth for scripted console UI tests run with the `test-ui` skill.

## Test configuration

- Java version: 25
- Compile command: `javac -d /tmp/farz-ui-classes Farz.java FarzException.java Task.java Todo.java Deadline.java Event.java`
- Launch command: `java -cp /tmp/farz-ui-classes Farz`
- Timeout: 10 seconds per test case
- Comparison: Exact standard-output comparison after normalizing CRLF line endings to LF. Whitespace and blank lines are significant.

## Test cases

### TC-1: Add, list, and update all task types

**Aim:** Verify that todos, deadlines with free-form dates, and events are stored, displayed, and updated correctly.

**Input:**

```text
todo borrow book
deadline do homework /by no idea :-p
event project meeting /from Mon 2pm /to 4pm
list
mark 1
unmark 1
bye
```

**Expected output:**

```text
____________________________________________________________
 _____              
|  ___|_ _ _ __ ____
| |_ / _` | '__|_  /
|  _| (_| | |   / / 
|_|  \__,_|_|  /___|

Hello! I'm Farz.
What can I do for you?
____________________________________________________________
Got it. I've added this task:
  [T][ ] borrow book
Now you have 1 tasks in the list.
____________________________________________________________
Got it. I've added this task:
  [D][ ] do homework (by: no idea :-p)
Now you have 2 tasks in the list.
____________________________________________________________
Got it. I've added this task:
  [E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] borrow book
2.[D][ ] do homework (by: no idea :-p)
3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] borrow book
____________________________________________________________
OK, I've marked this task as not done yet:
  [T][ ] borrow book
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### TC-2: Recover from invalid commands

**Aim:** Verify that empty descriptions, unknown commands, malformed task details, invalid task numbers, and blank input produce specific errors without ending the session.

**Input:**

```text
todo
blah
deadline homework
event meeting /from noon
mark
mark abc
mark 1

bye
```

**Expected output:**

```text
____________________________________________________________
 _____              
|  ___|_ _ _ __ ____
| |_ / _` | '__|_  /
|  _| (_| | |   / / 
|_|  \__,_|_|  /___|

Hello! I'm Farz.
What can I do for you?
____________________________________________________________
Oops! The description of a todo cannot be empty.
____________________________________________________________
Oops! I don't recognise the command 'blah'.
____________________________________________________________
Oops! Use: deadline DESCRIPTION /by DATE_OR_TIME.
____________________________________________________________
Oops! Use: event DESCRIPTION /from START /to END.
____________________________________________________________
Oops! Please specify a task number to mark.
____________________________________________________________
Oops! The task number must be a whole number.
____________________________________________________________
Oops! Task 1 is not in your list.
____________________________________________________________
Oops! Please enter a command.
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

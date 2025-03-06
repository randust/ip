# NekoBot

## Features
- **Add tasks:** Create To-Do, Deadline, and Event tasks.
- **List tasks:** View all tasks currently stored.
- **Mark tasks:** Mark a task as completed.
- **Unmark tasks:** Unmark a task to indicate it is not yet completed.
- **Delete tasks:** Remove a task from the list.
- **Find tasks:** Search for tasks by keyword.
- **Save and load tasks:** Automatically save tasks to a file (`neko.txt`) and load them upon startup.

## Getting Started
### Prerequisites
- Java Development Kit (JDK) 11 or later installed on your system.
- A terminal or command prompt to run the chatbot.

### Commands
Below is a list of commands supported by NekoBot:

| Command           | Description | Example |
|------------------|-------------|---------|
| `todo <task>`    | Adds a To-Do task | `todo Buy groceries` |
| `deadline <task> /by <date>` | Adds a deadline task | `deadline Submit report /by 2025-03-10` |
| `event <task> /from <start_time> /to <end_time>` | Adds an event task | `event Team meeting /from 10:00AM /to 12:00PM` |
| `list`           | Lists all tasks | `list` |
| `mark <task_number>` | Marks a task as done | `mark 2` |
| `unmark <task_number>` | Unmarks a task | `unmark 2` |
| `delete <task_number>` | Deletes a task | `delete 3` |
| `find <keyword>` | Finds tasks containing the keyword | `find groceries` |
| `bye`            | Exits the chatbot | `bye` |

### Saving and Loading Data
- Tasks are automatically saved to a file (`neko.txt`) after every command.
- When the chatbot starts, it loads existing tasks from `neko.txt`.


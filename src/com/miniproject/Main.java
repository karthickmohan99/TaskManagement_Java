package com.miniproject;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.miniproject.dao.TaskManager;
import com.miniproject.pojo.Priority;
import com.miniproject.pojo.Status;
import com.miniproject.pojo.Task;

public class Main {

	public static void main(String[] args) {

		TaskManager taskManager = new TaskManager();

		Task task = new Task();

		Scanner scanner = new Scanner(System.in);
		int option = 0;

		do {
			menu();
			option = scanner.nextInt();

			switch (option) {

			case 1:
				if (task != null) {
					task = inputTask(scanner, task);
					System.out.println(task + "Task From User");
					taskManager.addTask(task);
				}

				break;
			case 2:
				System.out.print("Enter Task id number to be edit ");
				long id = scanner.nextLong();
				scanner.nextLine();
				taskManager.editTask(id, scanner);
				break;
			case 3:
				System.out.println("Enter task id to delete");
				long deleteId = scanner.nextLong();
				taskManager.deleteTask(deleteId);
				// System.out.println( taskManager.getTaskById(deleteId)+"from console");
				break;
			case 4:
				taskManager.viewAllTasks();
				break;
			case 5:
				scanner.nextLine();
				System.out.println("Enter Priority to filter task (High/Medium/Low)");

				try {
					String userEnteredPriority = scanner.nextLine().toUpperCase();
					Priority priority = Priority.valueOf(userEnteredPriority);
					taskManager.filterTasksByPriority(priority);
				} catch (IllegalArgumentException e) {
					System.out.println("Enter a valid Priority (High/Medium/Low)");
					scanner.nextLine(); // Clear the invalid input before retrying
				}

				break;
			case 6:
				System.out.println("\nThank you for using the Task Manager. Goodbye!\n");
				break;
			default:
				System.out.println("\nInvalid input\n");
				break;
			}

		} while (option != 6);

	}

	public static void menu() {
		System.out.println("\n1. Add task \n" + "2. Edit Task\n" + "3. Delete Task\n" + "4. View All Tasks\n"
				+ "5. Filter Tasks by Priority" + "6. Exit");
	}

	public static Task inputTask(Scanner input, Task task) {

		while (true) {

			try {

				System.out.println("Enter the task id");
				long idNum = input.nextLong();
				input.nextLine();

				System.out.println("Enter the task Tittle");
				String tittle = input.nextLine();

				System.out.println("Enter the task Description");
				String description = input.nextLine();

				System.out.println("Enter the task Priority(HIGH, MEDIUM, LOW)");
				String userEnteredPriority = input.nextLine().toUpperCase();

				Priority priority = Priority.valueOf(userEnteredPriority);

				System.out.println("Enter the task Status(PENDING, INPROGRESS, COMPLETED)");
				String userEnteredStatus = input.nextLine().toUpperCase();

				Status status = Status.valueOf(userEnteredStatus);

				task = new Task(idNum, tittle, description, priority, status);
				return task;

			} catch (IllegalArgumentException e) {
				System.out.println("Enter the valid Priority or Status");
				input.nextLine(); // Clear the invalid input before retrying
			} catch (InputMismatchException ex) {
				System.out.println("Invalid Input");
				input.nextLine(); // Clear the invalid input before retrying
			}
		}

	}

}

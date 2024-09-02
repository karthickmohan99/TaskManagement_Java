package com.miniproject.dao;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

import com.miniproject.pojo.Priority;
import com.miniproject.pojo.Status;
import com.miniproject.pojo.Task;

public class TaskManager implements TaskManagerDao {

	LinkedList<Task> task_list;

	public TaskManager() {

		task_list = new LinkedList<>();
	}

	@Override
	public void addTask(Task task) {
		if (!find(task.getId())) {
			task_list.add(task);
			System.out.println("Task Added Successfully");

		} else {
			System.out.println("Task already exists in the  list");

		}

	}

	@Override
	public void editTask(long id, Scanner input) {

		if (find(id)) {

			Task task = getTaskById(id);

			try {

				System.out.println("Enter the task Tittle to Edit");
				String tittle = input.nextLine();

				System.out.println("Enter the task Description to Edit");
				String description = input.nextLine();

				System.out.println("Enter the task Priority(HIGH, MEDIUM, LOW) to Edit");
				String userEnteredPriority = input.nextLine().toUpperCase();

				Priority priority = Priority.valueOf(userEnteredPriority);

				System.out.println("Enter the task Status(PENDING, INPROGRESS, COMPLETED) to Edit");
				String userEnteredStatus = input.nextLine().toUpperCase();

				Status status = Status.valueOf(userEnteredStatus);

				task.setTitle(tittle);
				task.setDescription(description);
				task.setPriority(priority);
				task.setStatus(status);
				System.out.println(task.toString() + "\nTask Edited Successfully");
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid priority or status entered. Please try again.");

			} catch (InputMismatchException ex) {
				System.out.println("Invalid input. Please enter in correct format");

			}

		} else {
			System.out.println("Task Not Found in the Task list");
		}

	}

	@Override
	public void deleteTask(long id) {
		Task deletedTask = null;

		for (Task t : task_list) {
			if (t.getId() == id) {
				System.out.println("task to be deleted" + " " + t);
				deletedTask = t;
			}
		}
		if (deletedTask == null) {
			System.out.println("Invalid task Id");
		} else {
			task_list.remove(deletedTask);

			System.out.println("Successfully removed record from the list");
		}

	}

	@Override
	public Task getTaskById(long id) {
//		System.out.println("Size of task_list: " + task_list.size());
		for (Task task : task_list) {
//			System.out.println(task.getId());
			if (task.getId() == id) {
				System.out.println(task);
				return task;
			}
		}
		System.out.println("Task not found with ID: " + id);
		return null;

	}

	public boolean find(long id) {
		for (Task task : task_list) {
			if (task.getId() == id) {
				// System.out.println(task + "tasskk from find");
				return true;
			}
		}
		return false;
	}

	@Override
	public void viewAllTasks() {
		if (task_list.isEmpty()) {
			System.out.println("The list has no Tasks\n");
		}

		for (Task tsk : task_list) {
			System.out.println(tsk.toString());
		}

	}

	@Override
	public void filterTasksByPriority(Priority priority) {
		System.out.println("Results From filter");
		LinkedList<Task> tasksByPriority = new LinkedList<>();

		for (Task task : task_list) {
			if (task.getPriority().equals(priority)) {
				tasksByPriority.add(task);
			}
		}
		if (!tasksByPriority.isEmpty()) {
			for (Task ts : tasksByPriority) {
				System.out.println("TaskId:" + ts.getId() + ", Title:" + ts.getTitle() + ", Description:"
						+ ts.getDescription() + ", Priority:" + ts.getPriority() + ", Status" + ts.getStatus());
			}
		} else {
			System.out.println("There is no task with " + priority + " found");
		}

	}

}

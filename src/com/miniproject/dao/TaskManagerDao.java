package com.miniproject.dao;

import java.util.LinkedList;
import java.util.Scanner;

import com.miniproject.pojo.Priority;
import com.miniproject.pojo.Task;

public interface TaskManagerDao {
   
	public void addTask(Task task);
	public void editTask(long id,Scanner input);
	public void deleteTask(long id);
	public Task getTaskById(long id);
	public void viewAllTasks();
	public void filterTasksByPriority(Priority priority);
	//public boolean find(long id);
}

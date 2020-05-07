package br.ce.wcaquino.taskbackend.controller;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import junit.framework.Assert;

public class TaskControllerTest{
	@Mock
	private TaskRepo taskRepo;
	
	@InjectMocks
	private TaskController controller;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
		
	@Test
	public void naoDeveSalvarTasSemDesc(){
		Task todo = new Task();
		//todo.setTask("Descricao");
		todo.setDueDate(LocalDate.now());
		try {
			controller.save(todo);
			org.junit.Assert.fail("N deveria chegar aqui");
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			org.junit.Assert.assertEquals("Fill the task description", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTasSemData(){
		Task todo = new Task();
		todo.setTask("Descricao");
		//todo.setDueDate(LocalDate.now());
		try {
			controller.save(todo);
			org.junit.Assert.fail("N deveria chegar aqui");
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			org.junit.Assert.assertEquals("Fill the due date", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTasComDataPassada(){
		Task todo = new Task();
		todo.setTask("Descricao");
		todo.setDueDate(LocalDate.of(2010,01,01));
		try {
			controller.save(todo);
			org.junit.Assert.fail("N deveria chegar aqui");
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			org.junit.Assert.assertEquals("Due date must not be in past", e.getMessage());
		}
	}
	
	@Test
	public void deveSalvarTasComSucesso() throws ValidationException{
		Task todo = new Task();
		todo.setTask("Descricao");
		todo.setDueDate(LocalDate.now());
		controller.save(todo);
		Mockito.verify(taskRepo).save(todo);
	}
}

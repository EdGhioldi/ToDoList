package com.portfolio.rest.controller;

import com.portfolio.rest.model.Task;
import com.portfolio.rest.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping(value = "/")
    public String holaMundo(){
        return "Hola Mundo!!!";
    }

    @GetMapping(value = "/tasks")
    public List<Task> getTask(){
        return todoRepository.findAll();
    }

    @PostMapping(value = "/savetask")
    public String saveTasks(@RequestBody Task task){
        todoRepository.save(task);
        return "Saved Task";
    }

    @PutMapping(value="/update/{id}")
    public String updateTaks(@PathVariable long id, @RequestBody Task task){
        Task updatedTask = todoRepository.findById(id).get();
        updatedTask.setTitle(task.getTitle());
        updatedTask.setDescription(task.getDescription());
        todoRepository.save(updatedTask);
        return "Updated Task";
    }

    @DeleteMapping(value ="/delete/{id}")
    public String deleteTaks(@PathVariable long id) {
        Task deleteTask = todoRepository.findById(id).get();
        todoRepository.delete(deleteTask);
        return "Deleted Task";
    }


}

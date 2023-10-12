package br.com.guzz.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/tasks", produces = {"application/json"})
@Tag(name = "Task")
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @PostMapping(value = "/", consumes = "application/json")
    @Operation(summary = "Realizada a criação de uma tarefa", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso!"),
        @ApiResponse(responseCode = "404", description = "Erro ao criar tarefa"),
        @ApiResponse(responseCode = "500", description = "Erro interno do serviço!")
    })
    public ResponseEntity<?> create(@RequestBody TaskModel taskModel){
        var task = this.taskService.createTask(taskModel);
        return task;
    }
}

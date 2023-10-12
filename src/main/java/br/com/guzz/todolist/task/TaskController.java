package br.com.guzz.todolist.task;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

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
        @ApiResponse(responseCode = "404", description = "(A data de início / data de término deve ser maior do que a data atual!) ou (A data de início deve ser menor do que a data de término!)"),
        @ApiResponse(responseCode = "500", description = "Erro interno do serviço!")
    })
    public ResponseEntity<?> create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        return this.taskService.createTask(taskModel, request);
         
    }

    @GetMapping(value = "/")
    @Operation(summary = "Busca todas as tarefas do usuário", method = "GET")
     @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Busca realizada com sucesso!"),
        @ApiResponse(responseCode = "500", description = "Erro interno do serviço!")
    })
    public List<TaskModel> findByIdUser(HttpServletRequest request){
        return this.taskService.listByIdUser(request);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Faz Update de uma tarefa", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Update realizado com sucesso!"),
        @ApiResponse(responseCode = "500", description = "Erro interno do serviço!")
    })
    public TaskModel update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id){
        return this.taskService.updateTask(taskModel, request, id);
    }
}

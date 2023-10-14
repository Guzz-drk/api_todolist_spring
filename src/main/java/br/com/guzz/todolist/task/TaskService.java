package br.com.guzz.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.guzz.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    public ResponseEntity<?> createTask(TaskModel taskModel, HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID)idUser);
        
        var valid = validaTempoTask(taskModel);

        if(valid != null){
            return valid;
        }

        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(201).body(task);
    }

    public List<TaskModel> listByIdUser(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        return this.taskRepository.findByIdUser((UUID)idUser);
    }

    public ResponseEntity<?> updateTask(TaskModel taskModel, HttpServletRequest request, UUID id){
        var task = this.taskRepository.findById(id).orElse(null);

        var valid = validaPermissaoTask(task, request);

        if(valid != null){
            return valid;
        }

        Utils.copyNonNullProperties(taskModel, task);

        var taskUpdated =  this.taskRepository.save(task);
        return ResponseEntity.ok().body(taskUpdated);
    }

    public ResponseEntity<?> validaPermissaoTask(TaskModel task, HttpServletRequest request){
        if(task == null){
            return ResponseEntity.status(404).body("Tarefa não encontrada!");
        }

        var idUser = request.getAttribute("idUser");

        if(!task.getIdUser().equals(idUser)){
            return ResponseEntity.status(404).body("Usuário não tem permissão para alterar essa tarefa");
        }
        return null;
    }

    public ResponseEntity<?> validaTempoTask(TaskModel taskModel){
        var currentDate = LocalDateTime.now();

        if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(404).body("A data de início / data de término deve ser maior do que a data atual!");
        }

        if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(404).body("A data de início deve ser menor do que a data de término!");
        }

        return null;
    }
}

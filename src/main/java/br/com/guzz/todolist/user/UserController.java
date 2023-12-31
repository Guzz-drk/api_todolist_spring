package br.com.guzz.todolist.user;

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
@RequestMapping(value = "/users", produces = {"application/json"})
@Tag(name = "User")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping(value = "/", consumes = "application/json")
    @Operation(summary = "Realizada a criação de um usuário", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso!"),
        @ApiResponse(responseCode = "404", description = "Erro ao criar usuário, username já existe!"),
        @ApiResponse(responseCode = "500", description = "Erro interno do serviço!")
    })
    public ResponseEntity<?> create(@RequestBody UserModel userModel){
        return userService.createUser(userModel);
    }
}

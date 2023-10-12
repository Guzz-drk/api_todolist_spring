package br.com.guzz.todolist.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    
    private String username;
    private String name;
    private String password; 
}

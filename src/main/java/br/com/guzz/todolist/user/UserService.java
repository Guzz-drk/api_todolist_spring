package br.com.guzz.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> createUser(UserModel userModel){
        var user = this.userRepository.findByUsername(userModel.getUsername());
        if(user != null){
            return ResponseEntity.status(404).body("Usuário já existe!");
        }
        var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        userModel.setPassword(passwordHashred);
        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(201).body(userCreated);
    }
}

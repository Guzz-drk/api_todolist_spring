package br.com.guzz.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "tb_users")
@Table(schema = "guzz", name = "tb_users")
public class UserModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    private String username;
    private String name;
    private String password; 

    @CreationTimestamp
    private LocalDateTime createdAt;
}

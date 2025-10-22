/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs241_assignment2.knowledge_repository.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String password;
    private String email;
    private boolean enabled;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles = new HashSet<>();
    
    public Long getId() { return id; }
    
    public void setId(Long id) { this.id = id;} 

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
    
    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public Set<UserRole> getUserRoles() { return userRoles; }
    
    public void addRole(Role role) { 
        boolean exists = userRoles.stream().anyMatch(ur -> ur.getRole().equals(role));
        
        if(exists) { return; }
        
        UserRole userRole = new UserRole(this, role);
        userRole.setId(new UserRoleId(this.getId(), role.getId()));
        
        this.userRoles.add(userRole);
        role.getUserRoles().add(userRole);
    }
    
    public void removeRole(Role role) {
        userRoles.removeIf(ur -> ur.getRole().equals(role));
        role.getUserRoles().removeIf(ur -> ur.getUser().equals(this));
    }
    
    public Set<Role> getRoles() {
        return userRoles.stream().map(UserRole::getRole).collect(Collectors.toSet());
    }
}

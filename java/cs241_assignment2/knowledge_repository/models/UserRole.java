/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs241_assignment2.knowledge_repository.models;

import jakarta.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {
    
    @EmbeddedId
    private UserRoleId id = new UserRoleId();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;
    
    public UserRole() {}
    
    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public UserRoleId getId() { return id; }

    public void setId(UserRoleId id) { this.id = id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }
}

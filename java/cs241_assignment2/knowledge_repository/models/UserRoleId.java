/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs241_assignment2.knowledge_repository.models;
 
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserRoleId implements Serializable {
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "role_id")
    private Long roleId;

    public UserRoleId() { } 
    
    public UserRoleId(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public Long getRoleId() { return roleId; }

    public void setRoleId(Long roleId) { this.roleId = roleId; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if(!(o instanceof UserRoleId)) { return false; }
        
        UserRoleId that = (UserRoleId) o;
        
        return Objects.equals(userId, that.userId) && Objects.equals(roleId, that.roleId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}

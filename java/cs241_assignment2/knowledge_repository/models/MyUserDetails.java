/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs241_assignment2.knowledge_repository.models;

import cs241_assignment2.knowledge_repository.models.Role;
import cs241_assignment2.knowledge_repository.models.UserRole;
import cs241_assignment2.knowledge_repository.models.User;
import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {
    private final User user;
    
    public MyUserDetails(User user) {
        this.user = user;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<UserRole> userRoles = user.getUserRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        
        for(UserRole userRole : userRoles) {
            Role role = userRole.getRole();
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        
        return authorities;
    }
    
    @Override
    public String getPassword() { return user.getPassword(); }
    
    @Override
    public String getUsername() { return user.getUsername(); }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return user.isEnabled(); }
    
}

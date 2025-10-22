/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs241_assignment2.knowledge_repository.services;

import cs241_assignment2.knowledge_repository.repositories.UserRepository;
import cs241_assignment2.knowledge_repository.repositories.RoleRepository;
import cs241_assignment2.knowledge_repository.models.Role;
import cs241_assignment2.knowledge_repository.models.User;
import java.util.*;

import javax.management.relation.RoleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }
    
    public User findUserbyUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
    
    public User findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Id not found: " + id));
    }
    
    public void updateUserRole(String username, String roleName) 
            throws UsernameNotFoundException, RoleNotFoundException {
        User user = findUserbyUsername(username);
        
        Role role = roleRepository.findByName(roleName);
        if (role == null) { throw new RoleNotFoundException("Role not found" + role); }
        
        user.addRole(role);
        userRepository.save(user);
    }
    
    public void registerNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        
        Role userRole = roleRepository.findByName("USER");
        user.addRole(userRole);
        userRepository.save(user);
    }
    
    public User save(User user) {
        return userRepository.save(user);
    }
    
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

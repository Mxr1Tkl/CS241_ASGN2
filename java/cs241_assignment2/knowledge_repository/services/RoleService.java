/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs241_assignment2.knowledge_repository.services;

import cs241_assignment2.knowledge_repository.repositories.RoleRepository;
import cs241_assignment2.knowledge_repository.models.Role;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository;
    
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
    
    public Role save(Role role) {
        return roleRepository.save(role);
    }
    
    public void delete(int id) {
        roleRepository.deleteById(id);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs241_assignment2.knowledge_repository.services;

import cs241_assignment2.knowledge_repository.repositories.UserRoleRepository;
import cs241_assignment2.knowledge_repository.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoleService {
    
    @Autowired
    private UserRoleRepository userRoleRepository;
    
    public UserRole save(UserRole userRole) { return userRoleRepository.save(userRole); }
    
    @Transactional
    public void deleteRolesByUserId(Long userId) {
        userRoleRepository.deleteByUserId(userId);
    }
}

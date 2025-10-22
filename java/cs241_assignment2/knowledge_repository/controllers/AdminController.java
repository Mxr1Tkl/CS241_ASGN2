/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs241_assignment2.knowledge_repository.controllers;

import cs241_assignment2.knowledge_repository.models.Role;
import cs241_assignment2.knowledge_repository.services.RoleService;
import cs241_assignment2.knowledge_repository.models.User;
import cs241_assignment2.knowledge_repository.services.UserRoleService;
import cs241_assignment2.knowledge_repository.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserRoleService userRoleService;
    
    @GetMapping("/dashboard")
    public String viewDashboard() {
        return "admin/dashboard";
    }
    
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.listAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        
        return "admin/users";
    }
    
    @PostMapping("/users/{username}/role")
    public String updateUserRole(@PathVariable("username") String username, 
                                @RequestParam("roleName") String roleName) {
        User user;
        try {
           user = userService.findUserbyUsername(username);
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        
        Role role = roleService.getRoleByName(roleName);
        if (role == null) { throw new RuntimeException("Role not found: " + roleName); }
        
        boolean hasRole = user.getRoles().contains(role);
        
        if(!hasRole) {
            user.addRole(role);
            userService.save(user);
        }
        
        return "redirect:/admin/users";
    }
    
    @PostMapping("/users/{userId}/delete")
    public String deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUser(id);
        
        return "redirect:/admin/users";
    }
}

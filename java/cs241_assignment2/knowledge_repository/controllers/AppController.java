/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs241_assignment2.knowledge_repository.controllers;

import cs241_assignment2.knowledge_repository.models.User;
import cs241_assignment2.knowledge_repository.repositories.UserRepository;
import cs241_assignment2.knowledge_repository.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserService userService;
    
    @GetMapping("/")
    public String viewHomePage() {
        return "homepage";
    }
    
    @GetMapping("/home")
    public String viewClientHomePage() {
        return "User dashboard";
    }
    
    @GetMapping("/archive")
    public String viewArchive() {
        return "Archives";
    }
    
    @GetMapping("/login")
    public String viewLogin() {
        return "login";
    }
    
    @GetMapping("/forgot-password")
    public String viewForgotPassword() {
        return "forgot-password";
    }
    
    @GetMapping("/register")
    public String viewRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "redirect:/register?error=username";
        }
        userService.registerNewUser(user);
        
        return "redirect:/login?registered";
    }
    
    @GetMapping("/Access_Denied")
    public String accessDenied() {
        return "Acess Denied";
    }
    
    @GetMapping("/search_result")
    public String viewSearchResultPage() {
        return "search_result";
    }
    
    @PostMapping("/search_result")
    public String viewSearchResults() {
        return "search_result";
    }
    
    @GetMapping("/profile")
    public String viewProfilePage() {
        return "profile";
    }
    
    @GetMapping("/resources")
    public String viewResourcesPage() {
        return "resources";
    }
    
    @GetMapping("/resource/environment")
    public String viewEnvironemntPage() {
        return "resource/Environment";
    }
    
    @GetMapping("/resource/environment/fishing_content")
    public String viewFishingContentPage() {
        return "resource/environment/FishingContent";
    }
    
    @GetMapping("/resource/environment/hunting_content")
    public String viewHuntingContentPage() {
        return "resource/environment/HuntingContent";
    }
    
    @GetMapping("/resource/healing")
    public String viewHealingPage() {
        return "resource/Healing";
    }
    
    @GetMapping("/resource/healing/bone_alignment")
    public String viewBoneAlignmentPage() {
        return "resource/healing/Medicine_Bone_Alignment";
    }
    
    @GetMapping("/resource/healing/healing_water_of_dawasamu")
    public String viewHealingWaterPage() {
        return "resource/healing/Medicine_healing_water";
    }
    
    @GetMapping("/resource/healing/medicinal_plants")
    public String viewMedicinalPlantsPage() {
        return "resource/healing/herbal_fijian";
    }
    
    @GetMapping("/resource/healing/gau_islands'_medicinal_plants")
    public String viewGauIslandsPage() {
        return "resource/healing/herbal_Gau";
    }
    
    @GetMapping("/resource/history")
    public String viewHistoryPage() {
        return "resource/History";
    }
    
    @GetMapping("/resource/history/culture")
    public String viewCulturePage() {
        return "resource/history/history1";
    }
    
    @GetMapping("/resource/history/indenture")
    public String viewIndenturePage() {
        return "resource/history/history2";
    }
}
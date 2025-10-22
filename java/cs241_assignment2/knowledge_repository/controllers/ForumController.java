/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs241_assignment2.knowledge_repository.controllers;

import cs241_assignment2.knowledge_repository.models.ForumThread;
import cs241_assignment2.knowledge_repository.models.Post;
import cs241_assignment2.knowledge_repository.services.PostService;
import cs241_assignment2.knowledge_repository.services.ThreadService;
import cs241_assignment2.knowledge_repository.models.User;
import cs241_assignment2.knowledge_repository.models.UserRole;
import cs241_assignment2.knowledge_repository.services.UserService;
import java.security.Principal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/forum")
public class ForumController {

    private final ThreadService threadService;
    private final PostService postService;
    
    @Autowired
    UserService userService;

    public ForumController(ThreadService threadService, PostService postService) {
        this.threadService = threadService;
        this.postService = postService;
    }

    @GetMapping
    public String forumPage(Model model) {
        model.addAttribute("threads", threadService.listAll());
        return "forum"; // list of threads
    }

    @GetMapping("/{thread_id}")
    public String viewThread(@PathVariable("thread_id") Long id, Model model) {
        ForumThread thread = threadService.get(id);
        model.addAttribute("thread", thread);
        model.addAttribute("posts", thread.getPosts());
        model.addAttribute("newPost", new Post());
        return "thread"; // thread details + posts
    }

    @GetMapping("/new_thread")
    public String newThreadForm(Model model, Principal principal) {
        User user;
        try {
           user = userService.findUserbyUsername(principal.getName());
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        
        ForumThread thread = new ForumThread();
        thread.setAuthor(user);
        model.addAttribute("thread", thread);
        return "new_thread";
    }

    @PostMapping("/new_thread")
    public String createThread(@ModelAttribute ForumThread thread, Principal principal) {
        User user;
        try {
           user = userService.findUserbyUsername(principal.getName());
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        
        thread.setAuthor(user);
        threadService.save(thread);
        return "redirect:/forum";
    }

    @PostMapping("/{thread_id}/reply")
    public String replyToThread(@PathVariable("thread_id") Long id, @ModelAttribute Post post, Principal principal) {
        ForumThread thread = threadService.get(id);
        post.setAuthor(principal.getName());
        post.setThread(thread);
        postService.savePost(post);
        return "redirect:/forum/" + id;
    }
    
    @PostMapping("/{thread_id}/delete")
    public String deleteThread(@PathVariable("thread_id") Long id, Principal principal) {
        User currentUser;
        try {
           currentUser = userService.findUserbyUsername(principal.getName());
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        
        ForumThread thread = threadService.get(id);
        
        boolean isAdmin = currentUser.getUserRoles().stream()
                .map(UserRole::getRole)
                .anyMatch(role -> role.getName().equals("ADMIN"));
        
        if(!thread.getAuthor().getId().equals(currentUser.getId()) && !isAdmin) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot delete this thread!");
        }
        
        threadService.delete(id);
        return "redirect:/forum";
    }
}
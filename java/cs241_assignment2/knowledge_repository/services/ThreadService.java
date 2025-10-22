/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs241_assignment2.knowledge_repository.services;

import cs241_assignment2.knowledge_repository.repositories.ThreadRepository;
import cs241_assignment2.knowledge_repository.models.ForumThread;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service
@Transactional
public class ThreadService {
    
    @Autowired
    private ThreadRepository threadRepo;

    public List<ForumThread> listAll() {
        return threadRepo.findAll();
    }

    public ForumThread get(long id) {
        return threadRepo.findById(id).get();
    }

    public ForumThread save(ForumThread thread) {
        return threadRepo.save(thread);
    }
    
    public void delete(long id) {
        threadRepo.deleteById(id);
    }
}
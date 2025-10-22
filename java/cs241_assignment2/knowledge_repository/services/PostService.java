/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs241_assignment2.knowledge_repository.services;

import cs241_assignment2.knowledge_repository.repositories.PostRepository;
import cs241_assignment2.knowledge_repository.models.ForumThread;
import cs241_assignment2.knowledge_repository.models.Post;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepo;

    public PostService(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    public List<Post> getPostsByThread(ForumThread thread) {
        return thread.getPosts();
    }

    public Post savePost(Post post) {
        return postRepo.save(post);
    }
    
    public void deletePost(long id) {
        postRepo.deleteById(id);
    }
}
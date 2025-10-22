/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs241_assignment2.knowledge_repository.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
 
@Entity
@Table(name = "forum_thread")
public class ForumThread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long thread_id;
    
    private String title;

    @Column(length = 2000)
    private String body;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;
    
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL)
    private List<Post> posts;

    public Long getThread_id() {
        return thread_id;
    }

    public void setThread_id(Long thread_id) {
        this.thread_id = thread_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}

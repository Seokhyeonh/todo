package io.thesun4sky.todoapp.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String content;

    private String userName;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @Builder
    public Comment(String content, String userName ,Todo todo) {
        this.content = content;
        this.userName = userName;
        this.createdAt = LocalDateTime.now();
        this.todo = todo;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

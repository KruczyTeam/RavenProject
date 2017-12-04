package com.kruczyteam.raven.Task.model;

import com.kruczyteam.raven.ProgressState;
import com.kruczyteam.raven.UserStory.model.UserStory;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Column(name = "description")
    private String description;

    @NotEmpty
    @Column(name = "author")
    private String author;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "current")
    private String current;

    @Column(name = "progress_state")
    private ProgressState progressState;

    @ManyToOne
    @JoinColumn(name = "user_story_id")
    private UserStory userStory;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public LocalDateTime getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate)
    {
        this.creationDate = creationDate;
    }

    public String getCurrent()
    {
        return current;
    }

    public void setCurrent(String current)
    {
        this.current = current;
    }

    public ProgressState getProgressState()
    {
        return progressState;
    }

    public void setProgressState(ProgressState progressState)
    {
        this.progressState = progressState;
    }

    public UserStory getUserStory()
    {
        return userStory;
    }

    public void setUserStory(UserStory userStory)
    {
        this.userStory = userStory;
    }
}

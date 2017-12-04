package com.kruczyteam.raven.UserStory.model;

import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.ProgressState;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "user_stories")
public class UserStory
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Column(name = "description")
    private String description;

    @Column(name = "progress_state")
    private ProgressState progressState;

    @ManyToOne
    @JoinColumn(name = "backlog_id")
    private Backlog backlog;

    public UserStory()
    {
    }

    public UserStory(String description, Backlog backlog)
    {
        this.description = description;
        this.backlog = backlog;
    }

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

    public ProgressState getProgressState()
    {
        return progressState;
    }

    public void setProgressState(ProgressState progressState)
    {
        this.progressState = progressState;
    }

    public Backlog getBacklog()
    {
        return backlog;
    }

    public void setBacklog(Backlog backlog)
    {
        this.backlog = backlog;
    }
}

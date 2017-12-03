package com.kruczyteam.raven.UserStories.model;

import com.kruczyteam.raven.Backlog.model.Backlog;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "user_stories")
public class UserStories
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "backlog_id")
    private Backlog backlog;

    public UserStories()
    {
    }

    public UserStories(String description, Backlog backlog)
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

    public Backlog getBacklog()
    {
        return backlog;
    }

    public void setBacklog(Backlog backlog)
    {
        this.backlog = backlog;
    }
}

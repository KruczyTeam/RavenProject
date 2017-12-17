package com.kruczyteam.raven.Backlog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kruczyteam.raven.UserStory.model.UserStory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "backlogs")
public class Backlog
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "backlog", cascade = CascadeType.REMOVE)
    private List<UserStory> userStories;

    public Backlog()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<UserStory> getUserStories()
    {
        return userStories;
    }

    public void setUserStories(List<UserStory> userStories)
    {
        this.userStories = userStories;
    }
}

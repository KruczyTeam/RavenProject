package com.kruczyteam.raven.BackLog;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_backlogs")
public class Backlog
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "backlogs_id")
    private Long id;

    @NotEmpty
    @Column(name = "backlogs_name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

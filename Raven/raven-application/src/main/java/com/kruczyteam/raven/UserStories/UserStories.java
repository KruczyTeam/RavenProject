package com.kruczyteam.raven.UserStories;

import com.kruczyteam.raven.BackLog.Backlog;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_userStories")
public class UserStories {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userStoriesId")
    private Long userStoriesId;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="backlog_id")
    private Backlog fBacklog;

	@NotEmpty
	@Column(name = "userStoriesDescription")
	private String description;

    public Long getUserStoriesId() {
        return userStoriesId;
    }

    public void setUserStoriesId(Long id) {
        this.userStoriesId = id;
    }

    public Backlog getfBacklog() {
        return fBacklog;
    }

    public void setfBacklog(Backlog f_backlog) {
        this.fBacklog = f_backlog;
    }


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

package com.kruczyteam.raven.UserStories.repository;

import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.UserStories.model.UserStories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserStoriesRepository extends CrudRepository<UserStories,Long>
{
    List<UserStories> findByBacklogId(Long backlogId);
    UserStories findByBacklogAndId(Backlog backlog, Long userStoriesId);
}

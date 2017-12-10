package com.kruczyteam.raven.UserStory.repository;

import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.UserStory.model.UserStory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserStoryRepository extends CrudRepository<UserStory,Long>
{
    List<UserStory> findByBacklog(Backlog backlog);
    UserStory findByBacklogAndId(Backlog backlog, Long userStoryId);
}

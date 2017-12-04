package com.kruczyteam.raven.UserStory.repository;

import com.kruczyteam.raven.UserStory.model.UserStory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserStoryRepository extends CrudRepository<UserStory,Long>
{
    List<UserStory> findByBacklogId(Long backlogId);
    UserStory findOneByBacklogIdAndId(Long backlogId, Long userStoryId);
}

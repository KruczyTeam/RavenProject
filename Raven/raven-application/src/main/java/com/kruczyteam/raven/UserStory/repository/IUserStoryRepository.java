package com.kruczyteam.raven.UserStory.repository;

import com.kruczyteam.raven.Backlog.model.Backlog;
import com.kruczyteam.raven.UserStory.model.UserStory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserStoryRepository extends CrudRepository<UserStory, Long>
{
	List<UserStory> findByBacklog(Backlog backlog);
}

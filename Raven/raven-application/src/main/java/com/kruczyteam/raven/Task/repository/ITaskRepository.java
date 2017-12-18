package com.kruczyteam.raven.Task.repository;

import com.kruczyteam.raven.Task.model.Task;
import com.kruczyteam.raven.UserStory.model.UserStory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaskRepository extends CrudRepository<Task, Long>
{
	List<Task> findByUserStory(UserStory userStory);
}

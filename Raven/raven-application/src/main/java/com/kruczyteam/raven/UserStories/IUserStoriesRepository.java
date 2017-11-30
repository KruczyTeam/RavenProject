package com.kruczyteam.raven.UserStories;

import com.kruczyteam.raven.BackLog.Backlog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserStoriesRepository extends CrudRepository<UserStories,Long>{
    List<UserStories> findByfBacklog(Backlog fBacklogId);
}

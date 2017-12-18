package com.kruczyteam.raven.Backlog.repository;

import com.kruczyteam.raven.Backlog.model.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBacklogRepository extends CrudRepository<Backlog, Long>
{

}

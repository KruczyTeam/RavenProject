package com.kruczyteam.raven.History.repository;

import com.kruczyteam.raven.History.model.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHistoryRepository extends CrudRepository<History, Long>
{
}
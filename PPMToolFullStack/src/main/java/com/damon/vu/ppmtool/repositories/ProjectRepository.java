package com.damon.vu.ppmtool.repositories;

import com.damon.vu.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByProjectIdentifier(String projectIdentifier);

    // No need
    @Override
    Iterable<Project> findAll();



}

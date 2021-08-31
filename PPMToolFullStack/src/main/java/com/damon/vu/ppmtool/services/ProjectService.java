package com.damon.vu.ppmtool.services;

import com.damon.vu.ppmtool.domain.Project;
import com.damon.vu.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository theProjectRepository) {
        this.projectRepository = theProjectRepository;
    }

    public Project saveOrUpdateProject(Project theProject) {

        // Logic (once adding user and backlog)
        return projectRepository.save(theProject);
    }
}

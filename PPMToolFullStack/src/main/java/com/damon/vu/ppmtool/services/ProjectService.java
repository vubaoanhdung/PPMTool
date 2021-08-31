package com.damon.vu.ppmtool.services;

import com.damon.vu.ppmtool.domain.Project;
import com.damon.vu.ppmtool.exceptions.ProjectIdentifierException;
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
        try {
            theProject.setProjectIdentifier(theProject.getProjectIdentifier().toUpperCase());
            return projectRepository.save(theProject);
        }catch (Exception e) {
            throw new ProjectIdentifierException("Project ID '" + theProject.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Project findProjectByProjectIdentifier(String projectIdentifier) {
        Project project = projectRepository.findProjectByProjectIdentifier(projectIdentifier.toUpperCase());
        if (project == null) {
            throw new ProjectIdentifierException("Project ID " + projectIdentifier + " does not exist");
        }
        return project;
    }
}

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

    public Project saveOrUpdate(Project theProject) {

        try {
            theProject.setProjectIdentifier(theProject.getProjectIdentifier().toUpperCase());
            return projectRepository.save(theProject);
        }catch (Exception e) {
            throw new ProjectIdentifierException("Project ID '" + theProject.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Project findByProjectIdentifier(String projectIdentifier) {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if (project == null) {
            throw new ProjectIdentifierException("Project ID " + projectIdentifier + " does not exist");
        }
        return project;
    }

    public Iterable<Project> findAll() {
        return projectRepository.findAll();
    }

    public void deleteByIdentifier(String projectIdentifier) {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if (project == null) {
            throw new ProjectIdentifierException("Cannot delete Project with ID " + projectIdentifier + "! This project does not exist");
        }
        projectRepository.delete(project);
    }


}

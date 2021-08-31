package com.damon.vu.ppmtool.web;

import com.damon.vu.ppmtool.domain.Project;
import com.damon.vu.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    private ProjectService projectService;

    // injecting the service using constructor
    @Autowired
    public ProjectController(ProjectService theProjectService) {
        this.projectService = theProjectService;
    }

    @PostMapping("")
    public ResponseEntity<?>createNewProject(@Valid @RequestBody Project theProject, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<String>("Invalid Project object", HttpStatus.BAD_REQUEST);
        }
        // save the project to database
        Project p = projectService.saveOrUpdateProject(theProject);

        // returning the project as well as the status of the request
        return new ResponseEntity<Project>(p, HttpStatus.CREATED);
    }


}

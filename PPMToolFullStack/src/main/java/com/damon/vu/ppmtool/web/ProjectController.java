package com.damon.vu.ppmtool.web;

import com.damon.vu.ppmtool.domain.Project;
import com.damon.vu.ppmtool.services.MapValidationErrorService;
import com.damon.vu.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    public ProjectController() {}

    @PostMapping("")
    public ResponseEntity<?>createNewProject(@Valid @RequestBody Project theProject, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.mapError(result);
        if (errorMap != null) return errorMap;

        // otherwise
        // save the project to database
        Project p = projectService.saveOrUpdate(theProject);
        // returning the project as well as the status of the request
        return new ResponseEntity<Project>(p, HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> getProjectByProjectIdentifier(@PathVariable String projectIdentifier) {
        Project project = projectService.findByProjectIdentifier(projectIdentifier);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects() {
        return projectService.findAll();
    }

    @DeleteMapping("/{projectIdentifier}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectIdentifier) {
        this.projectService.deleteByIdentifier(projectIdentifier);
        return new ResponseEntity<String>("Project with ID: " + projectIdentifier + " was deleted!", HttpStatus.OK);
    }





}

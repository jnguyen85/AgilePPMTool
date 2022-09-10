package io.agileintelligence.ppmtool.web;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.services.MapValicationErrorService;
import io.agileintelligence.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    private MapValicationErrorService mapValicationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Validated @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorMap = mapValicationErrorService.MapValicationService(result);
        if(errorMap != null) {
            return errorMap;
        }
        Project theProject = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(theProject, HttpStatus.CREATED);
    }
}

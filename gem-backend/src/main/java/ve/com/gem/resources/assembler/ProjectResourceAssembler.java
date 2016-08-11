package ve.com.gem.resources.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.ProjectController;
import ve.com.gem.entities.Project;
import ve.com.gem.resources.ProjectResource;

@Component
public class ProjectResourceAssembler extends ResourceAssemblerSupport<Project, ProjectResource> {

		
	public ProjectResourceAssembler() {
		super(ProjectController.class, ProjectResource.class);
	}

	@Override
	public ProjectResource toResource(Project project) {
		ProjectResource resource = new ProjectResource();
		resource.setName(project.getName());
		resource.setDescription(project.getDescription());
		resource.setCreatedAt(project.getCreatedAt());
		resource.setUpdatedAt(project.getUpdatedAt());
		resource.setDeletedAt(project.getDeletedAt());
		resource.setEstimatedStartDate(project.getEstimatedStartDate());
		resource.setStartDate(project.getStartDate());
		resource.setEstimatedDateEnd(project.getEstimatedDateEnd());
		resource.setIsActive(project.getIsActive());
		resource.setIds(project.getId());
		return resource;
	}
	
	
}

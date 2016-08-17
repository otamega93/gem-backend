package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.AccountController;
import ve.com.gem.controllers.PhaseController;
import ve.com.gem.controllers.ProjectController;
import ve.com.gem.entities.Phase;
import ve.com.gem.resources.PhaseResource;
import ve.com.gem.services.IPhaseService;

@Component
public class PhaseResourceAssembler extends ResourceAssemblerSupport<Phase, PhaseResource> {
	
	public PhaseResourceAssembler() {
		super(PhaseController.class, PhaseResource.class);
	}

	@Override
	public PhaseResource toResource(Phase phase) {
		PhaseResource resource = new PhaseResource();
		resource.setName(phase.getName());
		resource.setDescription(phase.getDescription());
		resource.setValue(phase.getValue());
		resource.setDateEnd(phase.getDateEnd());
		resource.setEstimatedDateEnd(phase.getEstimatedDateEnd());
		resource.setStartDate(phase.getStartDate());
		resource.setEstimatedStartDate(phase.getEstimatedStartDate());
		resource.setIds(phase.getId());
		//resource.setProject(phase.getProject());
		resource.setDepartment(phase.getDepartment());
		resource.add(linkTo(PhaseController.class).slash("").slash(phase.getId()).withSelfRel());
		if(phase.getProject()!=null){
			resource.setProjectName(phase.getProject().getName());
			resource.add(linkTo(ProjectController.class).slash(phase.getId()).withRel("project"));
		}
		if(phase.getDepartment()!=null)
			resource.setDepartmentName(phase.getDepartment().getName());
		resource.add(linkTo(PhaseController.class).slash(phase.getId()).slash("tasks").withRel("tasks"));
		
		return resource;
		
	}
}

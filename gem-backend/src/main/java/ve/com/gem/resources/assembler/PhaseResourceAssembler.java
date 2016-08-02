package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.PhaseController;
import ve.com.gem.controllers.ProjectController;
import ve.com.gem.entities.Phase;
import ve.com.gem.resources.PhaseResource;
import ve.com.gem.services.IPhaseService;

@Component
public class PhaseResourceAssembler extends ResourceAssemblerSupport<Phase, PhaseResource> {

	@Autowired
	private IPhaseService phaseService;
	
	public PhaseResourceAssembler() {
		super(PhaseController.class, PhaseResource.class);
	}

	@Override
	public PhaseResource toResource(Phase phase) {
		PhaseResource resource = new PhaseResource();
		resource.setName(phase.getName());		
		resource.setIds(phase.getId());
		resource.setTask(phaseService.findTaskFromPhase(phase.getId()));
		resource.add(linkTo(ProjectController.class).slash("").slash(phase.getId()).withSelfRel());
		resource.add(linkTo(ProjectController.class).slash("").slash(phase.getId()).withRel("phase"));
		return resource;
	}
	
	
}
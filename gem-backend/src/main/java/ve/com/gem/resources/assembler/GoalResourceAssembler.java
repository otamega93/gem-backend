package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.AccountController;
import ve.com.gem.controllers.GoalController;
import ve.com.gem.entities.Goal;
import ve.com.gem.resources.GoalResource;

@Component
public class GoalResourceAssembler extends ResourceAssemblerSupport<Goal, GoalResource>{

	public GoalResourceAssembler() {
		super(GoalController.class, GoalResource.class);
	}
	
	@Override
	public GoalResource toResource(Goal object) {
//		CompanyResource companyResource = new CompanyResource();
		GoalResource resource = createResourceWithId(object.getId(), object);
		resource.setName(object.getName());
		resource.setDescription(object.getDescription());
//		resource.setCreatedAt(company.getCreatedAt());
//		resource.setUpdatedAt(company.getUpdatedAt());
//		resource.setDeletedAt(company.getDeletedAt());
		resource.setIds(object.getId());
		resource.add(linkTo(GoalController.class).slash("").slash(object.getId()).withSelfRel());
		return resource;
	}	
}

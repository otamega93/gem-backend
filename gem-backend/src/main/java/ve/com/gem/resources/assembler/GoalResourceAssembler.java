package ve.com.gem.resources.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.GoalController;
import ve.com.gem.entities.Goal;
import ve.com.gem.resources.GoalResource;

@Component
public class GoalResourceAssembler extends ResourceAssemblerSupport<Goal, GoalResource>{

	public GoalResourceAssembler() {
		super(GoalController.class, GoalResource.class);
	}
	
	@Override
	public GoalResource toResource(Goal objeto) {
//		CompanyResource companyResource = new CompanyResource();
		GoalResource resource = createResourceWithId(objeto.getId(), objeto);
		resource.setName(objeto.getName());
		resource.setDescription(objeto.getDescription());
//		resource.setCreatedAt(company.getCreatedAt());
//		resource.setUpdatedAt(company.getUpdatedAt());
//		resource.setDeletedAt(company.getDeletedAt());
		resource.setIds(objeto.getId());
		return resource;
	}	
}

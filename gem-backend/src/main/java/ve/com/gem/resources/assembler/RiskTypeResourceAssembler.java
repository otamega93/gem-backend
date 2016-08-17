package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.RiskLevelController;
import ve.com.gem.controllers.RiskTypeController;
import ve.com.gem.entities.RiskType;
import ve.com.gem.resources.RiskTypeResource;

@Component
public class RiskTypeResourceAssembler extends ResourceAssemblerSupport<RiskType, RiskTypeResource>{

	public RiskTypeResourceAssembler() {
		super(RiskTypeController.class, RiskTypeResource.class);
	}
	
	@Override
	public RiskTypeResource toResource(RiskType object) {
//		CompanyResource companyResource = new CompanyResource();
		RiskTypeResource resource = createResourceWithId(object.getId(), object);
		resource.setName(object.getName());
		resource.setDescription(object.getDescription());
//		resource.setCreatedAt(company.getCreatedAt());
//		resource.setUpdatedAt(company.getUpdatedAt());
//		resource.setDeletedAt(company.getDeletedAt());
		resource.setIds(object.getId());
		resource.add(linkTo(RiskLevelController.class).slash("").slash(object.getId()).withSelfRel());
		return resource;
	}	
}

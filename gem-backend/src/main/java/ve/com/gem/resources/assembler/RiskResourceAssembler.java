package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.RiskController;
import ve.com.gem.controllers.RiskLevelController;
import ve.com.gem.entities.Risk;
import ve.com.gem.resources.RiskResource;

@Component
public class RiskResourceAssembler extends ResourceAssemblerSupport<Risk, RiskResource>{

	public RiskResourceAssembler() {
		super(RiskController.class, RiskResource.class);
	}
	
	@Override
	public RiskResource toResource(Risk object) {
//		CompanyResource companyResource = new CompanyResource();
		RiskResource resource = createResourceWithId(object.getId(), object);
		resource.setName(object.getName());
		resource.setDescription(object.getDescription());
//		resource.setCreatedAt(company.getCreatedAt());
//		resource.setUpdatedAt(company.getUpdatedAt());
//		resource.setDeletedAt(company.getDeletedAt());
		resource.setIds(object.getId());
		resource.add(linkTo(RiskController.class).slash("").slash(object.getId()).withSelfRel());
		return resource;
	}	
}

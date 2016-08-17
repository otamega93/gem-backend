package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.AccountController;
import ve.com.gem.controllers.RiskLevelController;
import ve.com.gem.entities.RiskLevel;
import ve.com.gem.resources.RiskLevelResource;

@Component
public class RiskLevelResourceAssembler extends ResourceAssemblerSupport<RiskLevel, RiskLevelResource>{

	public RiskLevelResourceAssembler() {
		super(RiskLevelController.class, RiskLevelResource.class);
	}
	
	@Override
	public RiskLevelResource toResource(RiskLevel object) {
//		CompanyResource companyResource = new CompanyResource();
		RiskLevelResource resource = createResourceWithId(object.getId(), object);
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

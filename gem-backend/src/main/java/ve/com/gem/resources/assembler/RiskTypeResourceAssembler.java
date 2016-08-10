package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.CompanyController;
import ve.com.gem.controllers.DocumentStateController;
import ve.com.gem.controllers.RiskTypeController;
import ve.com.gem.entities.Company;
import ve.com.gem.entities.DocumentState;
import ve.com.gem.entities.RiskType;
import ve.com.gem.resources.CompanyResource;
import ve.com.gem.resources.DocumentStateResource;
import ve.com.gem.resources.GemResource;
import ve.com.gem.resources.RiskTypeResource;

@Component
public class RiskTypeResourceAssembler extends ResourceAssemblerSupport<RiskType, RiskTypeResource>{

	public RiskTypeResourceAssembler() {
		super(RiskTypeController.class, RiskTypeResource.class);
	}
	
	@Override
	public RiskTypeResource toResource(RiskType objeto) {
//		CompanyResource companyResource = new CompanyResource();
		RiskTypeResource resource = createResourceWithId(objeto.getId(), objeto);
		resource.setName(objeto.getName());
		resource.setDescription(objeto.getDescription());
//		resource.setCreatedAt(company.getCreatedAt());
//		resource.setUpdatedAt(company.getUpdatedAt());
//		resource.setDeletedAt(company.getDeletedAt());
		resource.setIds(objeto.getId());
		return resource;
	}	
}

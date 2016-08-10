package ve.com.gem.resources.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.RiskController;
import ve.com.gem.entities.Risk;
import ve.com.gem.resources.RiskResource;

@Component
public class RiskResourceAssembler extends ResourceAssemblerSupport<Risk, RiskResource>{

	public RiskResourceAssembler() {
		super(RiskController.class, RiskResource.class);
	}
	
	@Override
	public RiskResource toResource(Risk objeto) {
//		CompanyResource companyResource = new CompanyResource();
		RiskResource resource = createResourceWithId(objeto.getId(), objeto);
		resource.setName(objeto.getName());
		resource.setDescription(objeto.getDescription());
//		resource.setCreatedAt(company.getCreatedAt());
//		resource.setUpdatedAt(company.getUpdatedAt());
//		resource.setDeletedAt(company.getDeletedAt());
		resource.setIds(objeto.getId());
		return resource;
	}	
}

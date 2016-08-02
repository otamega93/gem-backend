package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.CompanyController;
import ve.com.gem.controllers.DocumentStateController;
import ve.com.gem.controllers.ExpertController;
import ve.com.gem.entities.Company;
import ve.com.gem.entities.DocumentState;
import ve.com.gem.entities.Expert;
import ve.com.gem.resources.CompanyResource;
import ve.com.gem.resources.DocumentStateResource;
import ve.com.gem.resources.ExpertResource;
import ve.com.gem.resources.GemResource;

@Component
public class ExpertResourceAssembler extends ResourceAssemblerSupport<Expert, ExpertResource>{

	public ExpertResourceAssembler() {
		super(ExpertController.class, ExpertResource.class);
	}
	
	@Override
	public ExpertResource toResource(Expert objeto) {
//		CompanyResource companyResource = new CompanyResource();
		ExpertResource resource = createResourceWithId(objeto.getId(), objeto);
		resource.setName(objeto.getName());
		resource.setDescription(objeto.getDescription());
//		resource.setCreatedAt(company.getCreatedAt());
//		resource.setUpdatedAt(company.getUpdatedAt());
//		resource.setDeletedAt(company.getDeletedAt());
		resource.setIds(objeto.getId());
		resource.add(linkTo(CompanyController.class).slash("").slash(objeto.getId()).withSelfRel());
		resource.add(linkTo(CompanyController.class).slash("").slash(objeto.getId()).withRel("company"));
		return resource;
	}	
}

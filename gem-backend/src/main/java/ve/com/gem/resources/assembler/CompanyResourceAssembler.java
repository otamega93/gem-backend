package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.CompanyController;
import ve.com.gem.entities.Company;
import ve.com.gem.resources.CompanyResource;

@Component
public class CompanyResourceAssembler extends ResourceAssemblerSupport<Company, CompanyResource>{

	public CompanyResourceAssembler() {
		super(CompanyController.class, CompanyResource.class);
	}
	
	@Override
	public CompanyResource toResource(Company company) {

		CompanyResource resource = createResourceWithId(company.getId(), company);
		resource.setName(company.getName());
		resource.setDescription(company.getDescription());
		resource.setIds(company.getId());

		resource.add(linkTo(CompanyController.class).slash(company.getId()).withSelfRel());
		return resource;
	}	
}

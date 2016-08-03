package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.CompanyController;
import ve.com.gem.controllers.ValorationController;
import ve.com.gem.entities.Valoration;
import ve.com.gem.resources.ValorationResource;

@Component
public class ValorationResourceAssembler extends ResourceAssemblerSupport<Valoration, ValorationResource>{

	public ValorationResourceAssembler() {
		super(ValorationController.class, ValorationResource.class);
	}
	
	@Override
	public ValorationResource toResource(Valoration objeto) {
//		CompanyResource companyResource = new CompanyResource();
		ValorationResource resource = createResourceWithId(objeto.getId(), objeto);
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

package ve.com.gem.resources.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.ExpertController;
import ve.com.gem.entities.Expert;
import ve.com.gem.resources.ExpertResource;

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
		//resource.add(linkTo(ExpertController.class).slash("").slash(objeto.getId()).withSelfRel());
		return resource;
	}	
}

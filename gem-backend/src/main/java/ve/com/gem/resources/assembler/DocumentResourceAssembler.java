package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.CompanyController;
import ve.com.gem.controllers.DocumentController;
import ve.com.gem.controllers.DocumentStateController;
import ve.com.gem.entities.Company;
import ve.com.gem.entities.Document;
import ve.com.gem.entities.DocumentState;
import ve.com.gem.resources.CompanyResource;
import ve.com.gem.resources.DocumentResource;
import ve.com.gem.resources.DocumentStateResource;
import ve.com.gem.resources.GemResource;

@Component
public class DocumentResourceAssembler extends ResourceAssemblerSupport<Document, DocumentResource>{

	public DocumentResourceAssembler() {
		super(DocumentController.class, DocumentResource.class);
	}
	
	@Override
	public DocumentResource toResource(Document objeto) {
//		CompanyResource companyResource = new CompanyResource();
		DocumentResource resource = createResourceWithId(objeto.getId(), objeto);
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

package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.CompanyController;
import ve.com.gem.controllers.DocumentController;
import ve.com.gem.entities.Document;
import ve.com.gem.resources.DocumentResource;

@Component
public class DocumentResourceAssembler extends ResourceAssemblerSupport<Document, DocumentResource>{

	
	
	public DocumentResourceAssembler() {
		super(DocumentController.class, DocumentResource.class);
	}
	
	@Override
	public DocumentResource toResource(Document objeto) {
		DocumentResource resource = createResourceWithId(objeto.getId(), objeto);
		resource.setName(objeto.getName());
		resource.setDescription(objeto.getDescription());
		resource.setIds(objeto.getId());
		resource.add(linkTo(CompanyController.class).slash("").slash(objeto.getId()).withSelfRel());
		resource.add(linkTo(CompanyController.class).slash("").slash(objeto.getId()).withRel("company"));
		return resource;
	}	

	
	
	
	
	
	
	
	

	
	
}

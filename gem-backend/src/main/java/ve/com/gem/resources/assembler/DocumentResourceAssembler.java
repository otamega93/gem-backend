package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.DocumentController;
import ve.com.gem.entities.Document;
import ve.com.gem.resources.DocumentResource;

@Component
public class DocumentResourceAssembler extends ResourceAssemblerSupport<Document, DocumentResource>{

	
	
	public DocumentResourceAssembler() {
		super(DocumentController.class, DocumentResource.class);
	}
	
	@Override
	public DocumentResource toResource(Document object) {
		DocumentResource resource = createResourceWithId(object.getId(), object);
		resource.setName(object.getName());
		resource.setDescription(object.getDescription());
		resource.setIds(object.getId());
		resource.add(linkTo(DocumentController.class).slash("").slash(object.getId()).withSelfRel());
		return resource;
	}	

	
	
	
	
	
	
	
	

	
	
}

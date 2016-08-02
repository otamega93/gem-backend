package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.DocumentStateController;
import ve.com.gem.entities.DocumentState;
import ve.com.gem.resources.DocumentStateResource;

@Component
public class DocumentStateResourceAssembler extends ResourceAssemblerSupport<DocumentState, DocumentStateResource>{

	public DocumentStateResourceAssembler() {
		super(DocumentStateController.class, DocumentStateResource.class);
	}
	
	@Override
	public DocumentStateResource toResource(DocumentState documentState) {
		DocumentStateResource resource = new DocumentStateResource();
		resource.setName(documentState.getName());
		resource.setDescription(documentState.getDescription());
		resource.setCreatedAt(documentState.getCreatedAt());
		resource.setUpdatedAt(documentState.getUpdatedAt());
		resource.setDeletedAt(documentState.getDeletedAt());
		resource.setIds(documentState.getId());
		resource.add(linkTo(DocumentStateController.class).slash("").slash(documentState.getId()).withSelfRel());
	    resource.add(linkTo(DocumentStateController.class).slash("").slash(documentState.getId()).withRel("documentState"));
		return resource;
	}

	
}

package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.EndPointServiceAuthenticationController;
import ve.com.gem.entities.EndPointServiceAuthentication;
import ve.com.gem.resources.EndPointServiceAuthenticationResource;

@Component
public class EndPointServiceAuthenticationResourceAssembler extends ResourceAssemblerSupport<EndPointServiceAuthentication, EndPointServiceAuthenticationResource>{

	public EndPointServiceAuthenticationResourceAssembler () {
		super(EndPointServiceAuthenticationController.class, EndPointServiceAuthenticationResource.class);
	}
	
	@Override
	public EndPointServiceAuthenticationResource toResource(EndPointServiceAuthentication endPointServiceAuthentication) {
		EndPointServiceAuthenticationResource resource = new EndPointServiceAuthenticationResource();
		resource.setName(endPointServiceAuthentication.getName());
		resource.setDescription(endPointServiceAuthentication.getDescription());
		resource.setAuthorities(endPointServiceAuthentication.getAuthorities());
		resource.setIds(endPointServiceAuthentication.getId());
		resource.setCreatedAt(endPointServiceAuthentication.getCreatedAt());
		resource.setUpdatedAt(endPointServiceAuthentication.getDeletedAt());
		resource.setIsActive(endPointServiceAuthentication.getIsActive());
		resource.add(linkTo(EndPointServiceAuthenticationController.class).slash("").slash(endPointServiceAuthentication.getId()).withSelfRel());
		return resource;
		
	}
}

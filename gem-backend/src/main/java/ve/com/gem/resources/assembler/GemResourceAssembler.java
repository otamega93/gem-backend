package ve.com.gem.resources.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.GemController;
import ve.com.gem.entities.Gem;
import ve.com.gem.resources.GemResource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class GemResourceAssembler extends ResourceAssemblerSupport<Gem, GemResource> {

	public GemResourceAssembler() {
		super(GemController.class,GemResource.class);
	}
	
	/*
	 * Create custom representation of your object, compatible with pagination and sorting
	 * @see org.springframework.hateoas.ResourceAssembler#toResource(java.lang.Object)
	 */
	@Override
	public GemResource toResource(Gem gem) {
		GemResource resource = createResourceWithId(gem.getId(), gem);
		resource.setName(gem.getName());
		resource.setDescription(gem.getDescription());
		resource.setIds(gem.getId());
		/*
		 * ADD custom link to your representation object
		 */
		//resource.add(linkTo(GemController.class).slash(gem.getId()).withRel("gem"));
		return resource;
	}

}

package ve.com.gem.resources.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import ve.com.gem.controllers.NatureController;
import ve.com.gem.entities.Nature;
import ve.com.gem.resources.NatureResource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class NatureResourceAssembler extends ResourceAssemblerSupport<Nature, NatureResource>{

	public NatureResourceAssembler() {
		super(NatureController.class,NatureResource.class);
	}

	@Override
	public NatureResource toResource(Nature nature) {
		NatureResource resource = createResourceWithId(nature.getId(), nature);
		resource.setName(nature.getName());
		resource.setDescription(nature.getDescription());
		resource.setIds(nature.getId());
		resource.add(linkTo(NatureController.class).slash(nature.getId()).withRel("nature"));
		return resource;
	}

}

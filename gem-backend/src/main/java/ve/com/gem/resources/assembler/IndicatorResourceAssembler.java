package ve.com.gem.resources.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.IndicatorController;
import ve.com.gem.entities.Indicator;
import ve.com.gem.resources.IndicatorResource;

@Component
public class IndicatorResourceAssembler extends ResourceAssemblerSupport<Indicator, IndicatorResource>{

	public IndicatorResourceAssembler() {
		super(IndicatorController.class, IndicatorResource.class);
	}
	
	@Override
	public IndicatorResource toResource(Indicator object) {
		IndicatorResource resource = createResourceWithId(object.getId(), object);
		resource.setName(object.getName());
		resource.setDescription(object.getDescription());
		resource.setIds(object.getId());
		return resource;
	}	

	
	
	
	
	
	
	
	

	
	
}

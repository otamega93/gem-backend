package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.CompanyController;
import ve.com.gem.controllers.DepartmentController;
import ve.com.gem.controllers.DocumentStateController;
import ve.com.gem.entities.Company;
import ve.com.gem.entities.Department;
import ve.com.gem.entities.DocumentState;
import ve.com.gem.resources.CompanyResource;
import ve.com.gem.resources.DepartmentResource;
import ve.com.gem.resources.DocumentStateResource;
import ve.com.gem.resources.GemResource;

@Component
public class DepartamentResourceAssembler extends ResourceAssemblerSupport<Department, DepartmentResource>{

	public DepartamentResourceAssembler() {
		super(DepartmentController.class, DepartmentResource.class);
	}
	
	@Override
	public DepartmentResource toResource(Department objeto) {
//		CompanyResource companyResource = new CompanyResource();
		DepartmentResource resource = createResourceWithId(objeto.getId(), objeto);
		resource.setName(objeto.getName());
		resource.setDescription(objeto.getDescription());
//		resource.setCreatedAt(company.getCreatedAt());
//		resource.setUpdatedAt(company.getUpdatedAt());
//		resource.setDeletedAt(company.getDeletedAt());
		resource.setIds(objeto.getId());
		resource.add(linkTo(DepartmentController.class).slash("").slash(objeto.getId()).withSelfRel());
		resource.add(linkTo(DepartmentController.class).slash("").slash(objeto.getId()).withRel("company"));
		return resource;
	}	
}

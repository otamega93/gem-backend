package ve.com.gem.resources.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.ExpertController;
import ve.com.gem.entities.Employee;
import ve.com.gem.resources.EmployeeResource;

@Component
public class EmployeeResourceAssembler extends ResourceAssemblerSupport<Employee, EmployeeResource>{

	public EmployeeResourceAssembler() {
		super(ExpertController.class, EmployeeResource.class);
	}
	
	@Override
	public EmployeeResource toResource(Employee objeto) {
//		CompanyResource companyResource = new CompanyResource();
		EmployeeResource resource = createResourceWithId(objeto.getId(), objeto);
		resource.setName(objeto.getName());
//		resource.setDescription(objeto.getDescription());
//		resource.setCreatedAt(company.getCreatedAt());
//		resource.setUpdatedAt(company.getUpdatedAt());
//		resource.setDeletedAt(company.getDeletedAt());
		resource.setIds(objeto.getId());
		//resource.add(linkTo(ExpertController.class).slash("").slash(objeto.getId()).withSelfRel());
		return resource;
	}	
}

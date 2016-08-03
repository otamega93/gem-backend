package ve.com.gem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ve.com.gem.entities.Department;
import ve.com.gem.resources.DepartmentResource;
import ve.com.gem.resources.assembler.DepartmentResourceAssembler;
import ve.com.gem.services.IDepartmentService;


@RestController
@RequestMapping(value = "/api/v1/companies")
public class DepartmentController {

	@Autowired
	IDepartmentService service;
	
	@Autowired
	private DepartmentResourceAssembler assembler;

	@Autowired
	private PagedResourcesAssembler<Department> pageAssembler;
	
	public DepartmentController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * List all gems.
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET,produces="application/hal+json")
	@ResponseBody
	public PagedResources<DepartmentResource> loadAll(Pageable pageable){
		
		Page<Department> objects = service.findAll(pageable);
	
		return pageAssembler.toResource(objects, assembler);
	}
	
	/**
	 * Find one gem.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<DepartmentResource> load(@PathVariable Long id)
	{
		Department object = service.findById(id);
		if(null == object)
		{
			return new ResponseEntity<DepartmentResource>(assembler.toResource(object),HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<DepartmentResource>(assembler.toResource(object),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="",method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<DepartmentResource> save(@RequestBody Department object)
	{
		if(service.save(object)!=null)
		{
			return new ResponseEntity<DepartmentResource>(assembler.toResource(object),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<DepartmentResource>(assembler.toResource(object),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<DepartmentResource> update(@PathVariable Long id,@RequestBody Department object)
	{
		Department search = service.findById(id);
		if(null == search)
		{
			return new ResponseEntity<DepartmentResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
		}else
		
		if(null != object )
		{
			object.setId(id);
			service.save(object);
			return new ResponseEntity<DepartmentResource>(assembler.toResource(search),HttpStatus.OK);
		}
		else
			return new ResponseEntity<DepartmentResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
	public ResponseEntity<Department> delete(@PathVariable Long id){
		Department search = service.findById(id);
		System.out.println(search);
		if(null != search)
		{
			service.delete(search);
			return new ResponseEntity<Department>(HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
}

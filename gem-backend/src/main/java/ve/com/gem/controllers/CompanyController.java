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

import ve.com.gem.entities.Company;
import ve.com.gem.resources.CompanyResource;
import ve.com.gem.resources.assembler.CompanyResourceAssembler;
import ve.com.gem.services.ICompanyService;

@RestController
@RequestMapping(value = "/api/v1/companies")
public class CompanyController {

	@Autowired
	ICompanyService service;
	
	@Autowired
	private CompanyResourceAssembler assembler;

	@Autowired
	private PagedResourcesAssembler<Company> pageAssembler;
	
	
	public CompanyController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * List all gems.
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET,produces="application/hal+json")
	@ResponseBody
	public PagedResources<CompanyResource> loadAll(Pageable pageable){
		
		Page<Company> objects = service.findAll(pageable);
	
		return pageAssembler.toResource(objects, assembler);
	}
	
	/**
	 * Find one gem.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<CompanyResource> load(@PathVariable Long id)
	{
		Company object = service.findById(id);
		if(null == object)
		{
			return new ResponseEntity<CompanyResource>(assembler.toResource(object),HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<CompanyResource>(assembler.toResource(object),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="",method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<CompanyResource> save(@RequestBody Company object)
	{
		if(service.save(object)!=null)
		{
			return new ResponseEntity<CompanyResource>(assembler.toResource(object),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<CompanyResource>(assembler.toResource(object),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<CompanyResource> update(@PathVariable Long id,@RequestBody Company object)
	{
		Company search = service.findById(id);
		if(null == search)
		{
			return new ResponseEntity<CompanyResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
		}else
		
		if(null != object )
		{
			object.setId(id);
			service.save(object);
			return new ResponseEntity<CompanyResource>(assembler.toResource(search),HttpStatus.OK);
		}
		else
			return new ResponseEntity<CompanyResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
	public ResponseEntity<Company> delete(@PathVariable Long id){
		Company search = service.findById(id);
		System.out.println(search);
		if(null != search)
		{
			service.delete(search);
			return new ResponseEntity<Company>(HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<Company>(HttpStatus.BAD_REQUEST);
		}
	}
	
		
}

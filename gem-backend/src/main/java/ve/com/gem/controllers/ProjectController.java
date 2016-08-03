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
import org.springframework.web.bind.annotation.RestController;

import ve.com.gem.entities.Project;
import ve.com.gem.resources.ProjectResource;
import ve.com.gem.resources.assembler.ProjectResourceAssembler;
import ve.com.gem.services.IProjectService;

@RestController
@RequestMapping(value = "/api/v1/projects")
public class ProjectController {

	@Autowired
	private IProjectService service;

	@Autowired
	private ProjectResourceAssembler assembler;

	@Autowired
	private PagedResourcesAssembler<Project> pageAssembler;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public PagedResources<ProjectResource> loadAll(Pageable pageable) {

		Page<Project> object = service.findAll(pageable);
		return pageAssembler.toResource(object, assembler);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<ProjectResource> save (@RequestBody Project object) {
		if (null != object) {
			service.save(object);
			return new ResponseEntity<ProjectResource>(assembler.toResource(object), HttpStatus.OK);
		}
		else 
			return new ResponseEntity<ProjectResource>(assembler.toResource(object), HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProjectResource> findById (@PathVariable Long id) {
		
		if (null != id) {

			Project object = service.findById(id);
			if (null != object) {

				return new ResponseEntity<ProjectResource>(assembler.toResource(object), HttpStatus.OK);
			}
		}

		else {

			return new ResponseEntity<ProjectResource>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ProjectResource>(HttpStatus.BAD_REQUEST);
	}
		
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ProjectResource> updateAccount(@PathVariable Long id, @RequestBody Project object) {

		Project search = service.findById(id);

		if (null == search) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (null != object) {
			
			service.save(object);
			return new ResponseEntity<ProjectResource>(assembler.toResource(object), HttpStatus.OK);
		}

		else {
			
			return new ResponseEntity<ProjectResource>(assembler.toResource(object),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

package ve.com.gem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ve.com.gem.entities.Job;
import ve.com.gem.resources.JobResource;
import ve.com.gem.resources.assembler.JobResourceAssembler;
import ve.com.gem.services.IJobService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/v1/jobs")
public class JobController {

	@Autowired
	private IJobService service;

	@Autowired
	private JobResourceAssembler assembler;

	@Autowired
	private PagedResourcesAssembler<Job> pageAssembler;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public PagedResources<JobResource> loadAll(Pageable pageable) {

		Page<Job> jobs = service.findAll(pageable);
		return pageAssembler.toResource(jobs, assembler);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<JobResource> save (@RequestBody Job job) {
		System.out.println(job.toString());
		if (null != job) {
			service.save(job);
			return new ResponseEntity<JobResource>(assembler.toResource(job), HttpStatus.OK);
		}
		else 
			return new ResponseEntity<JobResource>(assembler.toResource(job), HttpStatus.BAD_REQUEST);
	}
}

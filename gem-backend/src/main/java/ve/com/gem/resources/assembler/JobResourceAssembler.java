package ve.com.gem.resources.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.JobController;
import ve.com.gem.entities.Job;
import ve.com.gem.resources.JobResource;

@Component
public class JobResourceAssembler extends ResourceAssemblerSupport<Job, JobResource> {

	public JobResourceAssembler() {
		super(JobController.class, JobResource.class);
	}
	
	@Override
	public JobResource toResource(Job job) {
		JobResource resource = createResourceWithId(job.getId(), job);
		resource.setName(job.getName());
		resource.setDescription(job.getDescription());
		resource.setCreatedAt(job.getCreatedAt());
		resource.setUpdatedAt(job.getUpdatedAt());
		resource.setDeletedAt(job.getDeletedAt());
		resource.setIsActive(job.getIsActive());
		//jobResource.setTask(job.getTask());
		resource.setIds(job.getId());
		return resource;
	}

}

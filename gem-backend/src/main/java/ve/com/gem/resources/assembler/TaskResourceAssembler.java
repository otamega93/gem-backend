package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.JobController;
import ve.com.gem.controllers.ProjectController;
import ve.com.gem.controllers.TaskController;
import ve.com.gem.entities.Task;
import ve.com.gem.resources.TaskResource;
import ve.com.gem.services.ITaskService;

@Component
public class TaskResourceAssembler extends ResourceAssemblerSupport<Task, TaskResource>{

	@Autowired
	private ITaskService taskService;

	public TaskResourceAssembler () {
		super(TaskController.class, TaskResource.class);
	}
	
	@Override
	public TaskResource toResource(Task task) {
		TaskResource resource = new TaskResource();
		resource.setName(task.getName());
		resource.setDescription(task.getDescription());
		resource.setCreatedAt(task.getCreatedAt());
		resource.setUpdatedAt(task.getUpdatedAt());
		resource.setDeletedAt(task.getDeletedAt());
		resource.setIsActive(task.getIsActive());
		if(task.getDocumentState()!=null)
			resource.setDocumentState(taskService.findDocumentStateFromTaskId(task.getDocumentState().getId()));
		resource.setEstimatedStartDate(task.getEstimatedStartDate());
		resource.setStartDate(task.getStartDate());
		resource.setEstimatedDateEnd(task.getEstimatedDateEnd());
		resource.setDateEnd(task.getDateEnd());
		resource.setIds(task.getId());
		//MOISES desde aqui 22-08-2016
		if(task.getPhase()!=null){
			resource.setPhaseName(task.getPhase().getName());
			resource.add(linkTo(TaskController.class).slash(task.getId()).slash("phases").withRel("phase"));
		}
		//MOISES hasta aqui 22-08-2016
		resource.add(linkTo(TaskController.class).slash("").slash(task.getId()).withSelfRel());
		resource.add(linkTo(ProjectController.class).slash(task.getId()).slash("projects").withRel("projects"));
		resource.add(linkTo(TaskController.class).slash(task.getId()).slash("jobs").withRel("jobs"));
		return resource;
	}

}

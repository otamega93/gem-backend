package ve.com.gem.resources;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import ve.com.gem.entities.Project;

//import ve.com.gem.entities.Risk;

@Relation(collectionRelation = "phases")
public class PhaseResource extends ResourceSupport {

	private String name;
	private String description;
	private Long ids;
	

	private List<TaskResource> task = new ArrayList<TaskResource>();

	private Project project;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TaskResource> getTask() {
		return task;
	}
	
	public Project getProject() {
		return project;
	}

	public void setTask(List<TaskResource> task) {
		this.task = task;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}

	public Long getIds() {
		return ids;
	}

	public void setIds(Long ids) {
		this.ids = ids;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
package ve.com.gem.resources;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

//import ve.com.gem.entities.Risk;

@Relation(collectionRelation = "phases")
public class PhaseResource extends ResourceSupport {
	
	private String name;
	
	private Long ids;
	
	private List<TaskResource> task = new ArrayList<TaskResource>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TaskResource> getTask() {
		return task;
	}

	public void setTask(List<TaskResource> task) {
		this.task = task;
	}

	public Long getIds() {
		return ids;
	}

	public void setIds(Long ids) {
		this.ids = ids;
	}

}
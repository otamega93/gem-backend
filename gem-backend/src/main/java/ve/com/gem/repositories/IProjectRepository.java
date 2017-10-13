package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.Project;

public interface IProjectRepository {

	Project findByName(String name);

	@Query("SELECT project FROM Project project WHERE project.id=?1")
	Project findOne(Long id);

	List<Project> findByNameLike(String name, Pageable pageable);
	
	List<Project> findByNameLike(String name);

	@SuppressWarnings("unchecked")
	Project save(Project object);
	
	List<Project> findByAll(Pageable pageable);
	
	long count();

}

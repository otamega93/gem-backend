package ve.com.gem.repositories;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import ve.com.gem.entities.Project;

@Repository
public class ProjectRepository implements IProjectRepository {

	@PersistenceContext
	private EntityManager em;

	
	@Override
	public Project findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> findByNameLike(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> findByNameLike(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project save(Project object) {
		em.persist(object);
		return object;
	}

	@Override
	public List<Project> findByAll(Pageable pageable) {
		if (pageable.getSort() != null) {
			String order = StringUtils.collectionToCommaDelimitedString(
						StreamSupport.stream(pageable.getSort().spliterator(), false)
						.map(o -> o.getProperty() + " " + o.getDirection())
						.collect(Collectors.toList()));

			Query query = em.createQuery(String.format("SELECT project FROM Project project ORDER BY %s", order));
			query.setMaxResults(pageable.getPageSize());
			query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
			return query.getResultList();
			
		} else {
			
			Query query = em.createQuery(String.format("SELECT project FROM Project project"));
			query.setMaxResults(pageable.getPageSize());
			query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
			return query.getResultList();
		}
	}

	@Override
	public long count() {
		Query query = em.createQuery("SELECT project FROM Project project");
		return (query.getResultList().size());
	}

}

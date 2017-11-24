/**
 * 
 */
package ve.com.gem.services.implementations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import ve.com.gem.entities.HasId;
import ve.com.gem.services.IService;

/**
 * @author Carlos Gomez
 *
 */
@Transactional(readOnly=true)
public class BasicService<T extends HasId<ID>, ID extends Serializable, S extends PagingAndSortingRepository<T, ID>> implements IService<T, ID> {
	
	@Autowired
	S repository;

	
	public BasicService() {
		// TODO Auto-generated constructor stub
	}

	@Transactional(readOnly=false)
	@Override
	public T save(T object) {
		repository.save(object);
		return object;
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		List<T> objects = new ArrayList<T>();
		objects = Lists.newArrayList(repository.findAll(pageable));
		PageImpl<T> pages= new PageImpl<T>(objects, pageable, repository.count());
		return pages;
	}

	@Override
	public T findById(ID id) {
		
		return repository.findOne(id);
	}

	@Transactional(readOnly=false)
	@Override
	public boolean delete(T object) {
		ID id = object.getId();
		repository.delete(object);
		return repository.exists(id);
	}

}

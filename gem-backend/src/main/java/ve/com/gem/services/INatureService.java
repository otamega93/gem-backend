package ve.com.gem.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ve.com.gem.entities.Nature;

public interface INatureService {
	
	boolean delete(Nature object);

	void addByName(String name);

	List<Nature> search(String key);

	Nature findById(Long id);

	Page<Nature> findAll(Sort sort);

	Page<Nature> findAll(Pageable pageable);

	Nature save(Nature object);
	
	
	

}

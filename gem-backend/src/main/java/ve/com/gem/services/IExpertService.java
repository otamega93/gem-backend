package ve.com.gem.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ve.com.gem.entities.Expert;

public interface IExpertService {
	 
	 Page<Expert> findAll(Pageable pageable);
	 Page<Expert> findAll(Sort sort);
	 List<Expert> search(String key);
	 void addByName(String name);
	 Expert save (Expert object);
	 Expert findById(Long id);
	boolean delete(Expert object);
	 
}

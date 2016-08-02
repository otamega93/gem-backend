package ve.com.gem.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ve.com.gem.entities.Risk;

public interface IRiskService {
	 
	 Page<Risk> findAll(Pageable pageable);
	 Page<Risk> findAll(Sort sort);
	 List<Risk> search(String key);
	 void addByName(String name);
	 Risk save (Risk object);
	 Risk findById(Long id);
	boolean delete(Risk object);
	 
}

package ve.com.gem.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ve.com.gem.entities.Department;

public interface IDepartmentService {
	 
	 Page<Department> findAll(Pageable pageable);
	 Page<Department> findAll(Sort sort);
	 List<Department> search(String key);
	 void addByName(String name);
	 Department save (Department object);
	 Department findById(Long id);
	boolean delete(Department object);
	 
}

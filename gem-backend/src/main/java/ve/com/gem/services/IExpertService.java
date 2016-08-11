package ve.com.gem.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ve.com.gem.entities.Employee;

public interface IExpertService {
	 
	 Page<Employee> findAll(Pageable pageable);
	 Page<Employee> findAll(Sort sort);
	 List<Employee> search(String key);
	 void addByName(String name);
	 Employee save (Employee object);
	 Employee findById(Long id);
	boolean delete(Employee object);
	 
}

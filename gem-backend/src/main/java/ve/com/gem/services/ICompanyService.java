package ve.com.gem.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ve.com.gem.entities.Company;

public interface ICompanyService {
	 
	 Page<Company> findAll(Pageable pageable);
	 Page<Company> findAll(Sort sort);
	 List<Company> search(String key);
	 void addByName(String name);
	 Company save (Company object);
	 Company findById(Long id);
	boolean delete(Company object);
	 
}

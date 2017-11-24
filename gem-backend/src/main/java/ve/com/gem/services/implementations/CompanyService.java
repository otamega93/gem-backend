package ve.com.gem.services.implementations;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import ve.com.gem.entities.Company;
import ve.com.gem.repositories.ICompanyRepository;
import ve.com.gem.services.ICompanyService;

@Transactional(readOnly=true)
@Service
public class CompanyService implements ICompanyService {
	
	@Autowired
	ICompanyRepository repository;
    
    
	public CompanyService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Page<Company> findAll(Pageable pageable) {
		
		List<Company> objects = new ArrayList<Company>();
		objects = Lists.newArrayList(repository.findAll(pageable));
		PageImpl<Company> pages= new PageImpl<>(objects,pageable,repository.count());
		
	return pages;
	}
	

	@Override
	public List<Company> search(String key) {
		return repository.findByNameLike("%"+key+"%");
	}

	@Override
	public void addByName(String name) {
		// TODO Auto-generated method stub
	}

	@Transactional(readOnly=false)
	@Override
	public Company save(Company object) {
		if(null != object)
		{
			if(null == object.getCreatedAt())
				object.setCreatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
			repository.save(object);
		}
		
		return object;
	}

	@Override
	public Company findById(Long id) {
		Company object = repository.findOne(id);
		return object;
	}

	@Override
	public Page<Company> findAll(Sort sort) {
		 PageImpl<Company> pages= new PageImpl<Company>(Lists.newArrayList(repository.findAll(sort)));
		 return pages;
	}

	@Override
	@Transactional(readOnly=false)
	public boolean delete(Company object) {
		Long id=0L;
		if(null != object){
			System.out.println("No es nula.");
			id=object.getId();
		}
		repository.delete(id);
		System.out.println(repository.exists(id));
		return !repository.exists(id);
	}
}

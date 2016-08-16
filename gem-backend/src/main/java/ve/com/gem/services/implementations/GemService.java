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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import ve.com.gem.entities.Gem;
import ve.com.gem.repositories.IGemRepository;
import ve.com.gem.services.IGemService;

@Transactional(readOnly=true)
@Service
public class GemService implements IGemService {
	
	@Autowired
	IGemRepository repository;
    List<Gem> objects = new ArrayList<Gem>();
    
	public GemService() {
		// TODO Auto-generated constructor stub
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@Override
	public Page<Gem> findAll(Pageable pageable) {
		
		objects = Lists.newArrayList(repository.findAll(pageable));
		PageImpl<Gem> pages= new PageImpl<>(objects,pageable,repository.count());
		
	return pages;
	}
	

	@Override
	public List<Gem> search(String key) {
		return repository.findByNameLike("%"+key+"%");
	}

	@Override
	public void addByName(String name) {
		// TODO Auto-generated method stub
	}

	@Transactional(readOnly=false)
	@Override
	public Gem save(Gem object) {
		if(null != object)
		{
			if(null == object.getCreatedAt())
				object.setCreatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
			repository.save(object);
		}
		
		return object;
	}

	@Override
	public Gem findById(Long id) {
		Gem object = repository.findOne(id);
		return object;
	}

	@Override
	public Page<Gem> findAll(Sort sort) {
		 PageImpl<Gem> pages= new PageImpl<Gem>(Lists.newArrayList(repository.findAll(sort)));
		 return pages;
	}

	@Override
	@Transactional(readOnly=false)
	public boolean delete(Gem object) {
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

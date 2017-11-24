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
import ve.com.gem.entities.Indicator;
import ve.com.gem.repositories.IIndicatorRepository;
import ve.com.gem.services.IIndicatorService;

@Transactional(readOnly=true)
@Service
public class IndicatorService implements IIndicatorService {
	
	@Autowired
	IIndicatorRepository repository;
	
    
	public IndicatorService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Page<Indicator> findAll(Pageable pageable) {
		
		List<Indicator> objects = new ArrayList<Indicator>();
		objects = Lists.newArrayList(repository.findAll(pageable));
		PageImpl<Indicator> pages= new PageImpl<>(objects,pageable,repository.count());
		
	return pages;
	}
	

	@Override
	public List<Indicator> search(String key) {
		return repository.findByNameLike("%"+key+"%");
	}

	@Override
	public void addByName(String name) {
		// TODO Auto-generated method stub
	}

	@Transactional(readOnly=false)
	@Override
	public Indicator save(Indicator object) {
		if(null != object)
		{
			if(null == object.getCreatedAt())
				object.setCreatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
			repository.save(object);
		}
		
		return object;
	}

	@Override
	public Indicator findById(Long id) {
		Indicator object = repository.findOne(id);
		return object;
	}

	@Override
	public Page<Indicator> findAll(Sort sort) {
		 PageImpl<Indicator> pages= new PageImpl<Indicator>(Lists.newArrayList(repository.findAll(sort)));
		 return pages;
	}

	@Override
	@Transactional(readOnly=false)
	public boolean delete(Indicator object) {
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

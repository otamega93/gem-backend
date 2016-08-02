package ve.com.gem.services.implementations;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Guava;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

//import ve.com.gem.entities.Gem;
import ve.com.gem.entities.Phase;
//import ve.com.gem.repositories.IGemRepository;
import ve.com.gem.repositories.IPhaseRepository;
import ve.com.gem.resources.DocumentStateResource;
import ve.com.gem.resources.TaskResource;
//import ve.com.gem.services.IGemService;
import ve.com.gem.services.IPhaseService;

@Transactional(readOnly=true)
@Service
public class PhaseService implements IPhaseService {
	
	@Autowired
	IPhaseRepository phaseRepository;
    List<Phase> phases = new ArrayList<Phase>();
    
	public PhaseService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Page<Phase> findAll(Pageable pageable) {
		
		phases = Lists.newArrayList(phaseRepository.findAll(pageable));
		PageImpl<Phase> pages= new PageImpl<>(phases,pageable,phaseRepository.count());
		
	return pages;
	}
	

	@Override
	public List<Phase> search(String key) {
		return phaseRepository.findByNameLike("%"+key+"%");
	}

	@Override
	public void addByName(String name) {
		// TODO Auto-generated method stub
	}

	@Transactional(readOnly=false)
	@Override
	public Phase save(Phase phase) {
		if(null != phase)
		{
			if(null == phase.getCreatedAt())
				phase.setCreatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
			phaseRepository.save(phase);
		}
		
		return phase;
	}

	@Override
	public Phase findById(Long id) {
		Phase phase = phaseRepository.findOne(id);
		return phase;
	}

	@Override
	public Page<Phase> findAll(Sort sort) {
		 PageImpl<Phase> pages= new PageImpl<Phase>(Lists.newArrayList(phaseRepository.findAll(sort)));
		 return pages;
	}

	@Override
	@Transactional(readOnly=false)
	public boolean delete(Phase phase) {
		Long id=0L;
		if(null != phase){
			System.out.println("No es nula.");
			id=phase.getId();
		}
		phaseRepository.delete(id);
		System.out.println(phaseRepository.exists(id));
		return !phaseRepository.exists(id);
	}

	@Override
	public List<TaskResource> findTaskFromPhase(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentStateResource findDocumentStateFromPhaseId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}

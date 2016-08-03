package ve.com.gem.services.implementations;

import org.springframework.stereotype.Service;

import ve.com.gem.entities.RiskType;
import ve.com.gem.repositories.IRiskTypeRepository;
import ve.com.gem.services.IRiskTypeService;
@Service
public class RiskTypeService extends BasicService<RiskType, Long, IRiskTypeRepository> implements IRiskTypeService{

	public RiskTypeService() {
		// TODO Auto-generated constructor stub
	}

}

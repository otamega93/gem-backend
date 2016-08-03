package ve.com.gem.services.implementations;

import org.springframework.stereotype.Service;

import ve.com.gem.entities.Risk;
import ve.com.gem.repositories.IRiskRepository;
import ve.com.gem.services.IRiskService;

@Service
public class RiskService extends BasicService<Risk, Long, IRiskRepository> implements IRiskService{

	public RiskService() {
		// TODO Auto-generated constructor stub
	}

}

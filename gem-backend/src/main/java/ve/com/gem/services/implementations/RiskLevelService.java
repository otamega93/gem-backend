package ve.com.gem.services.implementations;

import org.springframework.stereotype.Service;

import ve.com.gem.entities.RiskLevel;
import ve.com.gem.repositories.IRiskLevelRepository;
import ve.com.gem.services.IRiskLevelService;
@Service
public class RiskLevelService extends BasicService<RiskLevel,Long, IRiskLevelRepository> implements IRiskLevelService{

	public RiskLevelService() {
		// TODO Auto-generated constructor stub
	}

}

package sport.totalizator.service.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.LeagueDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.factory.DAOFactory;
import sport.totalizator.entity.League;
import sport.totalizator.exception.LeagueException;
import sport.totalizator.service.LeagueService;
import sport.totalizator.service.exception.ServiceException;

import java.util.List;

public class LeagueServiceImpl implements LeagueService {
    private static final Logger log = Logger.getLogger(LeagueServiceImpl.class);
    private static final LeagueServiceImpl instance = new LeagueServiceImpl();
    private LeagueDAO leagueDAO;

    public static LeagueServiceImpl getInstance(){
        return instance;
    }

    LeagueServiceImpl(){
        leagueDAO = DAOFactory.getFactory().getLeagueDAO();
    }

    @Override
    public List<League> getLeaguesByCategory(int categoryId) throws ServiceException {
        try {
            return leagueDAO.getLeaguesByCategory(categoryId);
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public League addLeague(String name, String categoryId) throws ServiceException, LeagueException {
        League league = new League();
        LeagueException leagueException = new LeagueException(league);
        if(name.isEmpty() || (name == null)){
            leagueException.addMessage("err.name-is-invalid");
        }
        league.setName(name);
        int intCategoryId = 0;
        try{
            intCategoryId = Integer.parseInt(categoryId);
        } catch (NumberFormatException exc){
            log.error(exc);
        }
        league.setCategoryId(intCategoryId);
        if(leagueException.getErrorMessageList().size() > 0){
            throw leagueException;
        }
        try {
            return leagueDAO.addLeague(league);
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }
}

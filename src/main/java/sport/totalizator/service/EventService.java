package sport.totalizator.service;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.Event;
import sport.totalizator.exception.EventException;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.util.PaginationObject;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface EventService {
    PaginationObject<Event> getAllNotEndedEvents(int page) throws ServiceException;

    PaginationObject<Event> getAllNotEndedEventsSortedByDate(int page) throws ServiceException;

    PaginationObject<Event> getAllNotEndedEventsByCategoryId(String categoryId, int page) throws ServiceException;

    PaginationObject<Event> getAllEndedEvents(int page) throws ServiceException;

    Event getEventById(int eventId) throws ServiceException;

    Event addEvent(String name, String leagueId, String rateTypes, String liveTranslationLink,
                          String date, List<Integer> memberIds)
            throws ServiceException, EventException;
}

package com.ffs.api.domain.service;

import com.ffs.api.domain.exception.EntityInUseException;
import com.ffs.api.domain.exception.EntityNotFoundException;
import com.ffs.api.domain.exception.StateNotFoundException;
import com.ffs.api.domain.model.City;
import com.ffs.api.domain.repository.CityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author francisco
 */
@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateService stateService;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City findById(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new EntityNotFoundException(cityId, City.class));
    }

    public City save(final City city) throws StateNotFoundException {
        final var state = stateService.findById(city.getState().getId());

        city.setState(state);
        return cityRepository.save(city);
    }

    public void delete(final Long cityId) throws EntityInUseException, EntityNotFoundException {
        try {
            cityRepository.deleteById(cityId);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(cityId, City.class);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(cityId, City.class);
        }
    }
}

package com.ffs.algafood.domain.service;

import com.ffs.algafood.domain.exception.CityNotFoundException;
import com.ffs.algafood.domain.exception.base.EntityInUseException;
import com.ffs.algafood.domain.exception.StateNotFoundException;
import com.ffs.algafood.domain.model.City;
import com.ffs.algafood.domain.repository.CityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public City findById(Long cityId) throws CityNotFoundException {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException("id", cityId));
    }

    @Transactional
    public City save(final City city) throws StateNotFoundException {
        final var state = stateService.findById(city.getState().getId());

        city.setState(state);
        return cityRepository.save(city);
    }

    @Transactional
    public void delete(final Long cityId) throws EntityInUseException, CityNotFoundException {
        try {
            cityRepository.deleteById(cityId);
        } catch (EmptyResultDataAccessException ex) {
            throw new CityNotFoundException("id", cityId);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(cityId, City.class);
        }
    }
}

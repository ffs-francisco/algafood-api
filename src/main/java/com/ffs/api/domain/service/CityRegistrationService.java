package com.ffs.api.domain.service;

import com.ffs.api.domain.exception.EntityNotFoundException;
import com.ffs.api.domain.model.City;
import com.ffs.api.domain.repository.CityRepository;
import com.ffs.api.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Service
public class CityRegistrationService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Transactional
    public City save(final City city) throws EntityNotFoundException {
        final var stateId = city.getState().getId();

        final var state = stateRepository.findById(stateId);
        if (state == null) {
            throw new EntityNotFoundException(
                    String.format("Não existe um cadastro de estado com o código %d", stateId));
        }

        city.setState(state);
        return cityRepository.save(city);
    }
}

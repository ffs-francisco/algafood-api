package com.ffs.api.domain.service;

import com.ffs.api.domain.exception.EntityInUseException;
import com.ffs.api.domain.exception.EntityNotFoundException;
import com.ffs.api.domain.model.City;
import com.ffs.api.domain.repository.CityRepository;
import com.ffs.api.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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

    public City save(final City city) throws EntityNotFoundException {
        final var stateId = city.getState().getId();

        final var state = stateRepository.findById(stateId)
                .orElseThrow(() -> new EntityNotFoundException(
                String.format("Não existe um cadastro de estado com o código %d", stateId)));

        city.setState(state);
        return cityRepository.save(city);
    }

    public void delete(final Long cityId) throws EntityInUseException, EntityNotFoundException {
        try {
            cityRepository.deleteById(cityId);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(
                    String.format("Não exsiste um cadastro de cidade com código %d", cityId));
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(
                    String.format("Cidade de código %d não pode ser removida, pois já está em uso", cityId));
        }
    }
}

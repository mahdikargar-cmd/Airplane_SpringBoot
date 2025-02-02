package com.Airplane.AirplaneApp.Service;

import com.Airplane.AirplaneApp.Entity.City;
import com.Airplane.AirplaneApp.Repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Transactional
    public City addCity(City city) {
        if (cityRepository.existsByName(city.getName())) {
            throw new IllegalArgumentException("City with this name already exists");
        }

        if (cityRepository.existsByCode(city.getCode())) {
            throw new IllegalArgumentException("City with this code already exists");
        }
        return cityRepository.save(city);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}

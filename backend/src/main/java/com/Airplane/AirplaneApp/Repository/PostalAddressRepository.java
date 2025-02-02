package com.Airplane.AirplaneApp.Repository;

import com.Airplane.AirplaneApp.Entity.PostalAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostalAddressRepository extends JpaRepository<PostalAddress, Long> {
}

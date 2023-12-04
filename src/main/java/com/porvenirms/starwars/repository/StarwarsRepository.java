package com.porvenirms.starwars.repository;

import com.porvenirms.starwars.models.entities.StarwarsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarwarsRepository extends JpaRepository<StarwarsEntity, Integer> {
}

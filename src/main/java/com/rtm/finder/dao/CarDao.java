package com.rtm.finder.dao;

import com.rtm.finder.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDao extends JpaRepository<City, Long> {
}

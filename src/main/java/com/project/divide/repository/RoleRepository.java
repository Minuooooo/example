package com.project.divide.repository;

import com.project.divide.domain.Repeat;
import com.project.divide.domain.Role;
import com.project.divide.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select re from Repeat re")
    public List<Repeat> findAllRepeat();

    @Query("select s from Schedule s")
    public List<Schedule> findAllSchedule();


}

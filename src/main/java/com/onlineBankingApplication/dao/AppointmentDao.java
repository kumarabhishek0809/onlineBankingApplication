package com.onlineBankingApplication.dao;

import org.springframework.data.repository.CrudRepository;

import com.onlineBankingApplication.domain.Appointment;

public interface AppointmentDao extends CrudRepository<Appointment, Long> {

}

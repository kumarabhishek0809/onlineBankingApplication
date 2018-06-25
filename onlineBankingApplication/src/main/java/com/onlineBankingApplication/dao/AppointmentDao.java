package com.onlineBankingApplication.dao;

import org.springframework.data.repository.CrudRepository;

import com.onlineBankingApplication.entity.Appointment;

public interface AppointmentDao extends CrudRepository<Appointment, Long> {

}

package com.onlineBankingApplication.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineBankingApplication.dao.AppointmentDao;
import com.onlineBankingApplication.entity.Appointment;
import com.onlineBankingApplication.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentDao appointmentDao;

	@Override
	public Appointment createAppointment(Appointment appointment) {
		return appointmentDao.save(appointment);

	}

	public Iterable<Appointment> findAll() {
		return appointmentDao.findAll();
	}

	public Appointment findAppointment(Long id) {
		Optional<Appointment> findById = appointmentDao.findById(id);
		return findById.isPresent() ? findById.get() : null;
	}

	public void confirmAppointment(Long id) {
		Appointment appointment = findAppointment(id);
		appointment.setConfirmed(true);
		appointmentDao.save(appointment);
	}

}

package com.github.hibernate_problem.service.impl

import com.github.hibernate_problem.entity.Doctor
import com.github.hibernate_problem.repository.DoctorRepository
import com.github.hibernate_problem.service.DoctorService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


/**
 * @author Raman Haurylau
 */
@Service
@Transactional
class DoctorServiceImpl(val doctorRepository: DoctorRepository) : DoctorService {
    override fun findByNameContaining(userName: String): List<Doctor> {
        return doctorRepository.findByNameContaining(userName)
    }

    override fun findByIdWithoutEntityGraph(id: Long): Doctor {
        return doctorRepository.findByIdWithoutEntityGraph(id)
    }
}
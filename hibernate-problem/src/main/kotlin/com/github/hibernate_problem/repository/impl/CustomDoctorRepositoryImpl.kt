package com.github.hibernate_problem.repository.impl

import com.github.hibernate_problem.entity.Doctor
import com.github.hibernate_problem.repository.CustomDoctorRepository
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext


/**
 * @author Raman Haurylau
 */
class CustomDoctorRepositoryImpl(@PersistenceContext val em: EntityManager) : CustomDoctorRepository {

    override fun findByIdWithoutEntityGraph(id: Long): Doctor {
        return em.find(Doctor::class.java, id)
    }
}
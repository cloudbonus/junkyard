package com.github.hibernate_problem.repository

import com.github.hibernate_problem.entity.Doctor



/**
 * @author Raman Haurylau
 */
interface CustomDoctorRepository {
    fun findByIdWithoutEntityGraph(id: Long): Doctor
}
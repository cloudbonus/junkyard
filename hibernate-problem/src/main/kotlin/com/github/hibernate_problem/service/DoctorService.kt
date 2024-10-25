package com.github.hibernate_problem.service

import com.github.hibernate_problem.entity.Doctor

/**
 * @author Raman Haurylau
 */
interface DoctorService {
    fun findByNameContaining(userName: String): List<Doctor>
    fun findByIdWithoutEntityGraph(id: Long): Doctor
}
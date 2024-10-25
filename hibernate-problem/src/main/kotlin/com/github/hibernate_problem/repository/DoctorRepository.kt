package com.github.hibernate_problem.repository

import com.github.hibernate_problem.entity.Doctor
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Raman Haurylau
 */
interface DoctorRepository : JpaRepository<Doctor, Long>, CustomDoctorRepository{
    @EntityGraph(attributePaths = ["appointments"], type = EntityGraph.EntityGraphType.LOAD)
    fun findByNameContaining(name: String): List<Doctor>
}
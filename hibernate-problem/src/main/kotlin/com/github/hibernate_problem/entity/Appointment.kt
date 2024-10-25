package com.github.hibernate_problem.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

/**
 * @author Raman Haurylau
 */
@Entity
class Appointment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    //@ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    val doctor: Doctor
)
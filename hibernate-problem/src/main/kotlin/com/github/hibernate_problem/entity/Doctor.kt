package com.github.hibernate_problem.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

/**
 * @author Raman Haurylau
 */
@Entity
class Doctor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    //@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "doctor", cascade = [CascadeType.ALL])
    //@Fetch(FetchMode.SUBSELECT)
    //@Fetch(FetchMode.JOIN)
    val patiens: Set<Patient>,
    //@Fetch(FetchMode.SUBSELECT)
    //@Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "doctor", cascade = [CascadeType.ALL])
    val appointments: Set<Appointment>,
)
package com.github.proxy.repository

import com.github.proxy.entity.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author Raman Haurylau
 */
@Repository
interface UserRepository : JpaRepository<Person, Long>
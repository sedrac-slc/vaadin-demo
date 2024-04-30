package com.ei.school.backend.repository

import com.ei.school.backend.model.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ContactRepository: JpaRepository<Contact, String> {
    fun findByEmail(email: String): Optional<Contact>
    fun findByFirstNameContaining(firstName: String): List<Contact>
}
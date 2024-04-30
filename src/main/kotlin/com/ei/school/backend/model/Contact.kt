package com.ei.school.backend.model

import com.ei.school.backend.enums.StatusEnum
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "tb_contacts")
data class Contact(
    @NotNull var firstName: String,
    @NotNull var lastName: String,
    @NotNull @Email @Column(unique = true) var email: String,
    @NotNull @Enumerated(EnumType.STRING) var status: StatusEnum,
    @ManyToOne @JoinColumn(name = "company_id") var company: Company
): ModelCommon(){
}
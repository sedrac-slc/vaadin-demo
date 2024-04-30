package com.ei.school.backend.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "tb_companies")
class Company(
    @NotNull @Column(unique = true) var name: String,
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY) var contacts: MutableList<Contact> = mutableListOf()
): ModelCommon() {

    constructor(name: String): this(name, mutableListOf())
}
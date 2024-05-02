package com.ei.vaadin.backend.model

import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.UuidGenerator
import java.io.Serializable

@MappedSuperclass
abstract class ModelCommon(@Id @UuidGenerator var id: String = ""): Serializable
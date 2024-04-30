package com.ei.school.ui

import com.ei.school.backend.enums.StatusEnum
import com.ei.school.backend.model.Company
import com.vaadin.flow.component.Key
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.combobox.ComboBox
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.textfield.EmailField
import com.vaadin.flow.component.textfield.TextField

class ContactForm : FormLayout() {

    private val firstName: TextField = TextField()
    private val lastName: TextField = TextField()
    private val email: EmailField = EmailField()
    private val status = ComboBox<StatusEnum>("Status")
    private val company = ComboBox<Company>("Company")

    private val save: Button = Button("Save")
    private val close: Button = Button("Close")
    private val delete: Button = Button("Delete")

    init {
        addClassName("contact-form")
        add(firstName, lastName, email, status, company, createButtonsLayout())
    }

    private fun createButtonsLayout(): HorizontalLayout {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY)
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR)

        save.addClickShortcut(Key.ENTER)
        close.addClickShortcut(Key.ESCAPE)

        return HorizontalLayout(save, delete, close)
    }

}
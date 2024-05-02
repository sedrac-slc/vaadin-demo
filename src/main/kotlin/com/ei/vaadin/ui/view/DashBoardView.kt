package com.ei.vaadin.ui.view

import com.ei.vaadin.backend.service.CompanyService
import com.ei.vaadin.backend.service.ContactService
import com.ei.vaadin.ui.MainView
import com.vaadin.flow.component.html.Span
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import com.vaadin.flow.theme.lumo.LumoUtility

@PageTitle("Dashboard | Vaadin CRM")
@Route(value = "dashboard", layout = MainView::class)
class DashBoardView(
    private val contactService: ContactService,
    private val companyService: CompanyService
) : VerticalLayout() {

    init {
        addClassName("dashboard-view")
        setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER)
        add(getContactStats())
    }

    private fun getContactStats(): Span {
        val stats = Span("${contactService.count()} contacts")
        stats.addClassNames(  LumoUtility.FontSize.MEDIUM, LumoUtility.Margin.Top.MEDIUM )
        return stats
    }

}
package com.ei.vaadin.ui

import com.ei.vaadin.ui.view.ListView
import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.applayout.DrawerToggle
import com.vaadin.flow.component.dependency.StyleSheet
import com.vaadin.flow.component.html.H1
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.RouterLink
import com.vaadin.flow.theme.lumo.LumoUtility

@StyleSheet("./css/style.css")
class MainView: AppLayout() {

    init {
        createHeader()
        createDrawer()
    }

    fun createHeader(){
        val logo = H1("Vaadin CRM")
        logo.addClassNames(LumoUtility.FontSize.MEDIUM, LumoUtility.Margin.MEDIUM)

        val header = HorizontalLayout(DrawerToggle(), logo)
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER)
        header.setWidthFull()
        header.addClassNames( LumoUtility.Padding.Vertical.NONE, LumoUtility.Padding.Horizontal.MEDIUM)
        addToNavbar(header)
    }

    private fun createDrawer() {
        addToDrawer( VerticalLayout( RouterLink("List", ListView::class.java) ) )
    }

}
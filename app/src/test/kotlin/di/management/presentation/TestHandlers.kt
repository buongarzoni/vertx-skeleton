package di.management.presentation

import di.management.actions.Actions
import management.ManagementHandler

object TestHandlers {
    val managementHandler: ManagementHandler by lazy { ManagementHandler(Actions.createRestaurant) }
}

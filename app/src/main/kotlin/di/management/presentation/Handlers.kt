package di.management.presentation

import di.management.actions.Actions
import management.ManagementHandler

object Handlers {
    val managementHandler: ManagementHandler by lazy { ManagementHandler(Actions.createRestaurant) }
}

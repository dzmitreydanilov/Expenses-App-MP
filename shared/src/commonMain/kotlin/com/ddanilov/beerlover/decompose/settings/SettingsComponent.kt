package com.ddanilov.beerlover.decompose.settings

import com.expenses.core.decompose.AppComponentContext

class SettingsComponent(
    componentContext: AppComponentContext
) : Settings, AppComponentContext by componentContext
package com.example.wellnessapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Tip(
    @DrawableRes val imageRes: Int,
    @StringRes val dayRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int

)


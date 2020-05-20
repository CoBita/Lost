@file:Suppress("ClassName", "unused", "NonAsciiCharacters")

package com.bita.lost.repo.data

import androidx.annotation.DrawableRes

data class AcquireData(val name: String,
                       val code: String,
                       @DrawableRes
                       val icon: Int)
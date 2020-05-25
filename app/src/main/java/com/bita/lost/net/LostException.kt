package com.bita.lost.net

class LostException(val code: String, override val message: String) : Exception(message) {
    companion object {
        private const val serialVersionUID: Long = 123
    }
}
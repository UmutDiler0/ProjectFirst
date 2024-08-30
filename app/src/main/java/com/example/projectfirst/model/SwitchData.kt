package com.example.projectfirst.model

object SwitchData {
    fun loadSwitches(): List<SwitchElements> {
        return listOf<SwitchElements>(
            SwitchElements(
                name = "Ego",
                isChecked = false
            ),
            SwitchElements(
                name = "Hapiness",
                isChecked = false
            ),
            SwitchElements(
                name = "Optimism",
                isChecked = false
            ),
        )
    }

    fun loadSwitches2(): List<SwitchElements> {
        return listOf(
            SwitchElements(
                name = "Respect",
                isChecked = false
            ),
            SwitchElements(
                name = "Giving",
                isChecked = false
            ),
            SwitchElements(
                name = "Kindness",
                isChecked = false
            )
        )
    }
}

data class SwitchElements(
    val name: String,
    val isChecked: Boolean
)
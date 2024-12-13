package com.github.ringoame196_s_mcPlugin.managers

import com.github.ringoame196_s_mcPlugin.Data

object DataManager {
    fun registerTableFileName(fileName: String) {
        Data.tableFileName = fileName
    }
}

package com.github.ringoame196_s_mcPlugin.managers

import com.github.ringoame196_s_mcPlugin.Data
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Statement

object DataBaseManager {
    fun runSQLCommand(command: String, parameters: List<Any>? = null) {
        val statement: Statement?
        var connection: Connection? = null
        val preparedStatement: PreparedStatement?
        try {
            statement = connection() // 接続
            connection = statement?.connection
            preparedStatement = connection?.prepareStatement(command)

            // パラメータをバインド
            parameters?.forEachIndexed { index, param ->
                preparedStatement?.setObject(index + 1, param)
            }

            preparedStatement?.executeUpdate()
        } catch (e: SQLException) {
            // エラーハンドリング
            println("SQL Error: ${e.message}")
        } finally {
            disconnect(connection) // 切断
        }
    }

    fun acquisitionStringInt(sql: String, parameters: List<Any>, label: String): Int? {
        var value: Int? = null
        val statement: Statement?
        var connection: Connection? = null
        val preparedStatement: PreparedStatement?
        try {
            statement = connection() // 接続
            connection = statement?.connection
            preparedStatement = connection?.prepareStatement(sql)

            // パラメータをバインド
            parameters.forEachIndexed { index, param ->
                preparedStatement?.setObject(index + 1, param)
            }

            val resultSet = preparedStatement?.executeQuery()
            value = if (resultSet != null && resultSet.next()) {
                resultSet.getInt(label)
            } else {
                null // 結果が存在しない場合はnullを返す
            }
        } catch (e: SQLException) {
            // エラーハンドリング
            println("SQL Error: ${e.message}")
        } finally {
            disconnect(connection) // 切断
        }
        return value
    }

    fun connection(): Statement? {
        val connection = DriverManager.getConnection("jdbc:sqlite:${Data.tableFileName ?: return null}")
        // SQLステートメントの作成
        val statement = connection.createStatement()
        statement.queryTimeout = 30 // タイムアウトの設定
        return statement
    }

    private fun disconnect(connection: Connection?) {
        connection?.close()
    }
}

package com.donxux.codate.utils

object GistsHtmlUtils {
    fun getHtmlCode(scriptCode: String): String =
        "<!DOCTYPE html>" +
            "<html lang=\"en\">" +
            "<head><meta charset=\"utf-8\"></head>" +
            "<body style='margin:0; padding:0; background-color: white;'>" +
            scriptCode +
            "</body>" +
            "</html>"
}

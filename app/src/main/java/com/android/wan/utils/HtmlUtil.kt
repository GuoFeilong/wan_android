package com.android.wan.utils

import java.util.regex.Pattern

/**
 * @author  by 有人@我 on 18/3/5.
 */

object HtmlUtil {
    fun htmlRemoveTag(inputString: String?): String? {
        if (inputString == null) {
            return null
        }
        var htmlStr: String = inputString // 含html标签的字符串
        var textStr = ""
        val p_script: java.util.regex.Pattern
        val m_script: java.util.regex.Matcher
        val p_style: java.util.regex.Pattern
        val m_style: java.util.regex.Matcher
        val p_html: java.util.regex.Pattern
        val m_html: java.util.regex.Matcher
        try {
            //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            val regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"
            //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            val regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"
            val regEx_html = "<[^>]+>" // 定义HTML标签的正则表达式
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE)
            m_script = p_script.matcher(htmlStr)
            htmlStr = m_script.replaceAll("") // 过滤script标签
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE)
            m_style = p_style.matcher(htmlStr)
            htmlStr = m_style.replaceAll("") // 过滤style标签
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE)
            m_html = p_html.matcher(htmlStr)
            htmlStr = m_html.replaceAll("") // 过滤html标签
            textStr = htmlStr
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return textStr// 返回文本字符串
    }
}

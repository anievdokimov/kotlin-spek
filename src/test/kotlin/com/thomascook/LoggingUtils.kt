package com.thomascook

import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import kotlin.reflect.full.companionObject


inline fun <T : Any> getLogger(javaClass: Class<T>): Logger {
    return getLogger(javaClass.enclosingClass?.takeIf {
        it.kotlin.companionObject?.java == javaClass
    } ?: javaClass)
}

package com.wtt.commonlibtemplatemenu

data class TemplateDefinition(
    val name: String,
    val resourcePath: String,
    val type: ETemplateType = ETemplateType.DOCUMENT
)
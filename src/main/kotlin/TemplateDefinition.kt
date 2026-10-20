package com.wtt.commonlibtemplatesmenu

data class TemplateDefinition(
    val name: String,
    val resourcePath: String,
    val type: ETemplateType = ETemplateType.DOCUMENT
)
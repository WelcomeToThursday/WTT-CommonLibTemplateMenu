package com.wtt.commonlibtemplatemenu

import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class TemplateMenuGroup : ActionGroup("Insert Template", true) {

    override fun getChildren(e: AnActionEvent?): Array<AnAction> {
        val actions = mutableListOf<AnAction>()

        TemplateManager.getCategories().forEach { category ->
            val templates = TemplateManager.getTemplatesForCategory(category) ?: return@forEach

            val categoryGroup = object : ActionGroup(category, true) {
                override fun getChildren(e: AnActionEvent?): Array<AnAction> {
                    return templates.map { templateName ->
                        InsertTemplateAction(category, templateName, templateName)
                    }.toTypedArray()
                }

                override fun getActionUpdateThread(): ActionUpdateThread {
                    return ActionUpdateThread.BGT
                }
            }

            actions.add(categoryGroup)
        }

        return actions.toTypedArray()
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }
}

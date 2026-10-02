package com.wtt.commonlibtemplatemenu

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project

class InsertTemplateAction(
    private val category: String,
    private val templateName: String,
    text: String
) : AnAction(text) {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return
        insertIntoEditor(project, editor, category, templateName)
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabledAndVisible = e.getData(CommonDataKeys.EDITOR) != null
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    private fun insertIntoEditor(project: Project, editor: Editor, category: String, templateName: String) {
        val template = TemplateManager.getTemplate(category, templateName) ?: return
        val document = editor.document

        WriteCommandAction.runWriteCommandAction(project) {
            val offset = editor.caretModel.offset
            document.insertString(offset, template)
            editor.caretModel.moveToOffset(offset + template.length)
        }
    }

}

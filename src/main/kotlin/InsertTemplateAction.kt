package com.wtt.commonlibtemplatemenu

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import java.io.File

class InsertTemplateAction(
    private val category: String,
    private val templateName: String,
    text: String
) : AnAction(text) {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val editor = e.getData(CommonDataKeys.EDITOR)
        val virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE)

        if (editor != null) {
            insertIntoEditor(project, editor, category, templateName)
        } else if (virtualFile != null) {
            createJsonFile(project, virtualFile, category, templateName)
        }
    }

    override fun update(e: AnActionEvent) {
        val editor = e.getData(CommonDataKeys.EDITOR)
        val virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE)
        e.presentation.isEnabledAndVisible = editor != null || (virtualFile != null && virtualFile.isDirectory)
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

    private fun createJsonFile(project: Project, directory: VirtualFile, category: String, templateName: String) {
        val template = TemplateManager.getTemplate(category, templateName) ?: return

        val fileName = Messages.showInputDialog(
            project,
            "Enter file name (without .json extension):",
            "Create Template File",
            null,
            "${category.lowercase()}_${templateName.lowercase().replace(" ", "_")}",
            null
        ) ?: return

        WriteCommandAction.runWriteCommandAction(project) {
            try {
                val sanitizedFileName = fileName.replace(Regex("[^a-zA-Z0-9_-]"), "_")
                val file = File(directory.path, "$sanitizedFileName.json")
                file.writeText(template)
                directory.refresh(false, false)

                Messages.showInfoMessage(
                    project,
                    "Template file created: ${file.name}",
                    "Success"
                )
            } catch (ex: Exception) {
                Messages.showErrorDialog(
                    project,
                    "Failed to create template file: ${ex.message}",
                    "Error"
                )
            }
        }
    }
}

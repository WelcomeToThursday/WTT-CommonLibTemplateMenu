package com.wtt.commonlibtemplatemenu

object TemplateManager {
    private val categories: LinkedHashMap<String, List<TemplateDefinition>> = linkedMapOf(
        "Achievements" to listOf(
            TemplateDefinition(
                name = "Achievement Config",
                resourcePath = "/templates/achievement/custom_achievement_config.jsonc"
            )
        ),

        "Bots" to listOf(
            TemplateDefinition(
                name = "Bot Loadout Config",
                resourcePath = "/templates/bot/custom_bot_loadout_config.jsonc"
            )
        ),

        "Character" to listOf(
            TemplateDefinition(
                name = "Head Config",
                resourcePath = "/templates/character/custom_head_config.jsonc",
                type = ETemplateType.OBJECT_CONTENT
            ),
            TemplateDefinition(
                name = "Voice Config",
                resourcePath = "/templates/character/custom_voice_config.jsonc",
                type = ETemplateType.OBJECT_CONTENT
            )
        ),

        "Clothing" to listOf(
            TemplateDefinition(
                name = "Clothing Top Config",
                resourcePath = "/templates/clothing/custom_clothing_top_config.jsonc"
            ),
            TemplateDefinition(
                name = "Clothing Bottom Config",
                resourcePath = "/templates/clothing/custom_clothing_bottom_config.jsonc"
            )
        ),

        "Dialogue" to listOf(
            TemplateDefinition(
                name = "Dialogue Config",
                resourcePath = "/templates/dialogue/custom_dialogue_config.jsonc"
            )
        ),

        "Effects" to listOf(
            TemplateDefinition(
                name = "Buff Config",
                resourcePath = "/templates/effect/custom_buff_config.jsonc"
            )
        ),

        "Hideout" to listOf(
            TemplateDefinition(
                name = "Customization Config",
                resourcePath = "/templates/hideout/custom_hideout_customization_config.jsonc"
            ),
            TemplateDefinition(
                name = "Customization Storage Config",
                resourcePath = "/templates/hideout/custom_hideout_customization_storage_config.jsonc"
            ),
            TemplateDefinition(
                name = "Hideout Customization Globals Config",
                resourcePath = "/templates/hideout/custom_hideout_customization_globals_config.jsonc"
            ),
            TemplateDefinition(
                name = "Extended Recipe Config",
                resourcePath = "/templates/hideout/custom_hideout_recipe_extended_config.jsonc"
            )
        ),

        "Items" to listOf(
            TemplateDefinition(
                name = "Item Config",
                resourcePath = "/templates/item/custom_item_config.jsonc",
                type = ETemplateType.OBJECT_CONTENT
            ),
            TemplateDefinition(
                name = "Item Parent Config",
                resourcePath = "/templates/item/custom_item_parent_config.jsonc",
                type = ETemplateType.OBJECT_CONTENT
            ),
            TemplateDefinition(
                name = "Weapon Preset Config",
                resourcePath = "/templates/item/custom_weapon_preset_config.jsonc"
            )
        ),

        "Locales" to listOf(
            TemplateDefinition(
                name = "Locale Config",
                resourcePath = "/templates/locale/custom_locale_config.jsonc"
            ),
            TemplateDefinition(
                name = "Achievement Locale Config",
                resourcePath = "/templates/locale/custom_achievement_locale_config.jsonc"
            ),
            TemplateDefinition(
                name = "Quest Locale Config",
                resourcePath = "/templates/locale/custom_quest_locale_config.jsonc"
            )
        ),

        "Lootspawn" to listOf(
            TemplateDefinition(
                name = "Loot Spawn Config",
                resourcePath = "/templates/lootspawn/custom_lootspawn_config.jsonc"
            )
        ),

        "Profiles" to listOf(
            TemplateDefinition(
                name = "Profile Config",
                resourcePath = "/templates/profile/custom_profile_config.jsonc"
            )
        ),

        "Quests" to listOf(
            TemplateDefinition(
                name = "Quest Assort Config",
                resourcePath = "/templates/quest/custom_quest_assort_config.jsonc"
            ),
            TemplateDefinition(
                name = "Quest Config",
                resourcePath = "/templates/quest/custom_quest_config.jsonc"
            ),
            TemplateDefinition(
                name = "Quest Item Config",
                resourcePath = "/templates/quest/custom_quest_item_config.jsonc",
                type = ETemplateType.OBJECT_CONTENT
            ),
            TemplateDefinition(
                name = "Quest Side Data Config",
                resourcePath = "/templates/quest/custom_quest_side_data_config.jsonc"
            ),
            TemplateDefinition(
                name = "Quest Time Data Config",
                resourcePath = "/templates/quest/custom_quest_time_data_config.jsonc"
            ),
            TemplateDefinition(
                name = "Quest Zone Config",
                resourcePath = "/templates/quest/custom_quest_zone_config.jsonc"
            )
        ),

        "Spawn" to listOf(
            TemplateDefinition(
                name = "Spawn Config Config",
                resourcePath = "/templates/spawn/custom_spawn_config.jsonc"
            )
        ),

        "Traders" to listOf(
            TemplateDefinition(
                name = "Assort Scheme Config",
                resourcePath = "/templates/trader/custom_assort_scheme_config.jsonc"
            )
        ),

        "World" to listOf(
            TemplateDefinition(
                name = "Static Spawn Config",
                resourcePath = "/templates/world/custom_static_spawn_config.jsonc"
            )
        )
    )

    fun getCategories(): List<String> = categories.keys.toList()

    fun getTemplatesForCategory(category: String): List<String>? = categories[category]?.map { it.name }

    fun getTemplate(category: String, templateName: String): String? {
        val definition = categories[category]?.firstOrNull { it.name == templateName } ?: return null

        val template = readTemplate(definition.resourcePath)

        return when (definition.type) {
            ETemplateType.DOCUMENT -> template
            ETemplateType.OBJECT_CONTENT -> template.unwrapRootObject()
        }
    }

    private fun readTemplate(resourcePath: String): String = TemplateManager::class.java
        .getResourceAsStream(resourcePath)?.bufferedReader()?.use {
            it.readText()
        } ?: error("Template resource not found: $resourcePath")

    private fun String.unwrapRootObject(): String {
        val trimmed = trim()

        require(trimmed.startsWith('{') && trimmed.endsWith('}')) {
            "Cannot unwrap template as it is not wrapped in a root object."
        }

        return trimmed.substring(1, trimmed.length - 1).trim()
    }
}

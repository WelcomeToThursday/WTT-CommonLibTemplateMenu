package com.wtt.commonlibtemplatemenu

object TemplateManager {
    private val categories = linkedMapOf(
        "Clothing" to mapOf(
            "Clothing Top" to createCustomClothingTop(),
            "Clothing Bottom" to createCustomClothingBottom(),
            "Head" to createCustomHead()
        ),
        "Item" to mapOf(
            "Item Config" to createCustomItemConfig()
        ),
        "Spawn" to mapOf(
            "Spawn Config" to createCustomSpawnConfig()
        ),
        "Voice" to mapOf(
            "Voice Config" to createCustomVoiceConfig()
        )
    )

    fun getCategories(): List<String> = categories.keys.toList()

    fun getTemplatesForCategory(category: String): Map<String, String>? = categories[category]

    fun getTemplate(category: String, templateName: String): String? = categories[category]?.get(templateName)

    private fun createCustomClothingTop() = """
        {
            "type": "top",
            "suiteId": "UNIQUE_ID_HERE",
            "outfitId": "UNIQUE_ID_HERE",
            "topId": "UNIQUE_ID_HERE",
            "handsId": "UNIQUE_ID_HERE",
            "bottomId": null,
            "side": ["usec", "bear"],
            "locales": {
                "en": {
                    "name": "NAME_YOUR_BOTTOM_HERE",
                    "description": "WRITE_A_DESCRIPTION_HERE"
                }
            },
            "topBundlePath": "path/to/top/bundle",
            "handsBundlePath": "path/to/hands/bundle",
            "bottomBundlePath": null,
            "traderId": "SET_ME",
            "loyaltyLevel": 2,
            "profileLevel": 15,
            "standing": 0.25,
            "currencyId": "SET_ME",
            "price": 1,
            "watchPrefab": null,
            "watchPosition": null,
            "watchRotation": null,
            "skillRequirements": [],
            "questRequirements": [],
            "achievementRequirements": []
        }
    """.trimIndent()

    private fun createCustomClothingBottom() = """
        {
            "type": "bottom",
            "suiteId": "UNIQUE_ID_HERE",
            "outfitId": "UNIQUE_ID_HERE",
            "bottomId": "UNIQUE_ID_HERE",
            "locales": {
                "en": {
                    "name": "NAME_YOUR_BOTTOM_HERE",
                    "description": "WRITE_A_DESCRIPTION_HERE"
                }
            },
            "bottomBundlePath": "path/to/bundle",
            "traderId": "SET_ME",
            "loyaltyLevel": 1,
            "profileLevel": 1,
            "standing": 0,
            "currencyId": "SET_ME",
            "price": 1,
            "questRequirements": [],
            "achievementRequirements": []
        }
    """.trimIndent()

    private fun createCustomHead() = """
        "GENERATE_UNIQUE_ID_HERE": {
            "path": "path/to/head/bundle",
            "addHeadToPlayer": true,
            "side": ["usec", "bear"],
            "locales": {
                "en": "Custom Head Model"
            }
        }
    """.trimIndent()

    private fun createCustomItemConfig() = """
        "GENERATE_UNIQUE_ID_HERE": {
            "itemTplToClone": "SET_ME_TO_ITEM_THATS_BEING_CLONED",
            "parentId": "SET_ME",
            "handbookParentId": "SET_ME",
            "overrideProperties": {
            },
            "locales": {
                "en": {
                    "name": "SET_ME",
                    "shortName": "SET_ME",
                    "description": "WRITE_A_DESCRIPTION_HERE"
                }
            },
            "fleaPriceRoubles": 0,
            "handbookPriceRoubles": 0,
            "addtoInventorySlots": [],
            "addtoModSlots": false,
            "modSlot": null,
            "addtoTraders": false,
            "traders": {
                "PUT_TRADER_ID_HERE": {
                    "PUT_UNIQUE_ITEM_ID_HERE": {
                        "barterSettings": {
                            "loyalLevel": 1,
                            "unlimitedCount": false,
                            "stackObjectsCount": 1,
                            "buyRestrictionMax": 3
                        },
                        "barters": [
                            {
                                "count": 15000,
                                "_tpl": "SET_ME",
                                "onlyFunctional": false,
                                "sptQuestLocked": false
                            }
                        ]
                    }
                }
            },
            "addtoBots": false,
            "addtoStaticLootContainers": false,
            "staticLootContainers": [
                {
                    "containerName": "weapon_box",
                    "probability": 10
                }
            ],
            "masteries": false,
            "masterySections": null,
            "addWeaponPreset": false,
            "weaponPresets": null,
            "addtoHallOfFame": false,
            "hallOfFameSlots": null,
            "addtoSpecialSlots": false,
            "addtoGeneratorAsFuel": false,
            "generatorFuelSlotStages": null,
            "addtoHideoutPosterSlots": false,
            "addPosterToMaps": false,
            "posterSpawnProbability": null,
            "addtoStatuetteSlots": false,
            "addCaliberToAllCloneLocations": false,
            "addtoStaticAmmo": false,
            "staticAmmoProbability": null,
            "addtoEmptyPropSlots": false,
            "emptyPropSlot": null,
            "addtoSecureFilters": false
        }
    """.trimIndent()


    private fun createCustomSpawnConfig() = """
        {
            "questId": "UNIQUE_ID_HERE",
            "locationID": "factory4_day",
            "bundleName": "custom_bundle",
            "prefabName": "custom_prefab",
            "position": {
                "x": 100.5,
                "y": 10.0,
                "z": 200.3
            },
            "rotation": {
                "x": 0,
                "y": 90,
                "z": 0
            },
            "requiredQuestStatuses": ["Started", "AvailableForFinish"],
            "excludedQuestStatuses": ["Success"],
            "questMustExist": false,
            "linkedQuestId": "linked_quest_001",
            "linkedRequiredStatuses": ["Success"],
            "linkedExcludedStatuses": [],
            "linkedQuestMustExist": false,
            "requiredItemInInventory": "item_id_123",
            "requiredLevel": 15,
            "requiredFaction": "usec",
            "requiredBossSpawned": "SET_ME"
        }
    """.trimIndent()

    private fun createCustomVoiceConfig() = """
        "GENERATE_UNIQUE_ID_HERE": {
            "locales": {
                "en": "Custom Voice Pack"
            },
            "name": "custom_voice_001",
            "bundlePath": "path/to/voice/bundle",
            "addVoiceToPlayer": true,
            "sideSpecificVoice": ["usec", "bear"],
            "addToBotTypes": {
                // SET YOUR BOTS HERE
            }
        }
    """.trimIndent()
}

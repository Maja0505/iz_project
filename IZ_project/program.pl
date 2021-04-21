
attack(Supply_Chain, Configuration_Environment_Manipulation).
parentOf(Configuration_Environment_Manipulation, Manipulating_Writeable_Configuration_Files).
parentOf(Configuration_Environment_Manipulation, Manipulating_Writeable_Configuration_Files).
parentOf(Configuration_Environment_Manipulation, Manipulating_Writeable_Configuration_Files).
parentOf(Configuration_Environment_Manipulation, Manipulating_Writeable_Configuration_Files).
parentOf(Configuration_Environment_Manipulation, Manipulating_Writeable_Configuration_Files).


parentOf(Configuration_Environment_Manipulation, Manipulating_Writeable_Configuration_Files).
related_weaknesses(Manipulating_Writeable_Configuration_Files,Acceptance_of_Extraneous_Untrusted_Data_With_Trusted_Data).
related_weaknesses(Manipulating_Writeable_Configuration_Files,Improper_Control_of_Resource_Identifiers).
related_weaknesses(Manipulating_Writeable_Configuration_Files,Improper_Neutralization_of_Special_Elements_used_in_a_Command).
related_weaknesses(Manipulating_Writeable_Configuration_Files,Origin_Validation_Error).
related_weaknesses(Manipulating_Writeable_Configuration_Files,Missing_Support_for_Integrity_Check).
related_weaknesses(Manipulating_Writeable_Configuration_Files,Improper_Validation_of_Integrity_Check_Value).
related_weaknesses(Manipulating_Writeable_Configuration_Files,OWASP_Top_Ten_2007_Category_A2_Injection_Flaws).
prerequisites(Manipulating_Writeable_Configuration_Files, Configuration_files_must_be_modifiable_by_the_attacker).
mitigations(Manipulating_Writeable_Configuration_Files, Design, Enforce_principle_of_least_privilege).
mitigations(Manipulating_Writeable_Configuration_Files, Design, Backup_copies_of_all_configuration_files).
mitigations(Manipulating_Writeable_Configuration_Files, Design, Backup_copies_of_all_configuration_files).
mitigations(Manipulating_Writeable_Configuration_Files, Implementation, Integrity_monitoring_for_configuration_files ).
mitigations(Manipulating_Writeable_Configuration_Files, Implementation, Enforce_audit_logging_on_code_and_configuration_promotion_procedures).
mitigations(Manipulating_Writeable_Configuration_Files, Implementation, Load_configuration_from_separate_process_and_memory_space_for_example_a_separate_physical_device_like_a_CD).

/*Modification_During_Manufacture*/
attack(Supply_Chain,Modification_During_Manufacture).
parentOf(Modification_During_Manufacture, Development_Alteration).
parentOf(Modification_During_Manufacture, Design_Alteration).

/*Development_Alteration*/
parentOf(Development_Alteration, Malicious_Logic_Inserted_Into_Product_Software_by_Authorized_Developer).
parentOf(Development_Alteration, Malicious_Logic_Insertion_into_Product_Software_via_Configuration_Management_Manipulation).
parentOf(Development_Alteration, Malicious_Logic_Insertion_into_Product_Software_via_Inclusion_of_3rd_Party_Component_Dependency).
parentOf(Development_Alteration, Infiltration_of_Software_Development_Environment).
parentOf(Development_Alteration, Hardware_Component_Substitution_During_Baselining).
parentOf(Development_Alteration, Counterfeit_Hardware_Component_Inserted_During_Product_Assembly).
parentOf(Development_Alteration, Altered_Installed_BIOS).
parentOf(Development_Alteration, Infiltration_of_Hardware_Development_Environment).
parentOf(Development_Alteration, Open_Source_Libraries_Altered).
parentOf(Development_Alteration, ASIC_With_Malicious_Functionality).

prerequisites(Development_Alteration, Access_to_the_system_during_the_development_phase_to_alter_and_or_modify_software_and_hardware_components).
mitigations(Development_Alteration, Assess_Software_and_software_components_during_development_and_prior_to_deployment_to_ensure_that_they_function_as_intended_and_without_any_malicious_functionality).

prerequisites(Malicious_Logic_Inserted_Into_Product_Software_by_Authorized_Developer, Access_to_the_software_during_the_development_phase).
mitigations(Malicious_Logic_Inserted_Into_Product_Software_by_Authorized_Developer, Assess_Software_and_software_components_during_development_and_prior_to_deployment_to_ensure_that_they_function_as_intended_and_without_any_malicious_functionality).

prerequisites(Malicious_Logic_Insertion_into_Product_Software_via_Configuration_Management_Manipulation, Access_to_the_configuration_management_system_during_deployment_or_currently_deployed_at_a_victim_location).
mitigations(Malicious_Logic_Insertion_into_Product_Software_via_Configuration_Management_Manipulation, Assess_Software_and_software_components_during_development_and_prior_to_deployment_to_ensure_that_they_function_as_intended_and_without_any_malicious_functionality).
mitigations(Malicious_Logic_Insertion_into_Product_Software_via_Configuration_Management_Manipulation, Leverage_anti-virus_products_to_detect_and_quarantine_software_with_known_virus).

prerequisites(Malicious_Logic_Insertion_into_Product_Software_via_Inclusion_of_3rd_Party_Component_Dependency, Access_to_the_software_during_the_development_phase).
mitigations(Malicious_Logic_Insertion_into_Product_Software_via_Inclusion_of_3rd_Party_Component_Dependency, Assess_Software_and_software_components_during_development_and_prior_to_deployment_to_ensure_that_they_function_as_intended_and_without_any_malicious_functionality).

prerequisites(Infiltration_of_Software_Development_Environment, The_victim_must_use_email_or_removable_media_from_systems_running_the_IDE).
prerequisites(Infiltration_of_Software_Development_Environment, The_victim_must_have_a_system_running_exploitable_applications_and_or_a_vulnerable_configuration).
prerequisites(Infiltration_of_Software_Development_Environment, The_attacker_must_have_working_knowledge_of_the_components_involved_in_IDE_and_infrastructure).

prerequisites(Hardware_Component_Substitution_During_Baselining, The_attacker_need_physical_access_or_be_able_to_supply_malicious_hardware_components_to_the_product_development_facility).

prerequisites(Counterfeit_Hardware_Component_Inserted_During_Product_Assembly, The_attacker_need_physical_access_or_be_able_to_supply_malicious_hardware_components_to_the_product_development_facility).

prerequisites(Altered_Installed_BIOS, Advanced_knowledge_about_the_installed_target_system_design).
prerequisites(Altered_Installed_BIOS, Advanced_knowledge_about_the_download_and_update_installation_processes).
prerequisites(Altered_Installed_BIOS, Access_to_the_download_and_update_systems_used_to_deliver_BIOS_images).


prerequisites(Infiltration_of_Hardware_Development_Environment, The_victim_must_use_email_or_removable_media_from_systems_running_the_IDE).
prerequisites(Infiltration_of_Hardware_Development_Environment, The_victim_must_have_a_system_running_exploitable_applications_and_or_a_vulnerable_configuration).
prerequisites(Infiltration_of_Hardware_Development_Environment, The_attacker_must_have_working_knowledge_of_the_components_involved_in_IDE_and_infrastructure).

prerequisites(Open_Source_Libraries_Altered,Access_to_the_open_source_code_base_being_used_by_the_manufacturer_in_system_being_developed_or_currently_deployed_at_victim_location).

prerequisites(ASIC_With_Malicious_Functionality, Advanced_knowledge_about_ASIC_installed_within_the_target_system).
prerequisites(ASIC_With_Malicious_Functionality, The_attacker_must_have_working_knowledge_of_the_components_involved_in_target_system_as_well_infrastructure_and_development_environment_of_the_manufacturer).

/*Design_Alteration*/
prerequisites(Design_Alteration, Access_to_system_design_documentation_prior_to_the_development_phase).
prerequisites(Design_Alteration, Ability_to_forge_web_communications_to_deliver_modified_design_documentation).
mitigations(Design_Alteration, Assess_design_documentation_during_development_and_prior_to_deployment_to_ensure_that_they_function_as_intended_and_without_any_malicious_functionality).
mitigations(Design_Alteration, Ensure_that_design_documentation_is_saved_in_a_secure_location_and_has_proper_access_controls_set_in_place_to_avoid_unnecessary_modification).
parentOf(Design_Alteration, Documentation_Alteration_to_Circumvent_Dial_down).
parentOf(Design_Alteration, Documentation_Alteration_to_Produce_Under_performing_Systems).
parentOf(Design_Alteration, Documentation_Alteration_to_Cause_Errors_in_System_Design).
parentOf(Design_Alteration, Hardware_Design_Specifications_Are_Altered).

prerequisites(Documentation_Alteration_to_Circumvent_Dial_down, Advanced_knowledge_of_internal_software_and_hardware_components_within_manufacturers_development_environment).
prerequisites(Documentation_Alteration_to_Circumvent_Dial_down, Access_to_the_manufacturers_documentation).

prerequisites(Documentation_Alteration_to_Produce_Under_performing_Systems, Advanced_knowledge_of_software_and_hardware_capabilities_of_a_manufacturers_product).
prerequisites(Documentation_Alteration_to_Produce_Under_performing_Systems, Access_to_the_manufacturers_documentation).

prerequisites(Documentation_Alteration_to_Cause_Errors_in_System_Design, Advanced_knowledge_of_software_capabilities_of_a_manufacturers_product).
prerequisites(Documentation_Alteration_to_Cause_Errors_in_System_Design, Access_to_the_manufacturers_documentation).

prerequisites(Hardware_Design_Specifications_Are_Altered, Advanced_knowledge_of_hardware_capabilities_of_a_manufacturers_product).
prerequisites(Hardware_Design_Specifications_Are_Altered, Access_to_the_manufacturers_documentation).



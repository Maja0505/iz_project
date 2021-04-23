%'Configuration Environment Manipulation'
attack('Supply Chain','Configuration Environment Manipulation').
parentOf('Configuration Environment Manipulation','Manipulating Writeable Configuration Files').
parentOf('Configuration Environment Manipulation','Manipulate Registry Information').
parentOf('Configuration Environment Manipulation','Schema Poisoning').
parentOf('Configuration Environment Manipulation','Data Injected During Configuration').
parentOf('Configuration Environment Manipulation','Disable Security Software').
prerequisites('Configuration Environment Manipulation','The target application must consult external files or configuration controls to control its execution').
related_weaknesses('Configuration Environment Manipulation','External Control of System or Configuration Setting').
related_weaknesses('Configuration Environment Manipulation','Improper Hardware Lock Protection for Security Sensitive Controls').
related_weaknesses('Configuration Environment Manipulation','Hardware Internal or Debug Modes Allow Override of Locks').

%'Manipulating Writeable Configuration Files'
attack('Configuration Environment Manipulation','Manipulating Writeable Configuration Files').
prerequisites('Manipulating Writeable Configuration Files','Configuration files must be modifiable by the attacker').
related_weaknesses('Manipulating Writeable Configuration Files','Acceptance of Extraneous Untrusted Data With Trusted Data').
related_weaknesses('Manipulating Writeable Configuration Files','Improper Control of Resource Identifiers').
related_weaknesses('Manipulating Writeable Configuration Files','Improper Neutralization of Special Elements used in a Command').
related_weaknesses('Manipulating Writeable Configuration Files','Origin Validation Error').
related_weaknesses('Manipulating Writeable Configuration Files','Missing Support for Integrity Check').
related_weaknesses('Manipulating Writeable Configuration Files','Improper Validation of Integrity Check Value').
related_weaknesses('Manipulating Writeable Configuration Files','OWASP Top Ten 2007 Category A2 Injection Flaws').
prerequisites('Manipulating Writeable Configuration Files','Configuration files must be modifiable by the attacker').
mitigations('Manipulating Writeable Configuration Files','Enforce principle of least privilege').
mitigations('Manipulating Writeable Configuration Files','Backup copies of all configuration files').
mitigations('Manipulating Writeable Configuration Files','Integrity monitoring for configuration files').
mitigations('Manipulating Writeable Configuration Files','Enforce audit logging on code and configuration promotion procedures').
mitigations('Manipulating Writeable Configuration Files','Load configuration from separate process and memory space for example a separate physical device like a CD').

%'Manipulate Registry Information'
attack('Configuration Environment Manipulation','Manipulate Registry Information').
parentOf('Manipulate Registry Information','Poison Web Service Registry').
parentOf('Manipulate Registry Information','Modification of Registry Run Keys').
parentOf('Manipulate Registry Information','Modification of Windows Service Configuration').
prerequisites('Manipulate Registry Information','The targeted application must rely on values stored in a registry').
prerequisites('Manipulate Registry Information','The adversary must have a means of elevating permissions in order to access and modify registry content through either administrator privileges or a remote access tool capable of editing a registry through an API').
related_weaknesses('Manipulate Registry Information','External Control of System or Configuration Setting').
mitigations('Manipulate Registry Information','Ensure proper permissions are set for Registry hives to prevent users from modifying keys').
mitigations('Manipulate Registry Information','Employ a robust and layered defensive posture in order to prevent unauthorized users on your system').
mitigations('Manipulate Registry Information','Employ robust identification and audit blocking using an allowlist of applications on your system').


%'Poison Web Service Registry'
attack('Manipulate Registry Information','Poison Web Service Registry').
prerequisites('Poison Web Service Registry','The attacker must be able to write to resources or redirect access to the service registry').
related_weaknesses('Poison Web Service Registry','Improper Authorization').
related_weaknesses('Poison Web Service Registry','Improper Neutralization of Special Elements in Output Used by a Downstream Component').
related_weaknesses('Poison Web Service Registry','Protection Mechanism Failure').
mitigations('Poison Web Service Registry','Enforce principle of least privilege').
mitigations('Poison Web Service Registry','Harden registry server and file access permissions').
mitigations('Poison Web Service Registry','Implement communications to and from the registry using secure protocols').

%'Modification of Registry Run Keys'
attack('Manipulate Registry Information','Modification of Registry Run Keys').
prerequisites('Modification of Registry Run Keys','The adversary must have gained access to the target system via physical or logical means in order to carry out this attack').
related_weaknesses('Modification of Registry Run Keys','External Control of System or Configuration Setting').
mitigations('Modification of Registry Run Keys','Identify programs that may be used to acquire process information and block them by using a software restriction policy or tools that restrict program execution by using a process allowlist').

%'Modification of Windows Service Configuration'
attack('Manipulate Registry Information','Modification of Windows Service Configuration').
prerequisites('Modification of Windows Service Configuration','The adversary must have the capability to write to the Windows Registry on the targeted system').
related_weaknesses('Modification of Windows Service Configuration','Improper Access Control').
mitigations('Modification of Windows Service Configuration','Ensure proper permissions are set for Registry hives to prevent users from modifying keys for system components that may lead to privilege escalation').

/*'Schema Poisoning'*/
attack('Configuration Environment Manipulation','Schema Poisoning').
prerequisites('Schema Poisoning','Some level of access to modify the target schema').
prerequisites('Schema Poisoning','The schema used by the target application must be improperly secured against unauthorized modification and manipulation').
mitigations('Schema Poisoning','Protect the schema against unauthorized modification').
mitigations('Schema Poisoning','For applications that use a known schema use a local copy or a known good repository instead of the schema reference supplied in the schema document').
mitigations('Schema Poisoning','For applications that leverage remote schemas use the HTTPS protocol to prevent modification of traffic in transit and to avoid unauthorized modification').
/*Consequences????*/

/*'Data Injected During Configuration'*/
attack('Configuration Environment Manipulation','Data Injected During Configuration').
prerequisites('Data Injected During Configuration','The attacker must have previously compromised the victims systems or have physical access to the victims systems').
prerequisites('Data Injected During Configuration','Advanced knowledge of software and hardware capabilities of a manufacturers product').
mitigations('Data Injected During Configuration','Ensure that proper access control is implemented on all systems to prevent unauthorized access to system files and processe').

/*'Disable Security Software'*/
attack('Configuration Environment Manipulation','Disable Security Software').
prerequisites('Disable Security Software','The adversary must have the capability to interact with the configuration of the targeted system').
mitigations('Disable Security Software','Ensure proper permissions are in place to prevent adversaries from altering the execution status of security tools').


/*''Manipulation During Distribution''*/
parentOf('Manipulation During Distribution','Malicious Hardware Componen Replacement').
parentOf('Manipulation During Distribution','Malicious Software Implanted').
parentOf('Manipulation During Distribution','Rogue Integration Procedures').

attack('Supply Chain', 'Manipulation During Distribution').
related_weaknesses('Manipulation During Distribution','Product Released in Non Release Configuration').

/*'Malicious Hardware Component Replacement'*/
attack('Manipulation During Distribution','Malicious Hardware Component Replacement').
prerequisites('Malicious Hardware Component Replacement','Physical access to the system after it has left the manufacturer but before it is deployed at the victim location').

/*'Malicious Software Implanted'*/
attack('Manipulation During Distribution','Maliciou Software Implanted').
prerequisites('Maliciou Software Implanted','Physical access to the system after it has left the manufacturer but before it is deployed at the victim location').

/*'Modification During Manufacture'*/
attack('Supply Chain','Modification During Manufacture').
parentOf('Modification During Manufacture', 'Development Alteration').
parentOf('Modification During Manufacture', 'Design Alteration').


/*'Development Alteration'*/
parentOf('Development Alteration', 'Malicious Logic Inserted Into Product Software by Authorized Developer').
parentOf('Development Alteration', 'Malicious Logic Insertion into Product Software via Configuration Management Manipulation').
parentOf('Development Alteration', 'Malicious Logic Insertion into Product Software via Inclusion of 3rd Party Component Dependency').
parentOf('Development Alteration', 'Infiltration of Software Development Environment').
parentOf('Development Alteration', 'Hardware Component Substitution During Baselining').
parentOf('Development Alteration', 'Counterfeit Hardware Component Inserted During Product Assembly').
parentOf('Development Alteration', 'Altered Installed BIOS').
parentOf('Development Alteration', 'Infiltration of Hardware Development Environment').
parentOf('Development Alteration', 'Open Source Libraries Altered').
parentOf('Development Alteration', 'ASIC With Malicious Functionality').

prerequisites('Development Alteration', 'Access to the system during the development phase to alter and or modify software and hardware components').
mitigations('Development Alteration', 'Assess Software and software components during development and prior to deployment to ensure that they function as intended and without any malicious functionality').

prerequisites('Malicious Logic Inserted Into Product Software by Authorized Developer', 'Access to the software during the development phase').
mitigations('Malicious Logic Inserted Into Product Software by Authorized Developer', 'Assess Software and software components during development and prior to deployment to ensure that they function as intended and without any malicious functionality').

prerequisites('Malicious Logic Insertion into Product Software via Configuration Management Manipulation',' Access to the configuration management system during deployment or currently deployed at a victim location').
mitigations('Malicious Logic Insertion into Product Software via Configuration Management Manipulation', 'Assess Software and software components during development and prior to deployment to ensure that they function as intended and without any malicious functionality').
mitigations('Malicious Logic Insertion into Product Software via Configuration Management Manipulation', 'Leverage anti virus products to detect and quarantine software with known virus').

prerequisites('Malicious Logic Insertion into Product Software via Inclusion of 3rd Party Component Dependency', 'Access to the software during the development phase').
mitigations('Malicious Logic Insertion into Product Software via Inclusion of 3rd Party Component Dependency', 'Assess Software and software components during development and prior to deployment to ensure that they function as intended and without any malicious functionality').

prerequisites('Infiltration of Software Development Environment', 'The victim must use email or removable media from systems running the IDE').
prerequisites('Infiltration of Software Development Environment',' The victim must have a system running exploitable applications and or a vulnerable configuration').
prerequisites('Infiltration of Software Development Environment', 'The attacker must have working knowledge of the components involved in IDE and infrastructure').

prerequisites('Hardware Component Substitution During Baselining', 'The attacker need physical access or be able to supply malicious hardware components to the product development facility').

prerequisites('Counterfeit Hardware Component Inserted During Product Assembly', 'The attacker need physical access or be able to supply malicious hardware components to the product development facility').

prerequisites('Altered Installed BIOS', 'Advanced knowledge about the installed target system design').
prerequisites('Altered Installed BIOS', 'Advanced knowledge about the download and update installation processes').
prerequisites('Altered Installed BIOS', 'Access to the download and update systems used to deliver BIOS images').


prerequisites('Infiltration of Hardware Development Environment', 'The victim must use email or removable media from systems running the IDE').
prerequisites('Infiltration of Hardware Development Environment',' The victim must have a system running exploitable applications and or a vulnerable configuration').
prerequisites('Infiltration of Hardware Development Environment', 'The attacker must have working knowledge of the components involved in IDE and infrastructure').

prerequisites('Open Source Libraries Altered','Access to the open source code base being used by the manufacturer in system being developed or currently deployed at victim location').

prerequisites('ASIC With Malicious Functionality', 'Advanced knowledge about ASIC installed within the target system').
prerequisites('ASIC With Malicious Functionality', 'The attacker must have working knowledge of the components involved in target system as well infrastructure and development environment of the manufacturer').

/*'Design Alteration'*/
prerequisites('Design Alteration', 'Access to system design documentation prior to the development phase').
prerequisites('Design Alteration', 'Ability to forge web communications to deliver modified design documentation').
mitigations('Design Alteration',' Assess design documentation during development and prior to deployment to ensure that they function as intended and without any malicious functionality').
mitigations('Design Alteration', 'Ensure that design documentation is saved in a secure location and has proper access controls set in place to avoid unnecessary modification').
parentOf('Design Alteration', 'Documentation Alteration to Circumvent Dial down').
parentOf('Design Alteration', 'Documentation Alteration to Produce Under performing Systems').
parentOf('Design Alteration', 'Documentation Alteration to Cause Errors in System Design').
parentOf('Design Alteration', 'Hardware Design Specifications Are Altered').

prerequisites('Documentation Alteration to Circumvent Dial down', 'Advanced knowledge of internal software and hardware components within manufacturers development environment').
prerequisites('Documentation Alteration to Circumvent Dial down', 'Access to the manufacturers documentation').

prerequisites('Documentation Alteration to Produce Under performing Systems', 'Advanced knowledge of software and hardware capabilities of a manufacturers product').
prerequisites('Documentation Alteration to Produce Under performing Systems', 'Access to the manufacturers documentation').

prerequisites('Documentation Alteration to Cause Errors in System Design', 'Advanced knowledge of software capabilities of a manufacturers product').
prerequisites('Documentation Alteration to Cause Errors in System Design', 'Access to the manufacturers documentation').

prerequisites('Hardware Design Specifications Are Altered', 'Advanced knowledge of hardware capabilities of a manufacturers product').
prerequisites('Hardware Design Specifications Are Altered', 'Access to the manufacturers documentation').

/*'Rogue Integration Procedures'*/
attack('Manipulation During Distribution','Rogue Integration Procedures').
prerequisites('Rogue Integration Procedures','Physical access to an integration facility that prepares the system before it is deployed at the victim location').

all_attacks(L) :- findall(L1,attack(M,L1),L).
all_mitigations_for_attack(M,X) :- findall(L1,mitigations(M,L1),X).
all_attacks_for_mitigation(N,L) :- findall(M,mitigations(M,N),L).
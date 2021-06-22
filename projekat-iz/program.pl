

attack('Configuration Environment Manipulation').
attack_type('Configuration Environment Manipulation','Software').
attack_type('Configuration Environment Manipulation','Hardware').


attack('Manipulating Writeable Configuration Files').
mitigations('Manipulating Writeable Configuration Files','Enforce principle of least privilege').
mitigations('Manipulating Writeable Configuration Files','Backup copies of all configuration files').
mitigations('Manipulating Writeable Configuration Files','Integrity monitoring for configuration files').
mitigations('Manipulating Writeable Configuration Files','Enforce audit logging on code and configuration promotion procedures').
mitigations('Manipulating Writeable Configuration Files','Load configuration from separate process and memory space for example a separate physical device like a CD').
attack_type('Manipulating Writeable Configuration Files','Software').


attack('Manipulate Registry Information').
attack_type('Manipulate Registry Information','Software').
mitigations('Manipulate Registry Information','Ensure proper permissions are set for Registry hives to prevent users from modifying keys').
mitigations('Manipulate Registry Information','Employ a robust and layered defensive posture in order to prevent unauthorized users on your system').
mitigations('Manipulate Registry Information','Employ robust identification and audit blocking using an allowlist of applications on your system').



attack('Poison Web Service Registry').
attack_type('Poison Web Service Registry','Software').
mitigations('Poison Web Service Registry','Enforce principle of least privilege').
mitigations('Poison Web Service Registry','Harden registry server and file access permissions').
mitigations('Poison Web Service Registry','Implement communications to and from the registry using secure protocols').


attack('Modification of Registry Run Keys').
attack_type('Poison Web Service Registry','Software').
mitigations('Modification of Registry Run Keys','Identify programs that may be used to acquire process information and block them by using a software restriction policy or tools that restrict program execution by using a process allowlist').


attack('Modification of Windows Service Configuration').
attack_type('Modification of Windows Service Configuration','Software').
mitigations('Modification of Windows Service Configuration','Ensure proper permissions are set for Registry hives to prevent users from modifying keys for system components that may lead to privilege escalation').


attack('Schema Poisoning').
attack_type('Schema Poisoning','Software').
mitigations('Schema Poisoning','Protect the schema against unauthorized modification').
mitigations('Schema Poisoning','For applications that use a known schema use a local copy or a known good repository instead of the schema reference supplied in the schema document').
mitigations('Schema Poisoning','For applications that leverage remote schemas use the HTTPS protocol to prevent modification of traffic in transit and to avoid unauthorized modification').




attack('Data Injected During Configuration').
attack_type('Data Injected During Configuration','Software').
mitigations('Data Injected During Configuration','Ensure that proper access control is implemented on all systems to prevent unauthorized access to system files and processe').
attack_description('Data Injected During Configuration',['Suspicious code changes']).


attack('Disable Security Software').
attack_type('Disable Security Software','Software').
mitigations('Disable Security Software','Ensure proper permissions are in place to prevent adversaries from altering the execution status of security tools').
attack_description('Disable Security Software',['Software in deployment phase']).


attack('Manipulation During Distribution').
attack_type('Manipulation During Distribution','Hardware').


attack('Malicious Hardware Component Replacement').
attack_type('Malicious Hardware Component Replacement','Hardware').


attack('Maliciou Software Implanted').
attack_type('Maliciou Software Implanted','Hardware').
attack_type('Maliciou Software Implanted','Software').


attack('Modification During Manufacture').
attack_type('Modification During Manufacture','Hardware').
attack_type('Modification During Manufacture','Software').



attack('Development Alteration').
attack_type('Development Alteration','Software').
mitigations('Development Alteration', 'Assess Software and software components during development and prior to deployment to ensure that they function as intended and without any malicious functionality').

attack('Malicious Logic Inserted Into Product Software by Authorized Developer').
attack_type('Malicious Logic Inserted Into Product Software by Authorized Developer','Software').
mitigations('Malicious Logic Inserted Into Product Software by Authorized Developer', 'Assess Software and software components during development and prior to deployment to ensure that they function as intended and without any malicious functionality').


attack('Malicious Logic Insertion into Product Software via Configuration Management Manipulation').
attack_type('Malicious Logic Insertion into Product Software via Configuration Management Manipulation','Software').
mitigations('Malicious Logic Insertion into Product Software via Configuration Management Manipulation', 'Assess Software and software components during development and prior to deployment to ensure that they function as intended and without any malicious functionality').
mitigations('Malicious Logic Insertion into Product Software via Configuration Management Manipulation', 'Leverage anti virus products to detect and quarantine software with known virus').
attack_description('Malicious Logic Insertion into Product Software via Configuration Management Manipulation',['Software in development phase','Sofware in deployment phase']).

attack('Malicious Logic Insertion into Product Software via Inclusion of 3rd Party Component Dependency').
attack_type('Malicious Logic Insertion into Product Software via Inclusion of 3rd Party Component Dependency','Software').
mitigations('Malicious Logic Insertion into Product Software via Inclusion of 3rd Party Component Dependency', 'Assess Software and software components during development and prior to deployment to ensure that they function as intended and without any malicious functionality').
attack_description('Malicious Logic Insertion into Product Software via Inclusion of 3rd Party Component Dependency',['Software in development phase','Sofware in deployment phase']).



attack('Infiltration of Software Development Environment').
attack_type('Infiltration of Software Development Environment','Software'). 
attack_description('Infiltration of Software Development Environment',['Software in development phase','Sofware in deployment phase','Physical access required','Recently received updates']).


attack('Hardware Component Substitution During Baselining').
attack_type('Hardware Component Substitution During Baselining','Hardware').

attack('Counterfeit Hardware Component Inserted During Product Assembly').
attack_type('Counterfeit Hardware Component Inserted During Product Assembly','Hardware').
attack_type('Counterfeit Hardware Component Inserted During Product Assembly','Software').


attack('Altered Installed BIOS').
attack_type('Altered Installed BIOS','Software').
attack_description('Altered Installed BIOS',['Recently received updates']).


attack('Infiltration of Hardware Development Environment').
attack_type('Infiltration of Hardware Development Environment','Hardware').
attack_description('Infiltration of Hardware Development Environment',['Physical access required']).

attack('Open Source Libraries Altered').
attack_type('Open Source Libraries Altered','Software').
attack_description('Open Source Libraries Altered',['Software in development phase','Sofware in deployment phase','Physical access required','Recently received updates']).


attack('ASIC With Malicious Functionality').
attack_type('ASIC With Malicious Functionality','Hardware').
attack_description('ASIC With Malicious Functionality',['Software in development phase','Sofware in deployment phase','Physical access required','Recently received updates']).




attack('Design Alteration').
attack_type('Design Alteration','Software').
mitigations('Design Alteration',' Assess design documentation during development and prior to deployment to ensure that they function as intended and without any malicious functionality').
mitigations('Design Alteration', 'Ensure that design documentation is saved in a secure location and has proper access controls set in place to avoid unnecessary modification').

attack('Documentation Alteration to Circumvent Dial down').
attack_type('Documentation Alteration to Circumvent Dial down','Software').
attack_description('Documentation Alteration to Circumvent Dial down',['Altered documentation','Sofware in deployment phase']).


attack('Documentation Alteration to Produce Under performing Systems').
attack_type('Documentation Alteration to Produce Under performing Systems','Software').
attack_description('Documentation Alteration to Produce Under performing Systems',['Altered documentation','Sofware in deployment phase']).



attack('Documentation Alteration to Cause Errors in System Design').
attack_type('Documentation Alteration to Cause Errors in System Design','Software').

attack('Hardware Design Specifications Are Altered').
attack_type('Hardware Design Specifications Are Altered','Hardware').
attack_description('Hardware Design Specifications Are Altered',['Physical access required']).


attack('Rogue Integration Procedures').
attack_type('Rogue Integration Procedures','Software').
attack_type('Rogue Integration Procedures','Hardware').
attack_description('Rogue Integration Procedures',['Physical access required','Software in development phase']).


attack('Hardware Integrity Attack').
attack_type('Hardware Integrity Attack','Hardware').
attack_description('Rogue Integration Procedures',['Physical access required']).


attack('Physically Hacking Hardware').
attack_type('Physically Hacking Hardware','Hardware').
attack_description('Physically Hacking Hardware',['Physical access required']).


attack('Bypassing ATA Password Security').
attack_type('Bypassing ATA Password Security','Software').
attack_description('Bypassing ATA Password Security',['Physical access required']).


attack('Malicious Hardware Update').
attack_type('Malicious Hardware Update','Hardware').
attack_description('Malicious Hardware Update',['Physical access required']).

attack('Hardware Component Substitution').
attack_type('Hardware Component Substitution','Hardware').
attack_description('Hardware Component Substitution',['Physical access required']).

attack('Provide Counterfeit Component').
attack_type('Provide Counterfeit Component','Hardware').

attack('Malicious Gray Market Hardware').
attack_type('Malicious Gray Market Hardware','Hardware').



attack('Malicious Logic Insertion').
attack_type('Malicious Logic Insertion','Software').


attack('Infected Software').
attack_type('Infected Software','Software').
mitigations('Infected Software', 'Leverage anti-virus products to detect and quarantine software with known virus.').

attack('Embed Virus into DLL').
attack_type('Embed Virus into DLL','Software').
mitigations('Embed Virus into DLL', 'Leverage anti-virus products to detect and quarantine software with known virus.').
attack_description('Embed Virus into DLL',['Recently used removable media']).

attack('Infected Hardware').
attack_type('Infected Hardware','Hardware').
attack_description('Infected Hardware',['Recently used removable media']).

attack('Altered Component Firmware').
attack_type('Altered Component Firmware','Software').

attack('Infected Memory').
attack_type('Infected Memory','Software').
mitigations('Infected Memory', 'Leverage anti-virus products to detect stop operations with known virus.').

attack('USB Memory Attacks').
attack_type('USB Memory Attacks','Hardware').
mitigations('USB Memory Attacks', 'Ensure that proper, physical system access is regulated to prevent an adversary from physically connecting a malicious USB device themself.').
mitigations('USB Memory Attacks', 'Use anti-virus and anti-malware tools which can prevent malware from executing if it finds its way onto a target system. Additionally, make sure these tools are regularly updated to contain up-to-date virus and malware signatures.').
mitigations('USB Memory Attacks', 'Do not connect untrusted USB devices to systems connected on an organizational network. Additionally, use an isolated testing machine to validate untrusted devices and confirm malware does not exist.').

attack('Flash Memory Attacks').
attack_type('Flash Memory Attacks','Software').

mitigations_for_type('Software','Backup copies of all configuration files.Integrity monitoring for configuration files.Identify programs that may be used to acquire process information and block them by using a software restriction policy.').
mitigations_for_type('Hardware','Ensure that proper, physical system access is regulated to prevent an adversary from physically connecting a malicious USB device themself.Do not connect untrusted USB devices to systems connected on an organizational network. Additionally, make sure these tools are regularly updated to contain up-to-date virus and malware signatures.').

mitigations_for_symptoms('Recently used removable media','Ensure that proper, physical system access is regulated to prevent an adversary from physically connecting a malicious USB device themself.').
mitigations_for_symptoms('Physical access required','Do not connect untrusted USB devices to systems connected on an organizational network. Additionally, use an isolated testing machine to validate untrusted devices and confirm malware does not exist.').
mitigations_for_symptoms('Suspicious code changes',' Assess design documentation during development and prior to deployment to ensure that they function as intended and without any malicious functionality').


is_mitigations_empty(Attack) :- findall(L1,mitigations(Attack,L1),X),X==[].
description_exists(attack,L1) :- attack_description(attack,L1).
find_description_for_attack(A,X):- attack_description(A,X).
find_type_for_attack(A,X):- attack_type(A,X). 
all_mitigations_for_attack(A,X) :- findall(L1,mitigations(A,L1),X).
mitigation_for_attack_type(T,M) :- findall(L1,mitigations_for_type(T,L1),M).
mitigation_for_attack_symptom(T,M) :- findall(L1,mitigations_for_symptoms(T,L1),M).

  

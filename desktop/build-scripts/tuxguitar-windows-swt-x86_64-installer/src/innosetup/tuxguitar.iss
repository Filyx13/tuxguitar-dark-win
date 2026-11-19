#ifndef MyAppVersion
  #define MyAppVersion "2.1.1-dark"
#endif
#ifndef BundleDir
  #error BundleDir must be defined
#endif
#ifndef OutputDir
  #error OutputDir must be defined
#endif
#ifndef OutputBaseFilename
  #define OutputBaseFilename "tuxguitar-" + MyAppVersion + "-windows-swt-x86_64-setup"
#endif

[Setup]
AppId={{D9C8F2A1-4E53-4A89-9E72-5C3D12B8F6A4}
AppName=TuxGuitar (Windows Dark Mode)
AppVersion={#MyAppVersion}
AppVerName=TuxGuitar {#MyAppVersion} (Windows Dark Mode)
AppPublisher=Filyx13 (Fork of TuxGuitar)
AppPublisherURL=https://github.com/Filyx13/tuxguitar-dark-win
AppSupportURL=https://github.com/Filyx13/tuxguitar-dark-win/issues
DefaultDirName={autopf}\TuxGuitar
DefaultGroupName=TuxGuitar
DisableProgramGroupPage=yes
LicenseFile={#BundleDir}\doc\LICENSE
OutputDir={#OutputDir}
OutputBaseFilename={#OutputBaseFilename}
SetupIconFile=..\..\..\common-resources\common-windows-installer-icons\TG_icon_install.ico
UninstallDisplayIcon={app}\dist\tuxguitar.ico
Compression=lzma2/normal
SolidCompression=yes
WizardStyle=modern
ArchitecturesAllowed=x64compatible
ArchitecturesInstallIn64BitMode=x64compatible
PrivilegesRequired=lowest
PrivilegesRequiredOverridesAllowed=dialog
ChangesAssociations=yes

[Languages]
Name: "german"; MessagesFile: "compiler:Languages\German.isl"
Name: "english"; MessagesFile: "compiler:Default.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"
Name: "associate"; Description: "TuxGuitar-Dateien (.tg, .gp*, .ptb, .tef)"; GroupDescription: "Dateizuordnungen:"

[Files]
Source: "{#BundleDir}\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{autoprograms}\TuxGuitar"; Filename: "{app}\tuxguitar.exe"; IconFilename: "{app}\dist\tuxguitar.ico"; WorkingDir: "{app}"
Name: "{autodesktop}\TuxGuitar"; Filename: "{app}\tuxguitar.exe"; IconFilename: "{app}\dist\tuxguitar.ico"; WorkingDir: "{app}"; Tasks: desktopicon

[Registry]
Root: HKA; Subkey: "Software\Classes\TuxGuitar.File"; ValueType: string; ValueName: ""; ValueData: "TuxGuitar Tablature"; Flags: uninsdeletekey; Tasks: associate
Root: HKA; Subkey: "Software\Classes\TuxGuitar.File\DefaultIcon"; ValueType: string; ValueName: ""; ValueData: "{app}\dist\tuxguitar.ico,0"; Tasks: associate
Root: HKA; Subkey: "Software\Classes\TuxGuitar.File\shell\open\command"; ValueType: string; ValueName: ""; ValueData: """{app}\tuxguitar.exe"" ""%1"""; Tasks: associate
Root: HKA; Subkey: "Software\Classes\.tg"; ValueType: string; ValueName: ""; ValueData: "TuxGuitar.File"; Flags: uninsdeletevalue; Tasks: associate
Root: HKA; Subkey: "Software\Classes\.gtp"; ValueType: string; ValueName: ""; ValueData: "TuxGuitar.File"; Flags: uninsdeletevalue; Tasks: associate
Root: HKA; Subkey: "Software\Classes\.gp3"; ValueType: string; ValueName: ""; ValueData: "TuxGuitar.File"; Flags: uninsdeletevalue; Tasks: associate
Root: HKA; Subkey: "Software\Classes\.gp4"; ValueType: string; ValueName: ""; ValueData: "TuxGuitar.File"; Flags: uninsdeletevalue; Tasks: associate
Root: HKA; Subkey: "Software\Classes\.gp5"; ValueType: string; ValueName: ""; ValueData: "TuxGuitar.File"; Flags: uninsdeletevalue; Tasks: associate
Root: HKA; Subkey: "Software\Classes\.gpx"; ValueType: string; ValueName: ""; ValueData: "TuxGuitar.File"; Flags: uninsdeletevalue; Tasks: associate
Root: HKA; Subkey: "Software\Classes\.gp"; ValueType: string; ValueName: ""; ValueData: "TuxGuitar.File"; Flags: uninsdeletevalue; Tasks: associate
Root: HKA; Subkey: "Software\Classes\.ptb"; ValueType: string; ValueName: ""; ValueData: "TuxGuitar.File"; Flags: uninsdeletevalue; Tasks: associate
Root: HKA; Subkey: "Software\Classes\.tef"; ValueType: string; ValueName: ""; ValueData: "TuxGuitar.File"; Flags: uninsdeletevalue; Tasks: associate

[Run]
Filename: "{app}\tuxguitar.exe"; Description: "{cm:LaunchProgram,TuxGuitar}"; Flags: nowait postinstall skipifsilent
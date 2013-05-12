@echo off
echo [INFO] Generate source code from entity (according templates) to %cd%\generated dir.
call ant -buildfile E:\plProject\PowerDesk\bin\hibernate\build.xml generate.code
echo [INFO] Artifacts will generate  in %cd%\generated dir.
pause
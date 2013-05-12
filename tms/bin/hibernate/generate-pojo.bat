@echo off
echo [INFO] Generate entity from database to %cd%\generated dir.
call ant -f build.xml generate.pojo
echo [INFO] Artifactss will generate  in %cd%\generated dir.

pause
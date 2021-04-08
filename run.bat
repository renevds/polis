@ECHO off
REM Pas de twee variabelen hieronder aan zodat ze overeenkomen
REM met jouw eigen configuratie:

REM Installatiemap van Java
SET JAVA_HOME="C:\Program Files\AdoptOpenJDK\jdk-11.0.10.9-hotspot"

REM Map waar de JAVAFX-bibliotheek zich bevindt. Zelfde als bij de
REM Global Libraries in je IDEA Project Structure
SET JAVAFX_PATH="C:\Program Files (x86)\javafx-sdk-11.0.2"

%JAVA_HOME%\bin\java.exe --module-path=%JAVAFX_PATH%\lib;%PROJECT_PATH%\lib ^
                         --add-modules=ALL-MODULE-PATH ^
                         -Dfile.encoding=UTF-8 %*%
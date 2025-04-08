@echo off
setlocal EnableDelayedExpansion

echo Debut du script de build...

:: 1. Creer le dossier build s'il n'existe pas, sinon le vider
if not exist "build" (
    mkdir build
) else (
    echo Suppression du contenu existant dans build...
    rd /s /q build
    mkdir build
)

:: 2. Creer le repertoire WEB-INF/classes dans build
echo Creation de WEB-INF/classes dans build...
mkdir build\WEB-INF\classes

:: 3. Generer source.txt avec les fichiers .java et compiler
echo Generation de source.txt avec les fichiers .java...
dir /s /b src\*.java > source.txt

@REM echo Compilation des fichiers Java avec compatibilite Java 8...
mkdir bin
javac -cp "lib\servlet-api.jar;lib\mysql-connector-j-9.0.0.jar" -d build\WEB-INF\classes @source.txt
if %ERRORLEVEL% NEQ 0 (
    echo Erreur lors de la compilation !
    exit /b %ERRORLEVEL%
)

echo Copie des classes compilees dans bin avec packages...
xcopy build\WEB-INF\classes bin /E /I /Y

:: 4. Copier le contenu de webapp vers build
echo Copie de webapp vers build...
xcopy webapp build /E /I /Y

:: 5. Creer le fichier ETU003305.war
echo Creation de ETU003305.war...
cd build
jar -cvf ..\ETU003305.war .
cd ..

:: 6. Copier le fichier .war dans "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps"
echo Copie de ETU003305.war vers "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps"...
xcopy ETU003305.war "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps" /Y

echo Build termine avec succes !
exit /b 0

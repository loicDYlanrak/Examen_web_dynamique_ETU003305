#!/bin/bash

# Variables
BUILD_DIR="build"
WEB_INF_CLASSES="$BUILD_DIR/WEB-INF/classes"
SRC_DIR="src"
SOURCE_FILE="source.txt"
LIB_DIR="lib"
SERVLET_API="$LIB_DIR/servlet-api.jar"
WEBAPP_DIR="webapp"
WAR_FILE="projet.war"
TOMCAT_WEBAPPS="/path/to/tomcat/webapps"

# 1. Creer le repertoire build s'il n'existe pas
if [ ! -d "$BUILD_DIR" ]; then
    mkdir -p "$BUILD_DIR"
fi

# 2. Ajouter repertoire WEB-INF/classes et nettoyer le contenu de build
if [ ! -d "$WEB_INF_CLASSES" ]; then
    mkdir -p "$WEB_INF_CLASSES"
fi
rm -rf "$BUILD_DIR"/*

# 3. Lister tous les fichiers .java dans src et ecrire dans source.txt
if [ -d "$SRC_DIR" ]; then
    find "$SRC_DIR" -name "*.java" > "$SOURCE_FILE"
    echo "Liste des fichiers Java generee dans $SOURCE_FILE."
else
    echo "Repertoire source introuvable : $SRC_DIR"
    exit 1
fi

# 4. Compiler les fichiers Java
if [ -f "$SOURCE_FILE" ]; then
    javac -cp "$SERVLET_API" -d "$WEB_INF_CLASSES" @"$SOURCE_FILE"
else
    echo "Fichier source.txt introuvable !"
    exit 1
fi

# 5. Copier les fichiers de webapp dans build
cp -r "$WEBAPP_DIR"/* "$BUILD_DIR"/

# 6. Creer le fichier WAR
cd "$BUILD_DIR"
jar -cvf "../$WAR_FILE" *
cd ..

# 7. Copier le fichier WAR dans le repertoire webapps de Tomcat
cp -f "$WAR_FILE" "$TOMCAT_WEBAPPS/$WAR_FILE"

echo "Deploiement termine !"

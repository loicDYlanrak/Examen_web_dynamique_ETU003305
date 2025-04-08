# Utiliser l'image officielle Tomcat
FROM mon-tomcat 

# Definir le repertoire de travail
WORKDIR /usr/local/tomcat/webapps

# Copier le fichier WAR dans le repertoire webapps de Tomcat
COPY projetServlet.war /usr/local/tomcat/webapps/

# Exposer le port 8080
EXPOSE 8080

# Demarrer Tomcat
CMD ["catalina.sh", "run"]
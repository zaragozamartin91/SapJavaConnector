# SapJavaConnector
Conector de sap para la ejecucion de Jobs.

Para compilar el proyecto es necesario tener instalado JCO3 en el repo local de maven.
Para ello, comprimir el javadoc de jco3 en un jar llamado sapjco3-javadoc.jar y correr el siguiente comando:
mvn install:install-file -DgroupId=com.sap.conn.jco -DartifactId=sapjco3 -Dversion=3.0.16 -Dpackaging=jar -Dfile=sapjco3.jar -Djavadoc=sapjco3-javadoc.jar
# SapJavaConnector
Conector de sap para la ejecucion de Jobs.

Para compilar el proyecto es necesario tener instalado JCO3 en el repo local de maven.
Para ello, comprimir el javadoc de jco3 en un jar llamado sapjco3-javadoc.jar y correr el siguiente comando:
mvn install:install-file -DgroupId=com.sap.conn.jco -DartifactId=sapjco3 -Dversion=3.0.16 -Dpackaging=jar -Dfile=sapjco3.jar -Djavadoc=sapjco3-javadoc.jar

RECORDAR QUE NO SE PUEDE CAMBIAR EL NOMBRE DEL JAR DE JCO:
'Exception in thread "main" java.lang.ExceptionInInitializerError: JCo initialization failed with java.lang.ExceptionInInitializerError: Illegal JCo archive "sapjco3-3.0.16.jar". It is not allowed to rl archive "sapjco3.jar".'
PARA SORTEAR ESTE PROBLEMA: 
> GENERAR EL JAR EJECUTABLE COMO UN RUNNABLE JAR CON LAS BIBLIOTECAS EN UN DIRECTORIO PARALELO.
> MODIFICAR EL MANIFEST PARA QUE LA DEPENDENCIA DE jco SEA CON EL JAR sapjco3.jar.
> MODIFICAR LA DEPENDENCIA DE JCO DEL DIRECTORIO PARALELO PARA QUE SE LLAME sapjco3.jar.
> VOLVER A GENERAR EL JAR DEL CONECTOR SAP.
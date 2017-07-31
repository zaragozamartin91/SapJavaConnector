# SapJavaConnector
Conector de sap para la ejecucion de Jobs.

Para compilar el proyecto es necesario tener instalado JCO3 en el repo local de maven.
Para ello, comprimir el javadoc de jco3 en un jar llamado sapjco3-javadoc.jar y correr el siguiente comando:
mvn install:install-file -DgroupId=com.sap.conn.jco -DartifactId=sapjco3 -Dversion=3.0.16 -Dpackaging=jar -Dfile=sapjco3.jar -Djavadoc=sapjco3-javadoc.jar

RECORDAR QUE NO SE PUEDE CAMBIAR EL NOMBRE DEL JAR DE JCO:
'Exception in thread "main" java.lang.ExceptionInInitializerError: JCo initialization failed with java.lang.ExceptionInInitializerError: Illegal JCo archive "sapjco3-3.0.16.jar". It is not allowed to rl archive "sapjco3.jar".'
SE RECOMIENDA EXPORTAR EL JAR USANDO ECLIPSE CREANDO UN RUNNABLE JAR FILE CON LAS DEPENDENCIAS EN UN DIRECTORIO PARALELO

PARA PROBAR EL CODIGO EN ECLIPSE ES NECESARIO INDICARLE A MAVEN LA LOCALIZACION DE LAS BIBLIOTECAS NATIVAS DE JCO:
> IR A PROPIEDADES DEL PROYECTO / JAVA BUILD PATH / LIBRARIES
> DESPLEGAR LAS DEPENDENCIAS DE MAVEN Y EDITAR LA OPCION "NATIVE LIBRARY LOCATION".
> INDICAR EL DIRECTORIO DONDE SE ENCUENTRA LA *.DLL DE JCO.
ESTOS PASOS NO SON NECESARIOS (SUPUESTAMENTE) SI SE USAN SYSTEM DEPENDENCIES CON MAVEN

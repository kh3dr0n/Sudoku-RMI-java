###Environment java :
java version "1.8.0_73"
Java(TM) SE Runtime Environment (build 1.8.0_73-b02)
Java HotSpot(TM) 64-Bit Server VM (build 25.73-b02, mixed mode)

##Compilation :
cd www
javac *.java
rmic -v1.1 Middle
rmic -v1.1 FabricSudoku
rmic -v1.1 Gui
cp DynamicClient.class ../client
cp DynamicServer.class ../server


##Execution serveur (il faut que les fichiers dans www copié dans répertoire du serveur web ou executer un serveur dans ce dossier, et changer http://localhost/  vers l’url du serveur web):
cd server
java -Djava.security.policy=server.policy -Djava.rmi.server.codebase=http://localhost/ DynamicServer



##Execution Client :
cd client
java -Djava.security.policy=client.policy -Djava.rmi.server.codebase=http://localhost/ DynamicClient

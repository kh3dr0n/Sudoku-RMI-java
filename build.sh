rm */*.class
cd www
javac *.java
rmic -v1.1 Middle
rmic -v1.1 FabricSudoku
rmic -v1.1 Gui
cp DynamicClient.class ../client
cp DynamicServer.class ../server
#cd www
#php -S 0.0.0.0:8000 > /dev/null &
#cd ../server
cd server
java -Djava.security.policy=server.policy -Djava.rmi.server.codebase=http://localhost:8000/ DynamicServer

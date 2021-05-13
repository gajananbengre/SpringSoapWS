Build Docker Image and push to Docker Hub

mvn clean
mvn install

docker build -t spring_soap_ws_wss4j .
docker run -it -p 8082:8082 spring_soap_ws_wss4j

docker login
docker images
docker tag spring_soap_ws_wss4j <docker_username>/spring_soap_ws_wss4j
docker push <docker_username>/spring_soap_ws_wss4j
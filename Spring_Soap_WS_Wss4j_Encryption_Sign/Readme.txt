Build Docker Image and push to Docker Hub

mvn clean
mvn install

docker build -t spring_soap_ws_wss4j_encryption_sign .
docker run -it -p 8083:8083 spring_soap_ws_wss4j_encryption_sign

docker login
docker images
docker tag spring_soap_ws_wss4j_encryption_sign <docker_username>/spring_soap_ws_wss4j_encryption_sign
docker push <docker_username>/spring_soap_ws_wss4j_encryption_sign
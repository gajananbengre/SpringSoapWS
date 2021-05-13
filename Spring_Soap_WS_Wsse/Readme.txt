Build Docker Image and push to Docker Hub

mvn clean
mvn install

docker build -t spring_soap_ws_wsse .
docker run -it -p 8081:8081 spring_soap_ws_wsse

docker login
docker images
docker tag spring_soap_ws_wsse <docker_username>/spring_soap_ws_wsse
docker push <docker_username>/spring_soap_ws_wsse
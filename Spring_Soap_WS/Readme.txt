Build Docker Image and push to Docker Hub

mvn clean
mvn install

docker build -t spring_soap_ws .
docker run -it -p 8080:8080 spring_soap_ws

docker login
docker images
docker tag spring_soap_ws <docker_username>/spring_soap_ws
docker push <docker_username>/spring_soap_ws
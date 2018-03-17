mvn clean package && docker build -t eldermoraes/javaeedocker .

docker rm -f javaeedocker || true

docker run -d --name javaeedocker \
    -p 8080:8080 \
    eldermoraes/javaeedocker

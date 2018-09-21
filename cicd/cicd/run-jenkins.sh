docker rm -f jenkins || true

docker run -d --name jenkins \
    -p 8888:8080 -p 50000:50000 \
    -v ${HOME}/jenkins_home:/var/jenkins_home \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v $(which docker):/usr/bin/docker \
    -v ${HOME}/.m2:/var/jenkins_home/.m2 \
    jenkins-docker

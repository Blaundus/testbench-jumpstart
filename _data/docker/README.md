# Environment Readme

## Local external IP
The local external IP is used to reach all services from outside of Docker

## Versions

* drone.io version: 8.0.0 - 


## Folder and Files
### Selenoid

#### create the browsers.json

````bash
docker run --rm \
-v /var/run/docker.sock:/var/run/docker.sock \
-v `pwd`/selenoid/:/root/.aerokube/selenoid/ \
aerokube/cm:latest \
selenoid configure \
--tmpfs 128 \
--browsers chrome,firefox,opera,phantomjs \
--last-versions 4
````

To reconfigure the browsers.json, delete the file in the folder
selenoid/browsers.json and container.
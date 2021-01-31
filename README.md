# marvel-characters-ms

Example ms using spring boot and marvel api, It concist in a remote fetching information to keep a daily sync of data,
I'm using spring, scheduler, and mongo as DB.

You can see a web documentation of How-to interact with the service [Here](https://documenter.getpostman.com/view/483767/TW6zFmNd)
## Run using docker and docker-compose
1- Create a `.env` file, copy the content of `env.example`, add the **API_PUBLIC_KEY** and **API_PRIVATE_KEY** values, you can get this values from [Marvel Developers Portal](https://developer.marvel.com.).
- Then, you can run this project with docker-compose using the script `run.sh`, and stop it using `stop.sh`

## Run using assemble.sh and avengers.sh
- You need a mongo db instance running on your computer
- then you can package the project using `assemble.sh`
- You need to set the environment variables required on your OS
- You can run the project using `avengers.sh`

## Enpoints
This app expose mainly two endpoints, to get information associated with Iron Man and Captain America

For example, you can get info of colaborators for a hero using this CURL command
```bash
curl --location --request GET 'http://localhost:80/marvel/colaborators/{{characterNick}}'
```
Fetch related characters related with a hero and the related comics
```bash
curl --location --request GET 'http://localhost:80/marvel/characters/{{characterNick}}'
```

## Importan
The application loads on startup, only info for Iron Man with nick `ironman` and Captain America `capamerica`, **You need to use the nicks on path variables**

- On UNIX, sometimes we will need root access to run an app on port 80, you can change the application.properties to 8080 and also update the Dockerfile and docker-compose.yml
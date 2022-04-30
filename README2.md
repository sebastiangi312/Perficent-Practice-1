Hi!
1. For downloading the images from Docker Hub you should run the following lines in your console:

    docker network create --subnet=122.23.0.0/16 my-network 

    docker run --name my-postgres --network="my-network" --ip 122.23.0.2 -e POSTGRES_PASSWORD=secret -p 5432:5432 -d postgres

    docker run --name backend --network="my-network" --ip 122.23.0.3 -p 8080:8080 -d segiraldovi/my_back

    docker run --name frontend --network="my-network" --ip 122.23.0.4 -p 4200:4200 -d segiraldovi/my_front

2. Then you can go to Docker Desktop and start the images called "backend" and "frontend" (Remember to also run the image of the data base)

3. Finally go to yout browser and write: "localhost:4200" and that's it! you will be able to see the application.



A Spring Boot web application for using Github Search API for finding top repositories sorted by stars and a couple of additional filters.

**Limitations of Github Search API**
1. The Github Search API has a custom rate limit. For requests using Basic Authentication, OAuth, or client ID and secret, you can make up to 30 requests per minute. **For unauthenticated requests, the rate limit allows you to make up to 10 requests per minute.**


## How to run the application
**With Docker**
1. Pull the repository.
2. From the project directory: `./mvnw spring-boot:build-image`
3. `docker run -it -p 8080:8080 --name=top-github-repos-finder top-github-repos-finder:0.0.1-SNAPSHOT`

**Without Docker**

1. Pull the repository.
2. From the project directory: `./mvnw clean install`
3. Run jar file: `java -jar target/top-github-repos-finder-0.0.1-SNAPSHOT.jar`

**Further steps**
Make request to http://localhost:8080/topRepositories?language=java&createdOnOrAfter=2022-05-10&limit=10
Swagger documentation is available at http://localhost:8080/swagger-ui.html


## Implementation TO-DO list

- [x] POC happy path is working.
- [x] Integration and unit tests are written for happy path.
- [x] Swagger documentation is written.
- [ ] Exception handling(timeouts, unparsable input) is implemented for negative test cases. Invalid input erorrs are returned to a user.
- [ ] Logging framework configured.
- [ ] Implement pagination when limit is not specified.
- [x] Application is dockerized.
- [ ] Application is deployed to a cloud(ECS/Elastic Beanstalk).


# FoodLogiQ Events management

## How to run?

### Option 1: Local build with test cases
**Prerequisites**

* Java 8+
* Maven 3.6.3
* Mysql 8

**Steps**

```
FoodLogiQ> mvn clean package
FoodLogiQ> mvn spring-boot:run OR java -jar target/food-logiq-0.0.1-SNAPSHOT.jar
```

* Refer "Access URL" section for next step

### Option 2: Local build without test cases
**Prerequisites**

* Java 8+
* Maven 3.6.3
* Mysql 8

**Steps**

```
FoodLogiQ> mvn clean package -DskipTests=true
FoodLogiQ> mvn spring-boot:run OR java -jar target/food-logiq-0.0.1-SNAPSHOT.jar
```

* Refer "Access URL" section for next step

### Option 3: Local docker build
**Prerequisites**

* Docker

**Steps**

```
/**To Start Docker:**/
FoodLogiQ> ./run.sh start

/**To Stop Docker:**/
FoodLogiQ> ./run.sh stop

/**To Restart Docker:**/
FoodLogiQ> ./run.sh restart
```

* Refer "Access URL" section for next step

### Option 4: Existing docker image
**Prerequisites**

* Docker
	
**Steps**

```
FoodLogiQ> ./run.sh run_existing_image
```

* Refer "Access URL" section for next step

## Access URL
  Open a browser and access the following URL
  
  http://localhost:8080/swagger-ui.html OR http://(<docker_host_ip>):8080/swagger-ui.html
	
## Access Information
* MySQL container:
    * hostname: mysql_db
    * Ports : 3306:3306 (<host_port>:<container_port>)
    * Username/Password: admin/admin@123
  
      * FoodLogiQ-service:
          * hostname: event_service
          * Ports: 8080:8080
          * URL: http://localhost:8080 OR http://(<docker_host_ip>):8080

## Extended implementation

* updatedBy, updatedAt fields are included to support update feature, in future

* User details stored in the database, to accommodate more users in future

## Folder Structure

        src
        ├───main/
        │   ├───java/
        │   │   └───com/
        │   │       └───food/
        │   │           └───logiq/
        │   │               ├───base/
        │   │               │   └───api/
        │   │               │       ├───BaseBodyError.java                # API response error class    
        │   │               │       └───BaseBodyResponse.java             # API response class
        │   │               ├───config/                                   # Application configuration classes
        │   │               │   ├───Config.java                           # Config class for validating the http request object
        │   │               │   ├───SwaggerConfigProperties.java          # SwaggerConfigProperties class to load swagger properties from application.properties file
        │   │               │   ├───SwaggerConfiguration.java             # Swagger2 configuration
        │   │               │   ├───WebConfig.java                        # Inteceptor and resource handler configuration
        │   │               │   └───WebSecurityConfiguration.java         # CORS and security configurations
        │   │               ├───interceptor/                              
        │   │               │   └───APIInterceptor.java                   # Intercept all API request and validate the user and token
        │   │               ├───repositories/                             # All JPA repositories(database), entity, enums
        │   │               │   ├───entity/
        │   │               │   │   ├───ContentEntity.java                # Entity class for contents
        │   │               │   │   ├───EventEntity.java                  # Entity class for Events
        │   │               │   │   ├───GtinLotUniqueKey.java             # Unique identity class for Gtin and lot
        │   │               │   │   └───UserEntity.java                   # Entity class for users
        │   │               │   ├───enums/
        │   │               │   │   └───EventType.java                    # Enum for event type
        │   │               │   ├───ContentRepository.java                # JPA repository interface for writing content related queries
        │   │               │   ├───EventRepository.java                  # JPA repository interface for writing event related queries
        │   │               │   └───UserRepository.java                   # JPA repository interface for writing user related queries
        │   │               ├───restapi/                                  # All the rest API modules
        │   │               │   ├───events/                               # Events related APIs   
        │   │               │   │   ├───api/
        │   │               │   │   │   ├───request/                      # API request data models
        │   │               │   │   │   │   ├───Content.java              # Event contents mapping from http request; field validations 
        │   │               │   │   │   │   └───EventRequest.java         # Event mapping from http request; field validations
        │   │               │   │   │   └───response/                     # API response data models
        │   │               │   │   │       ├───EventListResponse.java    # Response data model for event list
        │   │               │   │   │       ├───EventResponse.java        # Response data model for specific event
        │   │               │   │   │       └───MessageResponse.java      # Response data model for message response
        │   │               │   │   ├───EventController.java              # All Event API endpoints implementation            
        │   │               │   │   ├───EventMapper.java                  # Mapping request model to entity and entity to response model for events 
        │   │               │   │   └───EventService.java                 # Events database CRUD operations along with business logic 
        │   │               │   └───users/                                # User related APIs
        │   │               │       └───UserService.java                  # User database READ operation
        │   │               ├───util/                                     # Application constants & exception classes
        │   │               │   ├───exception/                            
        │   │               │   │   ├───ConflictException.java            # Exception class for conflict data
        │   │               │   │   ├───CustomException.java              # Custom exception class for API response error
        │   │               │   │   ├───GlobalExceptionHandler.java       # Global exception handler catch all the exception in this class
        │   │               │   │   ├───NotFoundException.java            # Exception class for data not found
        │   │               │   │   └───UnauthorizedException.java        # Exception class for unauthorized API request
        │   │               │   ├───Constants.java                        # Application constants are defined in this class
        │   │               │   └───ErrorCodes.java                       # Application error codes are defind in this class
        │   │               └───Application.java
        │   └───resources/
        │       ├───application-dev.properties
        │       ├───application.properties
        │       └───messages.properties
        └───test/     

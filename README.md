Backbone of whole job market place application, utilizing endpoints for data retrieval, storage and deletion as well as user authentication

BACKEND ENDPOINTS:

Accessible both with and without authorization token inside the HTTP header:
```java
AuthController (mapped by "/auth"):
    @PostMapping("/signin")
         //receives login credentials in request body and validates them, if credentials match an user in database, returns valid JWT
JobListingController (mapped by "/job-listing"):
    @GetMapping("/all")
        //retrieves all store JobListings
    @GetMapping("/{id}")
    @PathVariable final Long id
        //finds JobListing in database with matching ID
```
Accessible only while logged in:
```java
AppUserController (mapped by "/app-user"):
    @GetMapping("/{id}")
    @PathVariable final Long id
        //retrieves all listings posted by user with given ID

JobListingController (mapped by "/job-listing"):
    @PutMapping("/create")
    @RequestHeader (name="Authorization") String token, @RequestBody @Validated final JobListingInputDto input
        //takes JobListingInputDto received in request body, to create a new JobListing record in database

    @GetMapping("/all-not-owned")
    @RequestHeader (name="Authorization") String token,  @RequestParam("page") int page,@RequestParam("sort") String sortBy
        //retrieves all stored JobListings whose posterID does not match the one extracted from token, offers paging and sorting options

    @GetMapping("/owned")
    @RequestHeader (name="Authorization") String token
        //retrieves all store JobListings whose posterID matches the one given

    @DeleteMapping("/remove")
    @RequestHeader (name="Authorization") String token, @RequestBody final String jobId
        //deletes JobListing in database where jobId matches the one given

UsersInterestedInJobController (mapped by "/job-interest"):
    @PutMapping("/create")
    @RequestHeader(name = "Authorization") String token, @RequestBody @Validated final UsersInterestedInJobDto input
        //takes UsersInterestedInJobDto received in request body, to create a new UserInterestedInJob record in database

    @GetMapping("/user")
    @RequestHeader(name = "Authorization") String token, @RequestParam("page") int page, @RequestParam("sort") String sortBy
        //retrieves all stored UserInterstedInJob records whose posterId matches the one extracted from token, offers paging and sorting options

    @GetMapping("/already-interested")
    @RequestHeader(name = "Authorization") String token
        //retrieves IDs of JobListings that the user is interested in

    @DeleteMapping("/remove")
    @RequestHeader (name="Authorization") String token, @RequestBody final String interestId
        //removes record from database where its ID matches the one sent and poster is user currently logged in
```
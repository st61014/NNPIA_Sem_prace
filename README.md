Simple job market place where users can post their job listings, and other users can show interest, all in SPA model

FRONTEND ROUTES:
Accessible both while not logged in and logged in:
```java
"/job-listings"
    - shows all listings to users who are not logged in
    - shows all listings except the ones owned by logged in user
      logged in user is able to click interest to show interest in listing
"/login"
    -displays form and allows user to input credentials,if valid, log him in and set JWT
```
Accessible only when logged in:
```java
"/job-listing/create"
    - display form to create a job listing
"/job-interests"
    - shows all listings the user is interested in, with the option to stop being interested
"/job-listings/owned"
    - shows all listings owned by current logged user, with the option to delete them
      which also cancels any existing interests tied to the listing
```
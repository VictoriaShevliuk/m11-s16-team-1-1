# m11-s16-team-1-1
Add to the Web application from the previous sprint a Security Level, which includes user Authentication and Authorization.

Implemented security level should include the next scenarios:

1. If the user has not yet logged in, then after visiting any page redirect him to the login page, which is available at URL "/form-login". The login form should include fields for entering login and password, and the button “LogIn”. As the “username” for login, use the user’s email.
2. If the user entered the invalid credentials, then redirect him back to the login page with error information.
3. If the user's login and password was entered correctly, then allow him access to web application according to his role:

    3.1. If the role of the user is ADMIN, then allow him full access to all resources of the web application.
    3.2. If the role of the user is USER, then:

After going to the “All To-Dos of …” page, display for user the list of To-Dos  where he is owner or collaborator. If user does not owner To-Do, then he isn't allowed  to delete or edit this To-Do.
► User can view any To-Dos where he is owner or collaborator.
► Prohibit for user add collaborators  to To-Do if he does not owner this To-Do.
► In case of an attempt unauthorized access to forbidden URL, redirect the user to the “Access Denied” page and set the status code to 403.
4. If the user clicks the "LogOut" button, then end the user session and redirect him to the login page.

For store user credentials use “users” and “roles” tables from database.

The user password should encoded using the BCrypt encryption algorith.

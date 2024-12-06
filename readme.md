# SYSC4806 Group Project
Application: https://surveymonkey-be674bf9e661.herokuapp.com/api/surveys/home
## What is Surveyor?

Surveyor is a survey creation application that allows users to create, answer, and generate important data based off 
of data provided by other users. Surveyor's functionality consists of 3 major components:

1. Survey Creation
2. Survey Viewing
3. Survey Answering

### Survey Creation
Here, admin-users are able to create customized surveys for others.  Surveys have a variety of customizable options, allowing 
the survey to fulfill a whatever needs you may have.  The first customizable option, is the ability to support 3 different
formats of questions:
1. Multiple Choice
    - Multiple Choice questions allow the user to select one of many pre-established answers, this is great for limiting the answers a user can choose to a selection of procured options.
2. Numeric Range
    - Numeric Range questions allow the user to select a number within a specified range, featuring a min and max value, this question type is perfect for responses that exist on a spectrum, for example rating a score of a user's opinion on a product.
3. Open Text
    - Open Text questions allow the user to input any open-ended text answers they'd like, perfect for receiving feedback or hearing what users really have to say.

Additionally, surveys offer the ability to be toggled as anonymous or not, ensuring that users can only access the content of a survey
if they are a signed in to their Surveyor account.  Individual questions can also be marked as required or not, allowing the user the
option to leave questions empty if they don't wish to answer.  Lastly, surveys can be given an expiry date and time, if this is set, 
the survey will automatically be closed, and become unanswerable after the specified time, perfect for time-sensitive surveys.
### Survey Viewing
Survey viewing is eligible to any admin-user who has created a survey.  Here, users can see a list of all the surveys they created,
with different options to control them:
1. Open/Close
    - This option allows the user to open or close a survey, if a survey is open, users can answer it, if it is closed, users cannot.
2. Edit
    - This option allows the user to edit the survey, changing any of the options they set when they created it.
3. Generate Report
    - This option allows the user to generate a report of the survey, this report has a variety of graphics for gaining insight from survey answers, including:
      - A pie chart for multiple choice questions
      - A histogram for numeric range questions
      - A list of answers for text questions
4. Delete
    - This option allows the user to delete the survey, removing it from the database.
   
Additionally, the user is able to filter their surveys, sorting them by open/closed status, or filtering by name.
### Survey Answering
Survey answering is eligible to any user.  Here, users can see a list of all open surveys, can search through them, including filtering
by name, and answer them.  Upon answering a survey, the user is brought to a page where they can answer each question, and submit their
answer for the owner to view.  If a survey is closed, it will not be visible to the user, and they will not be able to answer it.  Additionally, 
non-anonymous surveys will require the user to be signed in to their account to see or answer them.  

## Other Features
Surveyor also features a small-scale user system, allowing users to sign up, and sign in to their account.  While the user system is
relatively simple, it interacts with the above systems as described, such as allowing admin-users to create surveys and view them, 
as well as interacting with anonymous surveys.

## Other Project Components
Outside the scope of the application, the project also features a variety of other components, including:
1. A CI/CD pipeline created using GitHub Actions, which automatically runs tests, and deploys the application to Heroku.
2. A variety of tests, including unit tests, and integration tests automatically run by the CI/CD pipeline.
3. A variety of documentation, including a README, and a UML diagrams documenting the project's structure.
4. A history of weekly scrum meetings, documented through GitHub issues.
5. A Kanban board, designed to track the project's progress, and organize tasks.


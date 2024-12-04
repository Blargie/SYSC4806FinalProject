# SYSC4806 Group Project
Application: https://surveymonkey-be674bf9e661.herokuapp.com/api/surveys/home
### What is Surveyor?

Surveyor is a survey creation application that allows users to create, answer, and generate important data based off of data provided by other users.
Surveyor's functionality consists of 3 major components:
1. Survey Creation
2. Survey Viewing
3. Survey Answering

### Survey Creation
Here, users are able to create customized surveys for others to answer.  Surveys can be given names and descriptions to describe the purpose of the survey they are creating.  Additionally, the most important part of the survey, the questions, are fully customizable by the user.  Currently, Surveyor supports 3 different formats for questions:
1. Text Questions, a question that prompts the user with an open-ended text based response
2. Multiple Choice Questions, a question that prompts the user with selecting from a series of options
3. Numeric Range Questions, a question that prompts the user to select a score within an upper and lower bound (i.e. a scale from 1-10)
Surveys are also able to be given an expiration date/time where the survey will automatically close, preventing users from adding to the survey, as well as the option to support "anonymouse responses" from users without accounts (see user functionality below)

#### Next Steps
We have a lot of ambitious ideas to add to the survey creation component, with our 3 largest being:
1. Single response/multiple responses (with users)
2. Additional question types/formats
3. Mandatory/optional questions

### Survey Viewing
Here, users are able to view their information about their created surveys.  They are also able to open or close the survey, allowing or preventing other users from answering the survey whenever they want.

#### Next Steps
We have a lot of ambitious ideas to add to the survey viewing component, with our 2 largest being:
1. The ability to edit pre-existing surveys
2. The ability to generate survey results in an easy-to-digest form, through graphs and other visual aides

We also plan to expand on survey viewing with our user functionality (discussed below)

### Survey Answering
Here, users are able to view surveys created by others, and answer them.  Upon selecting a survey to answer, the user is able to view the survey's information, such as the name, description, and read the questions.  The user is able to enter information into the survey using the different fields provided based off of the questions added during creation.  Upon submission, answers are stored for the survey owner to view later.

#### Next Steps
While we don't have much additional features for answering specifically, survey answering is directly connected to both creation/viewing that it will make the experience more well rounded (see Next Steps under Survey Creation/Survey Viewing)

### Large New Feature - User Functionality
Our biggest plan for Milestone 3 is the added functionality of supporting user identities.  Users can create accounts or login to provide an identity when answering surveys.  Additionally, we plan to make this a mandatory feature for survey creation/viewing.  Currently, many features have been designed with this feature in mind, but without the feature currently operating some things aren't fully functional (anonymous mode, survey viewing sees all surveys, etc.).  With the addition of the user features, these issues will be resolved, for the full release of the application.

### Additional Plans - PostgREST implementation
In accordance with the SYSC 4806 presentation, we plan to implement our vision of "RESTful CRUD out-of-the-box with PostgREST or SQLer" by leveraging PostgREST, an API that allows you to directly route HTML Get/Post requests to queries on a PostgreSQL database.  This will involve transferring over from the H2 database used currently into a new PostgreSQL database schema.

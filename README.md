<h1 align="center">﻿JAVA Quiz Application</h1>
<h1 align="left">Project Overview</h1>

#### [project link](https://github.com/vishalsinghalop/java-project-1)

The Quiz Application is a Java-based desktop application that allows users to take interactive quizzes on various topics. It fetches questions from the Open Trivia Database API and presents them with objective type questions. The application features a user `login/sign-up` system, dynamic `category` selection, and a `leaderboard` to track high scores of various users.

### Team Members
- [Lakshya Poonia](https://github.com/itz-lakshya)
- [Vishal Singhal](https://github.com/vishalsinghalop)
- [Rakesh Balara](https://github.com/RickiChaudhary)

  
### Project Description
This project demonstrates the following functionalities:
- **User Authentication**: Users can sign up and log in to access the quiz.
- **Quiz Mechanics**: Questions are fetched from an external API and displayed with objective type questions.
- **Category Selection**: Users can choose from different quiz categories like Sports, Movies, etc.
- **Timer and Scoring**: Each question is timed (15 seconds) and correct answers earn 10 points. Scores are saved and displayed in a leaderboard.
- **User Friendly UI**: The application uses Java Swing for visually appealing interface, with custom animations and transitions.

### Libraries and Technologies Used
- **Java Swing & AWT**: For building the graphical user interface (GUI).
- **OkHttp**: For making HTTP requests to the Open Trivia API.
- **org.json**: For parsing JSON responses.
- **Maven**: For project management and dependency handling.
- **JavaFX**: For additional UI enhancements.
- **Java File I/O**: For storing user data in files.

### How to Run the Project
#### Prerequisites
- Java Development Kit (JDK) 22 or later.
- Maven installed on your system.
- An internet connection (to fetch quiz questions from the API).

#### Steps
1. Open the Project in IDE
   - Open IntelliJ IDEA or Eclipse.
   - Click File → Open and select the project folder.
2. Load Dependencies
   - Ensure Maven is installed.
   - In IntelliJ: Open the Maven tab → Reload Project
   - In Eclipse: Right-click pom.xml → Run As → Maven install
3. Run the Application
   - Locate the login.java file.
   - Right-click and select Run.

### Screenshots / Video
- [Video Demonstration](https://drive.google.com/file/d/1R53MrWNKPIicI7ObOzYCspPADYSfzAhn/view?usp=share_link)

### Individual Contribution

1. Lakshya Poonia:
   - **API Integration**: Fetched quiz questions and categories dynamically using HTTP requests.
   - **Question Processing**: Parsed and formatted API responses for compatibility.
   - **Category Selection**: Implemented logic for users to choose quiz topics.
   - **Tech Stack Used**: Java (java.net.HttpURLConnection, java.io.BufferedReader), JSON Parsing (org.json.JSONObject)

2. Vishal Singhal:
   - **User Authentication**: Implemented login and signup system.
   - **Local Storage**: Stored user credentials in .txt files for authentication.
   - **Session Handling**: Ensured users remain logged in throughout the session.
   - **Tech Stack Used**: Java (java.io.FileWriter, java.io.FileReader, java.util.Scanner), Java Swing (JTextField, JPasswordField)



3. Rakesh Balara:
   - **UI/UX Enhancements**: Designed quiz interface layout for better user experience.
   - **Testing & Debugging**: Assisted in testing API responses and authentication functionality.
   - **Error Handling**: Helped in implementing basic error handling for smooth user interaction.
   - **Tech Stack Used**: Java Swing (JFrame, JPanel, JButton), Debugging.

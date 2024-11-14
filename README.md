8# Echo Face Recognition

Echo Face Recognition is a real-time facial recognition system built with Java and JavaFX. This project leverages OpenCV to detect faces and perform dynamic image retrieval based on real-time facial detection. Version 2 of the project includes future plans for implementing threat detection, allowing the system to track wanted individuals for law enforcement purposes.

## Features

- **Real-time Face Detection**: Detect faces from live video feed.
- **Dynamic Image Retrieval**: Automatically matches and retrieves images based on facial recognition.
- **Future Plans for Threat Detection**: Proposed feature to track and alert on specific individuals of interest.

## Technologies Used

- **Java**: Core programming language.
- **JavaFX**: Used for GUI development.
- **OpenCV (v4.10.0)**: Computer vision library for facial recognition.
- **MySQL**: Database for storing and retrieving face data.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- **Java JDK** 8 or higher
- **JavaFX SDK** installed
- **OpenCV (v4.10.0)** installed and linked
- **MySQL Database** for data storage
- **Eclipse IDE** (optional, but recommended for Java development)

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/Echo-Face-Recognition.git
   cd Echo-Face-Recognition

2. Set Up MySQL Database:

Create a new MySQL database (e.g., face_recognition_db).

Import the database schema from the provided SQL file in the database/ directory.

Update the database connection details in the Java source code if necessary.



3. Configure OpenCV:

Download and set up OpenCV v4.10.0.

Link OpenCV library files to the project.



4. Run the Project:

Open the project in Eclipse or another Java IDE.

Run the main application file (Main.java) to start the face recognition.




Usage

1. Start the Application:

Launch the application to access the main interface.



2. Real-time Face Recognition:

The system will begin detecting and recognizing faces in the video feed.

If a face is recognized, it will retrieve the corresponding images from the database.



3. Threat Detection (Future):

This feature aims to alert on detected individuals of interest, though it is planned for future development.




Project Structure

Echo-Face-Recognition/
├── src/
│   ├── main/
│   ├── recognition/
│   └── database/
├── database/
└── README.md

Contributing

Contributions are welcome! Please fork this repository and create a pull request for any enhancements or bug fixes.

License

This project is licensed under the MIT License - see the LICENSE file for details.

Future Goals

Implement threat detection to help with law enforcement applications.

Integrate additional biometric features to improve recognition accuracy.

Enhance the GUI for improved usability.


Acknowledgments

OpenCV - For providing powerful computer vision tools.

JavaFX - For making GUI development seamless.


---


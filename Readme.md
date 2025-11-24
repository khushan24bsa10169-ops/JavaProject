# 🏥 Clinic Record System

![Java](https://img.shields.io/badge/Language-Java-orange.svg)
![Status](https://img.shields.io/badge/Status-Active-green.svg)


> **A Vityarthi Project** > A robust Java-based application designed to streamline daily clinic operations using Object-Oriented principles and File Persistence.

---

## 📖 Overview

The **Clinic Management System** acts as a digital receptionist for healthcare clinics. It replaces manual paper records with a comprehensive digital solution. 

Unlike complex database-driven apps, this project utilizes **Java Serialization** to ensure data persistence without the need for external SQL servers—making it lightweight and portable.

**Key capabilities include:**
* Registering and managing patient profiles.
* Scheduling and tracking appointments.
* **Automatic Data Persistence:** Saving records to local storage (`.dat` files) so no data is lost when the application closes.

## 🧠 System Architecture

The following diagram illustrates how the application manages data flow from the user interface to the local file storage.

```mermaid
graph TD
    subgraph Interface
    User((User)) -->|Console Input| Main[MainApplication.java]
    end

    subgraph Logic Layer
    Main -->|Invokes| Manager[ClinicManager.java]
    Manager -->|Instantiates| P[Patient Object]
    Manager -->|Instantiates| A[Appointment Object]
    end

    subgraph Persistence Layer
    Manager -->|Serialization| D1[(patients.dat)]
    Manager -->|Serialization| D2[(appointments.dat)]
    D1 -.->|Deserialization| Manager
    D2 -.->|Deserialization| Manager
    end

    style Main fill:##000000,stroke:#333,stroke-width:2px
    style Manager fill:##000000,stroke:#333,stroke-width:2px
    style D1 fill:##000000,stroke:#333,stroke-width:2px
    style D2 fill:##000000,stroke:#333,stroke-width:2px
````

## ⚙️ Key Features

  * **👨‍⚕️ Patient Management** Add new patient details (Name, Age, Contact, Symptoms) and maintain a digital registry.
  * **📅 Appointment Scheduling** Book new appointments and link them efficiently to registered patients.
  * **💾 Data Persistence** Uses `ObjectOutputStream` and `ObjectInputStream` to store data in `patients.dat` and `appointments.dat`.
  * **🔍 View Records** Browse through patient history and upcoming appointments via the console interface.

## 🛠️ Tech Stack

| Category | Technology Used |
| :--- | :--- |
| **Language** | Java (JDK 8+) |
| **Core Concepts** | OOP, Collection Framework (ArrayList/HashMap), Exception Handling |
| **Persistence** | File I/O & Java Serialization |
| **Version Control** | Git & GitHub |

## 📂 Project Structure

```text
Clinic/
├── src/
│   ├── MainApplication.java   # Entry point; handles user menu
│   ├── ClinicManager.java     # Business logic (add, save, load methods)
│   ├── Patient.java           # Model class (implements Serializable)
│   └── Appointment.java       # Model class (implements Serializable)
├── data/
│   ├── patients.dat           # Binary file storing patient records
│   └── appointments.dat       # Binary file storing appointment records
├── screenshots/               # App previews
└── README.md                  # Project documentation
```

## 🚀 Getting Started

Follow these instructions to set up the project on your local machine.

### Prerequisites

  * **Java Development Kit (JDK)** installed (Version 8 or higher).
  * A terminal or IDE (VS Code, IntelliJ IDEA, Eclipse).

### Installation & Run

1.  **Clone the Repository**

    ```bash
    git clone [https://github.com/Aman35256/Clinic.git](https://github.com/Aman35256/Clinic.git)
    cd Clinic
    ```

2.  **Compile the Source Code**

    ```bash
    javac *.java
    ```

3.  **Run the Application**

    ```bash
    java MainApplication
    ```

## 🔮 Future Scope

To further enhance this project, the following features are planned:

  * [ ] **Database Integration:** Replace file handling with MySQL/PostgreSQL for high-volume scalability.
  * [ ] **GUI Implementation:** Build a user-friendly interface using JavaFX or Swing.
  * [ ] **Authentication:** Add login functionality for distinct roles (Doctor vs. Receptionist).
  * [ ] **Prescriptions:** Allow doctors to generate and print digital prescriptions.

## 🤝 Contributing

Contributions are welcome\! Please fork the repository and create a pull request.

1.  Fork the Project
2.  Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the Branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request




Made with Love❤️❤️ by Khushan



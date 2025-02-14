# MCQ-Based Quiz Mobile App 

This is an **MCQ-Based Quiz App** built using **Android (Java)** as part of an assignment. The app features:
- A **Splash Screen** with animations.
- A **Quiz Interface** with multiple-choice questions.
- **Explicit & Implicit Intents** for seamless navigation.

---

## 🎯 Objective
The objective of this app is to demonstrate:
- **Splash Screen with Animations**
- **Activities & Navigation**
- **Explicit & Implicit Intents**

---

## 🚀 Features
### 1️⃣ Splash Screen with Animations
✅ A **Splash Screen** appears when the app is launched.

✅ Includes animations (fade, scale, or translate).

✅ Navigates automatically to the **Main Quiz Screen** after 2-3 seconds.

### 2️⃣ Quiz Interface
#### **MainActivity (Start Screen)**
✅ A text field to **enter the user’s name**.

✅ A **Start Quiz** button that navigates to **QuizActivity** using an **Explicit Intent**.

#### **QuizActivity (MCQ Screen)**
✅ Displays **one MCQ at a time** with **four answer options**.

✅ Includes "Previous" & "Next" buttons:
   - **Next Button**: Moves to the next question.
   - **Previous Button**: Moves to the previous question (disabled on the first question).
     
✅ **Tracks score** and shows the **final result** after the last question.

### 3️⃣ Explicit & Implicit Intents
✅ **Explicit Intent**: Used to navigate between activities.
   - `MainActivity` → `QuizActivity` (Passes user's name via Intent Extra).

✅ **Implicit Intent**: Used to share the quiz score after completion.
   - "Share Score" button allows users to **share results** via WhatsApp, Gmail, etc.

---

## 🔧 Setup & Installation
1. **Clone the repository**:
   ```sh
   git clone https://github.com/faizan-zahid10/Quiz_Khelo.git
   ```
2. **Open in Android Studio**.
3. **Run the app** on an emulator or a real device.

---

## 📩 Contact
For any queries, feel free to reach out!

📧 **Email**: faizan1000444@gmail.com

🔗 **GitHub**: https://github.com/faizan-zahid10


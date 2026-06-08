# EncryptedNotepad – User Guide & Tutorial 📖

This guide will walk you through how to use **EncryptedNotepad** to secure your sensitive information, step-by-step.

## 1. Creating and Saving a Secure Note

Follow these steps to write a new note and save it securely on your Windows machine:

1. Open the application. You will be greeted with a blank text area.
2. Type or paste your sensitive information (e.g., account passwords, banking PINs, or private keys).
3. Navigate to the top menu and click **File** > **Save / Save As**.
4. A secure dialog box will pop up asking you to **"Create a PIN/Password to secure this file"**. Type your secret key. The characters will be automatically masked (`*****`) for your privacy. Click **OK**.
5. A standard Windows File Explorer window will open. Choose where you want to store the file, give it a name (e.g., `my_passwords.txt`), and click **Save**.
6. Once saved successfully, a confirmation popup will appear, and the screen will automatically clear itself to keep your data safe from wandering eyes.

---

## 2. Opening and Decrypting an Existing File

To read or edit a file that you previously encrypted:

1. Open the application and click **File** > **Open File**.
2. Select your encrypted text file from the Windows File Explorer window and click **Open**.
3. The application will detect that the file is encrypted and prompt you with a dialog box: **"Enter PIN/Password to open this file"**.
4. Type the exact key you used when creating the file and click **OK**.
* 🔑 **If the password is correct:** Your original plain text will instantly appear on the screen.
* ❌ **If the password is incorrect:** An **"Access Denied"** error message will pop up, and the text area will remain empty or hidden.



---

## 3. In-App Encryption & Decryption (On-the-Fly)

If you just want to encrypt or decrypt text directly on your screen without creating or opening a physical file, you can use the **Security** menu:

* **To Encrypt Tweak on Screen:** Type any text, go to **Security** > **Encrypt Text (In-App)**, enter a password, and your text will instantly transform into unreadable gibberish (*ciphertext*) on your screen.
* **To Decrypt Tweak on Screen:** Paste an encrypted gibberish text into the notepad, go to **Security** > **Decrypt Text (In-App)**, enter the correct password, and it will turn back into readable text.

---

## 4. Safely Clearing the Screen

To completely wipe the current text off your screen without saving:

1. Click **Edit** > **Clear Text**.
2. A security prompt will appear asking for a PIN/Password. This ensures unauthorized users cannot maliciousy wipe out what you are working on.
3. Enter your key, click **OK**, and a final confirmation dialog (**"Are you sure you want to delete all notes?"**) will appear.
4. Click **Yes**, and the notepad area will reset back to a clean slate.

---

## ⚠️ Critical Security Rules to Remember

* **Do Not Forget Your Password:** Because this application uses strong AES-128 encryption locally, **there is no "Forgot Password" or account recovery option.** If you lose the key, your data cannot be recovered.
* **Shoulder-Surfing Protection:** Always make sure no one is watching your screen when utilizing the text-area, even though your password inputs are fully hidden by the system's `*****` filters.

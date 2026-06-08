import javax.swing.*;
import java.io.*;

public class FileManager {
    
    // Method untuk menyimpan teks ke dalam file di Windows
    public static void saveToFile(JFrame parentFrame, String textToSave) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan Catatan Rahasia");
        
        // Memunculkan dialog Save
        int userSelection = fileChooser.showSaveDialog(parentFrame);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            
            // Menggunakan FileWriter untuk menulis teks ke file (Konsep I/O)
            try (FileWriter fw = new FileWriter(fileToSave)) {
                fw.write(textToSave);
                JOptionPane.showMessageDialog(parentFrame, "Catatan berhasil disimpan aman di:\n" + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(parentFrame, "Gagal menyimpan file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method untuk membaca isi file dari Windows
    public static String openFromFile(JFrame parentFrame) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Buka Catatan Rahasia");
        
        // Memunculkan dialog Open
        int userSelection = fileChooser.showOpenDialog(parentFrame);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            StringBuilder content = new StringBuilder();
            
            // Menggunakan BufferedReader untuk membaca file baris per baris
            try (BufferedReader br = new BufferedReader(new FileReader(fileToOpen))) {
                String line;
                while ((line = br.readLine()) != null) {
                    content.append(line).append("\n");
                }
                return content.toString(); // Mengembalikan isi file
            } catch (IOException e) {
                JOptionPane.showMessageDialog(parentFrame, "Gagal membuka file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null; // Mengembalikan null jika user membatalkan pilihan
    }
}
import javax.swing.*;
import java.awt.*;

public class NotepadUI extends JFrame {
    private JTextArea textArea;
    private JMenuItem menuOpen, menuSave, menuSaveAs, menuClear, menuEncrypt, menuDecrypt;

    public NotepadUI() {
        setTitle("EncryptedNotepad");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setMargin(new Insets(10, 10, 10, 10)); 
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        menuOpen = new JMenuItem("Open File");
        menuSave = new JMenuItem("Save / Save As");
        fileMenu.add(menuOpen);
        fileMenu.add(menuSave);

        JMenu securityMenu = new JMenu("Security");
        menuEncrypt = new JMenuItem("Encrypt Text (In-App)");
        menuDecrypt = new JMenuItem("Decrypt Text (In-App)");
        securityMenu.add(menuEncrypt);
        securityMenu.add(menuDecrypt);

        JMenu editMenu = new JMenu("Edit");
        menuClear = new JMenuItem("Clear Text");
        editMenu.add(menuClear);

        menuBar.add(fileMenu);
        menuBar.add(securityMenu);
        menuBar.add(editMenu);
        setJMenuBar(menuBar);

        setupActionListeners();
    }

    private void setupActionListeners() {
        // --- TOMBOL CLEAR (Sekarang dengan proteksi PIN) ---
        menuClear.addActionListener(e -> {
            if (textArea.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Layar sudah kosong.");
                return;
            }

            // Memanggil method input tersensor *****
            String pin = getPasswordInput("Masukkan PIN/Password untuk menghapus teks:");

            if (pin != null && !pin.trim().isEmpty()) {
                // Di sini kamu bisa menambahkan logika verifikasi PIN.
                // Sebagai contoh, kita izinkan hapus jika PIN tidak kosong.
                // Jika ingin lebih aman, bandingkan 'pin' dengan password yang digunakan saat enkripsi.
                
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "Apakah Anda yakin ingin menghapus semua catatan?", 
                    "Konfirmasi Terakhir", JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    textArea.setText("");
                    JOptionPane.showMessageDialog(this, "Catatan telah dibersihkan.");
                }
            } else if (pin != null) {
                JOptionPane.showMessageDialog(this, "PIN tidak boleh kosong!", "Peringatan", JOptionPane.ERROR_MESSAGE);
            }
        });

        menuSave.addActionListener(e -> {
            String plainText = textArea.getText();
            if (plainText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tidak ada teks yang bisa disimpan!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // MENGGUNAKAN METHOD BARU YANG TERSENSOR
            String secretKey = getPasswordInput("Buat PIN/Password untuk mengamankan file ini:");
            
            if (secretKey != null && !secretKey.trim().isEmpty()) {
                try {
                    String encryptedText = CryptoManager.encryptText(plainText, secretKey);
                    FileManager.saveToFile(this, encryptedText);
                    textArea.setText(""); 
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Gagal mengenkripsi: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        menuOpen.addActionListener(e -> {
            String encryptedContent = FileManager.openFromFile(this);
            
            if (encryptedContent != null && !encryptedContent.trim().isEmpty()) {
                // MENGGUNAKAN METHOD BARU YANG TERSENSOR
                String secretKey = getPasswordInput("Masukkan PIN/Password untuk membuka file ini:");
                
                if (secretKey != null && !secretKey.trim().isEmpty()) {
                    try {
                        String plainText = CryptoManager.decryptText(encryptedContent.trim(), secretKey);
                        textArea.setText(plainText);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Password SALAH atau file rusak!", "Akses Ditolak", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        menuEncrypt.addActionListener(e -> {
            String plainText = textArea.getText();
            String secretKey = getPasswordInput("Masukkan Password untuk Enkripsi:");
            if (secretKey != null && !secretKey.trim().isEmpty()) {
                try {
                    String encryptedText = CryptoManager.encryptText(plainText, secretKey);
                    textArea.setText(encryptedText);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            }
        });

        menuDecrypt.addActionListener(e -> {
            String encryptedText = textArea.getText();
            String secretKey = getPasswordInput("Masukkan Password untuk Dekripsi:");
            if (secretKey != null && !secretKey.trim().isEmpty()) {
                try {
                    String plainText = CryptoManager.decryptText(encryptedText.trim(), secretKey);
                    textArea.setText(plainText);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Password Salah!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Method baru untuk input tersensor *****
    private String getPasswordInput(String promptMessage) {
        JPasswordField passwordField = new JPasswordField();
        Object[] message = { promptMessage, passwordField };
        
        int option = JOptionPane.showConfirmDialog(this, message, "Keamanan EncryptedNotepad", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (option == JOptionPane.OK_OPTION) {
            return new String(passwordField.getPassword());
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NotepadUI().setVisible(true));
    }
}
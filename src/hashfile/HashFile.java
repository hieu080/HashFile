
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashfile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Scanner;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author HELLO
 */
public class HashFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  throws NoSuchAlgorithmException{
        System.out.println("Path to file:");
        Scanner sc = new Scanner(System.in);
        String filePath = sc.nextLine(); // Đặt đường dẫn tới tệp tin của bạn

        try {
            MessageDigest md = MessageDigest.getInstance("SHA3-512");

            // Mở tệp tin dưới dạng FileInputStream
            InputStream is = new FileInputStream(filePath);

            byte[] buffer = new byte[1024];
            int bytesRead;

            // Đọc từ tệp tin và cập nhật băm
            while ((bytesRead = is.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }

            // Hoàn thành quá trình băm và lấy giá trị băm
            byte[] hashBytes = md.digest();

            // In giá trị băm (dưới dạng chuỗi hex)
            StringBuilder hashStr = new StringBuilder();
            for (byte b : hashBytes) {
                hashStr.append(String.format("%02x", b));
            }
            System.out.println("Hash: " + hashStr.toString());

            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

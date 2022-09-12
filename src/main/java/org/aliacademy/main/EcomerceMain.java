package org.aliacademy.main;

import org.aliacademy.handle.OrderService;
import org.aliacademy.handle.ProductService;

import java.util.Scanner;

public class EcomerceMain {
    public static void main(String[] args) {
        int yourCustomerNumber = 112;
        menu(yourCustomerNumber);
    }

    private static void menu(int yourCustomerNumber) {
        boolean isExit = false;
        do {
            int functionChoice = functionChoice();
            switch (functionChoice) {
                case 1 :
                    ProductService.listProduct();
                    break;
                case 2 :
                    ProductService.productDetail();
                    break;
                case 3 :
                    OrderService.addToCart(yourCustomerNumber);
                    break;
                case 4 :
                    OrderService.viewCart(yourCustomerNumber);
                    break;
                case 5 :
                    OrderService.removeCart(yourCustomerNumber);
                    break;
                case 6:
                    isExit = true;
                    break;
                default:
                    isExit = true;
            }
        } while (!isExit);
    }

    private static int functionChoice() {
        showMenu();
        System.out.print("Xin mời chọn chức năng: ");
        int choice;
        do {
            choice = new Scanner(System.in).nextInt();
            if (choice >= 1 && choice <= 8) {
                break;
            }
            System.out.print("Lựa chọn không hợp lệ, vui lòng chọn lại: ");
        } while (true);
        return choice;
    }

    private static void showMenu() {
        System.out.println("----------CHƯƠNG TRÌNH QUẢN LÝ BÁN HÀNG------------");
        System.out.println("1. Danh sách sản phẩm.");
        System.out.println("2. Chi tiết sản phẩm.");
        System.out.println("3. Thêm vào giỏ hàng.");
        System.out.println("4. Xem giỏ hàng.");
        System.out.println("3. Xóa giỏ hàng.");
        System.out.println("6. Thoát chương trình.");
    }
}

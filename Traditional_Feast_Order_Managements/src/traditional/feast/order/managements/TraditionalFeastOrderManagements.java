package traditional.feast.order.managements;

import Business.Customers;
import Business.MenuItems;
import Business.Orders;
import Util.Inputter;
import view.Menu;

/**
 *
 * @author ttuan
 */
public class TraditionalFeastOrderManagements {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Khởi tạo đối tượng khách hàng không có tham số
        Customers CusList = new Customers(); 
        CusList.readFromFile(); // Đọc danh sách khách hàng từ file
        
        MenuItems MList = new MenuItems();
        MList.readFromFile(); // Đọc danh sách thực đơn từ file

        Orders OList = new Orders();
        OList.readFromFile(); // Đọc danh sách đơn hàng từ file

        int choice;
        do {
            Menu.displayMenu(); // Hiển thị menu
            choice = Menu.getUserChoice(); // Nhận lựa chọn từ người dùng

            switch (choice) {
                case 1:
                    CusList.addCustomer();
                    break;
                case 2:
                    CusList.updateCustomer();
                    break;
                case 3:
                    CusList.searchCustomerByName();
                    break;
                case 4:
                    MList.displayAllMenus();
                    break;
                case 5:
                    OList.placeOrder(CusList, MList);
                    break;
                case 6:
                    OList.updateOrder(MList);
                    break;
                case 7:
                    OList.saveToFile();
                    CusList.saveToFile();
                    System.out.println("Data has been saved successfully!");
                    break;
                case 8:
                    OList.displayOrders();
                    CusList.displayCustomers();
                    break;
                case 9:
                    if (!OList.isSaved() || !CusList.isSaved()) {
                        System.out.print("Do you want to save the data before exiting? (Y/N): ");
                        String confirm = Inputter.getString("").toUpperCase();
                        if (confirm.equals("Y")) {
                            OList.saveToFile();
                            CusList.saveToFile();
                            System.out.println("Data saved successfully.");
                        }
                    }
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 9);
    }
}


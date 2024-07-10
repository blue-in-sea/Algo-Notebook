package OOD;

import java.math.BigDecimal;
import java.util.*;

public class BillingSystem {
    class Billing {
        User user;
        List<Item> items;
        Payment payment;

        public void createUser(User user){
            // check if user is already there or not, if user is already created then we will use existing userId
        }

        public float createBill(int userId, Item[] items){
            float total = 0;
            for(int i = 0; i < items.length; i++){
                total += items[i].price * items[i].quantity;
            }
            return total;
        }
    }

    class User {
        int id;
        String Name;
        int phoneNo;
    }

    class Item {
        int id;
        Category category;
        float price;
        int quantity;
    }

    enum Category{
        KIDS, SPORTS, PANTRY
    }

    class Payment {
        User user;
        long timestamp;
        PaymentType paymentType;
        Invoice invoice;
        BigDecimal paymentAmount; // possible partial payment
        public boolean processPaymentByType(PaymentType paymentType){
            return false;
        }
    }

    public class InvoiceItem {
        int quantity;
        Item item;
        Optional<BigDecimal> discountPrice;
    }

    public class Invoice {
        List<InvoiceItem> invoiceItems;
        BigDecimal discountPercentage; // you could have a discount on the entire purchase
    }

    enum PaymentStatus {
        DONE, PENDING
    }

    enum PaymentType {
        CASH, UPI, DEBIT_CARD, CREDIT_CARD
    }
}

package org.aliacademy.handle;

import org.aliacademy.hibernate.HibernateUtil;
import org.aliacademy.model.Cart;
import org.aliacademy.model.CartItem;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Scanner;

public class OrderService {

    public static void addToCart(int yourCustomerNumber) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Cart> criteriaQuery = builder.createQuery(Cart.class);
            Root<Cart> root = criteriaQuery.from(Cart.class);
            criteriaQuery.select(root).where(builder.equal(root.get("customerNumber"), yourCustomerNumber));
            Cart cart = new Cart();
            int cartId = 0;
            try {
                cart = session.createQuery(criteriaQuery).getSingleResult();
                cartId = cart.getId();
            } catch (NoResultException exception) {
                exception.printStackTrace();
            }

            transaction = session.beginTransaction();
            if (cart.getId() == 0) {
                cartId = (int) session.save(new Cart(yourCustomerNumber));
            }
            // nhap product code
            System.out.println("Vui lòng nhập mã sản phẩm");
            String productCode = new Scanner(System.in).nextLine();
            System.out.println("Vui lòng nhập số lượng");
            int quantity = new Scanner(System.in).nextInt();
            System.out.println("Vui lòng nhập giá thêm");
            double extraPrice = new Scanner(System.in).nextDouble();
            session.save(new CartItem(cartId, productCode, quantity, extraPrice));
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) transaction.rollback();
        }
    }

    public static void viewCart(int yourCustomerNumber) {
        // lay ra card id
        // TODO ......
        // lay ra tat ca cart item co card id vua lay duoc
        // TODO ......
        // in ra list danh sach dang bang
        // TODO ......
    }

    public static void removeCart(int yourCustomerNumber) {
        // lay ra card id
        // TODO ......
        // xoa tat ca cart item co cart id vua lay duoc
        // TODO ......
    }
}

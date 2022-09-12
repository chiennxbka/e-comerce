package org.aliacademy.handle;

import org.aliacademy.hibernate.HibernateUtil;
import org.aliacademy.model.Product;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Scanner;

public class ProductService {

    public static void listProduct() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
            Root<Product> root = criteriaQuery.from(Product.class);
            criteriaQuery.select(root);
            List<Product> products = session.createQuery(criteriaQuery).setMaxResults(20).getResultList();

            // print with format table
            String leftAlignFormat = "| %-12s | %-55s | %-30s | %-11s | %-11s |%n";

            System.out.format("+--------------+---------------------------------------------------------+--------------------------------+-------------+-----------+%n");
            System.out.format("| Product code |                          Product name                   |             Vendor             |    Stock    | Buy Price |%n");
            System.out.format("+--------------+---------------------------------------------------------+--------------------------------+-------------+-----------+%n");
            products.forEach(product -> {
                System.out.format(leftAlignFormat, product.getProductCode(), product.getProductName(), product.getProductVendor(), product.getQuantityInStock(), product.getBuyPrice());
            });
            System.out.format("+--------------+---------------------------------------------------------+--------------------------------+-------------+-----------+%n");
        }
    }

    public static void productDetail() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Product product = new Product();
            System.out.println("Vui lòng nhập mã sản phẩm");
            do {
                Scanner scanner = new Scanner(System.in);
                String productCode = scanner.nextLine();
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
                Root<Product> root = criteriaQuery.from(Product.class);
                criteriaQuery.select(root).where(builder.equal(root.get("productCode"), productCode));
                try {
                    product = session.createQuery(criteriaQuery).getSingleResult();
                } catch (NoResultException exception) {
                    System.out.println("Sản phẩm không tồn tại, vui lòng nhập lại");
                }
            } while (product.getProductCode() == null);

            // print with format table
            if (product.getProductCode() != null) {
                String leftAlignFormat = "| %-12s | %-55s | %-30s | %-11s | %-11s |%n";

                System.out.format("+--------------+---------------------------------------------------------+--------------------------------+-------------+-----------+%n");
                System.out.format("| Product code |                          Product name                   |             Vendor             |    Stock    | Buy Price |%n");
                System.out.format("+--------------+---------------------------------------------------------+--------------------------------+-------------+-----------+%n");
                System.out.format(leftAlignFormat, product.getProductCode(), product.getProductName(), product.getProductVendor(), product.getQuantityInStock(), product.getBuyPrice());
                System.out.format("+--------------+---------------------------------------------------------+--------------------------------+-------------+-----------+%n");
            }
        }
    }
}

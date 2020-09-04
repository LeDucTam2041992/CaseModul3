package controller;

import model.Product;
import model.SpecSmartphone;
import productDAO.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Servlet", urlPatterns = "/products")
public class Servlet extends HttpServlet {
    private ProductDAO productDAO;

    public void init() {
        productDAO = new ProductDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                break;
            case "edit":
                break;
            case "delete":
                break;
            default:
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                break;
            case "edit":
                break;
            case "delete":
                break;
            case "show":
                showProduct(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }
    }

    private void showProduct(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Product product = productDAO.selectProduct(id);
        List<String> producers = productDAO.selectAllProducer(product.getSpecial());
        List<String[]> specSmartphone = productDAO.selectSpecSm(id);
        request.setAttribute("specifications", specSmartphone);
        request.setAttribute("producers", producers);
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/ProductDetail.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void listProducts(HttpServletRequest request, HttpServletResponse response) {
        String special = request.getParameter("special");
        if(special == null){
            special = "smartphone";
        }
        List<Product> listProduct = productDAO.selectAlProduct(special);
        List<String> producers = productDAO.selectAllProducer(special);
        request.setAttribute("producers", producers);
        request.setAttribute("listProduct", listProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/listProduct.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
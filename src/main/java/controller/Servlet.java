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
import java.sql.SQLException;
import java.util.ArrayList;
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
            case "login":
                login(request, response);
                break;
            case "create":
                break;
            case "edit":
                try {
                    updateProduct(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
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
                editProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "sort":
                sortProductByProducer(request, response);
                break;
            case "show":
                showProduct(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
//        String id = request.getParameter("id");
//        try {
//            productDAO.deleteProduct(id);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        listProducts(request, response);
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Product product = productDAO.selectProduct(id);
        List<String> producers = productDAO.selectAllProducer(product.getSpecial());
        List<String[]> specSmartphone = productDAO.selectSpecSm(id);
        request.setAttribute("specifications", specSmartphone);
        request.setAttribute("producers", producers);
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/EditProduct.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String id = request.getParameter("id");
        int price = Integer.parseInt(request.getParameter("price"));
        Product product = productDAO.selectProduct(id);
        if(product.getSpecial().equals("smartphone")) {
            String screen= request.getParameter("SCREEN");
            String operaSystem = request.getParameter("OPERA SYSTEM");
            String cameraFont = request.getParameter("CAMERA FONT");
            String cameraEnd = request.getParameter("CAMERA BACK");
            String cpu = request.getParameter("CPU");
            String ram = request.getParameter("RAM");
            String memory = request.getParameter("MEMORY");
            String sim = request.getParameter("SIM");
            String pin = request.getParameter("PIN");
            SpecSmartphone specSmartphone = new SpecSmartphone(id, screen, operaSystem, cameraFont, cameraEnd, cpu, ram,memory
            , sim, pin);
            productDAO.updateSpecSM(specSmartphone);
        }
        product.setPrice(price);
        productDAO.updateProduct(product);
        listProducts(request, response);
    }

    private void sortProductByProducer(HttpServletRequest request, HttpServletResponse response) {
        String spec = request.getParameter("spec");
        String producer = request.getParameter("nameSort");
        List<Product> productList = productDAO.sortProductByProducer(spec,producer);
        List<String> producers = productDAO.selectAllProducer(spec);
        request.setAttribute("listProduct", productList);
        request.setAttribute("producers", producers);
        request.setAttribute("spec", spec);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/listProduct.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showProduct(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Product product = productDAO.selectProduct(id);
        List<String> producers = productDAO.selectAllProducer(product.getSpecial());
        List<String[]> specProduct = new ArrayList<>();
        if(product.getSpecial().equals("smartphone")) {
            specProduct = productDAO.selectSpecSm(id);
        }
        if(product.getSpecial().equals("laptop")) {
            specProduct = productDAO.selectSpecLaptop(id);
        }
        if(product.getSpecial().equals("tablet")) {
            specProduct = productDAO.selectSpecTablet(id);
        }
        request.setAttribute("specifications", specProduct);
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

    private void login(HttpServletRequest request, HttpServletResponse response) {
        String account = request.getParameter("uname");
        String password = request.getParameter("psw");
        boolean isAdmin = false;
        if(account.equals("11111")&&password.equals("12345")){
            isAdmin = true;
        }
        request.setAttribute("admin", isAdmin);
        listProducts(request,response);
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) {
        String special = request.getParameter("special");
        if(special == null){
            special = "smartphone";
        }
        List<Product> listProduct = productDAO.selectAlProduct(special);
        List<String> producers = productDAO.selectAllProducer(special);
        request.setAttribute("spec", special);
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

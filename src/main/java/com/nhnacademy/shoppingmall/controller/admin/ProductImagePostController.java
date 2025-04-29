package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductCategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/product/image-upload.do")
public class ProductImagePostController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl(), new ProductCategoryRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String uploadPath = req.getServletContext().getRealPath("/upload");
            File dir = new File(uploadPath);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            Part part = req.getPart("product_image");
            String fileName = part.getSubmittedFileName();
            String filePath = uploadPath + File.separator + fileName;
            part.write(filePath);

            String productId = req.getParameter("product_id");
            Product product = productService.getProduct(productId);
            String webPath = "/upload/" + fileName;
            product.setProductImage(webPath);
            productService.updateProductImage(productId, webPath);
            log.debug("Product image updated: {}", webPath);

            return "redirect:/admin/product.do";
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

}

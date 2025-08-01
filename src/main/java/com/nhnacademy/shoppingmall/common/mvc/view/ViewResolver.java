package com.nhnacademy.shoppingmall.common.mvc.view;
public class ViewResolver {

    public static final String DEFAULT_PREFIX="/WEB-INF/views/";
    public static final String DEFAULT_POSTFIX=".jsp";
    public static final String REDIRECT_PREFIX="redirect:";
    public static final String DEFAULT_SHOP_LAYOUT="/WEB-INF/views/layout/shop.jsp";
    public static final String DEFAULT_ADMIN_LAYOUT="/WEB-INF/views/layout/admin.jsp";
    public static final String LAYOUT_CONTENT_HOLDER = "layout_content_holder";
    public static final String ERROR_PAGE = "/WEB-INF/views/error/error.jsp";

    private final String prefix;
    private final String postfix;

    public ViewResolver(){
        this(DEFAULT_PREFIX,DEFAULT_POSTFIX);
    }
    public ViewResolver(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public  String getPath(String viewName){
        //todo#6-1  prefix+viewNAme+postfix 반환 합니다.
        return String.format("%s%s%s", prefix, viewName, postfix).replace("//", "/");
    }
    public boolean isRedirect(String viewName){
        //todo#6-2 REDIRECT_PREFIX가 포함되어 있는지 체크 합니다.
        return viewName.toLowerCase().contains(REDIRECT_PREFIX);
    }

    public String getRedirectUrl(String viewName){
        //todo#6-3 REDIRECT_PREFIX를 제외한 url을 반환 합니다.
        return viewName.toLowerCase().substring(REDIRECT_PREFIX.length());
    }

    public String getLayOut(String viewName){

        /*todo#6-4 viewName에
           /admin/경로가 포함되었다면 DEFAULT_ADMIN_LAYOUT 반환 합니다.
           /admin/경로가 포함되어 있지않다면 DEFAULT_SHOP_LAYOUT 반환 합니다.
        */
        if (viewName.contains("/admin/")) {
            return DEFAULT_ADMIN_LAYOUT;
        }
        return DEFAULT_SHOP_LAYOUT;
    }
    public String getErrorPage(){
        return ERROR_PAGE;
    }
}

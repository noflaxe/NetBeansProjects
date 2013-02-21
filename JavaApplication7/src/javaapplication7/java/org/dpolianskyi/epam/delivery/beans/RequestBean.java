package org.dpolianskyi.epam.delivery.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.dpolianskyi.epam.delivery.controller.dao.abstr.JpaDAOFactory;
import org.dpolianskyi.epam.delivery.controller.dao.real.CurProductDAO;
import org.dpolianskyi.epam.delivery.controller.dao.real.Curpro_RequestDAO;
import org.dpolianskyi.epam.delivery.controller.dao.real.RequestDAO;
import org.dpolianskyi.epam.delivery.model.*;
import org.dpolianskyi.epam.delivery.paging.*;

@ManagedBean(name = "requestBean")
@SessionScoped
public class RequestBean implements Serializable {

    private Pagination pagination = new Pagination(5, 0);
    private static final long serialVersionUID = 1L;
    private DataModel<Request> modelRequest;
    private DataModel<CurProduct> modelCurProduct;
    private DataModel<Request> modelDelivery;
    private List<Curpro_Request> curPro_RequestList;
    private Request request;
    private Request curRequest;
    private RequestDAO requestJPAController = null;
    private CurProductDAO curProductJPAController = null;
    private Curpro_RequestDAO curPro_RequestJPAController = null;
    private CurProduct curProd;
    private String curModelName;
    private String curProducerName;
    private String curCategoryName;
    private final static String PAGINATIONERROR = "Something caused the error of pagging";
    private final static String CREATEERROR = "Error with creating of ";
    private final static String EDITERROR = "Error with editing of ";
    private final static String REMOVEERROR = "Error with removing of ";
    private final static String CONFIRMERROR = "Error with confirming of ";

    public void setDAO(RequestDAO dao) {
        this.requestJPAController = dao;
    }

    public void setDAO(CurProductDAO dao) {
        this.curProductJPAController = dao;
    }

    public void setDAO(Curpro_RequestDAO dao) {
        this.curPro_RequestJPAController = dao;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Long getRequestQuantity() {
        return requestJPAController.selectRequestCount() / pagination.getRecordsOnPage() + 1;
    }

    public Long getProductQuantity() throws Exception {
        return curProductJPAController.selectProductQuantityOfCurrentRequest(curRequest) / pagination.getRecordsOnPage() + 1;
    }

    public Long getDeliveryQuantity() {
        return requestJPAController.selectDeliveryCount() / pagination.getRecordsOnPage() + 1;
    }
    
    public String getProductListStatus() {
        Boolean confirmStatatus = curProductJPAController.defStatusOfCurrentProducts(curRequest);
        if (confirmStatatus == true) {
            return PagesNS.PAGE_LIST_DELIVERY;
        } else {
            return PagesNS.PAGE_LIST_PRODUCTS;
        }
    }

    public Request getCurRequest() {
        return curRequest;
    }

    public void setCurRequest(Request curRequest) {
        this.curRequest = curRequest;
    }

    public CurProduct getCurProduct() {
        return curProd;
    }

    public void setCurProduct(CurProduct curProd) {
        this.curProd = curProd;
    }

    public String getCurModelName() {
        return curModelName;
    }

    public void setCurModelName(String curModelName) {
        this.curModelName = curModelName;
    }

    public String getCurCategoryName() {
        return curCategoryName;
    }

    public void setCurCategoryName(String curCategoryName) {
        this.curCategoryName = curCategoryName;
    }

    public String getCurProducerName() {
        return curProducerName;
    }

    public void setCurProducerName(String curProducerName) {
        this.curProducerName = curProducerName;
    }

    public RequestBean() {
        requestJPAController = (RequestDAO) JpaDAOFactory.getDAO(Request.class);
        curProductJPAController = (CurProductDAO) JpaDAOFactory.getDAO(CurProduct.class);
        curPro_RequestJPAController = (Curpro_RequestDAO) JpaDAOFactory.getDAO(Curpro_Request.class);
        modelRequest = new ListDataModel();
        modelCurProduct = new ListDataModel();
        modelDelivery = new ListDataModel();
    }

    public List<CurProduct> selectCurRequest() throws Exception {
        List<CurProduct> curProdList = new LinkedList<CurProduct>();
        long curRequestId = curRequest.getId();
        curRequest = requestJPAController.findById(curRequestId);
        curPro_RequestList = new LinkedList<Curpro_Request>(curRequest.getCurrentProduct_Request());
        for (Curpro_Request elem : getCurPro_Request()) {
            curProdList.add(elem.getCurrentProduct());
            elem.setRequest(curRequest);
        }
        requestJPAController.merge(curRequest);
        return curProdList;
    }

    public List<Request> selectCurProduct() throws Exception {
        long curProductID = curProd.getId(); //1
        curProd = curProductJPAController.findById(curProductID);
        curPro_RequestList = new LinkedList<Curpro_Request>(curProd.getCurrentProduct_Request());
        return getProductListByProduct();
    }

    public List<Request> getProductListByProduct() {
        List<Request> curRequestList = new LinkedList<Request>();
        for (Curpro_Request elem : getCurPro_Request()) {
            curRequestList.add(elem.getRequest());
        }
        return curRequestList;
    }

    public List<Curpro_Request> getCurPro_Request() {
        return curPro_RequestList;
    }

    public void setCurPro_Request(List<Curpro_Request> curPro_Request) {
        this.curPro_RequestList = curPro_Request;
    }

    public DataModel<Request> getModelRequest() {
        try {
            PageController.updateModel(modelRequest, pagination, requestJPAController);
            if ((modelRequest.getRowCount() < 1) && (pagination.isPossiblePrev())) {
                movePrevPageRequest();
            }
        } catch (Exception e) {
            LogBean.getLogger().error(PAGINATIONERROR + " " + java.util.Calendar.getInstance().getTime(), e);
        }
        return modelRequest;
    }

    public DataModel<Request> getModelDelivery() {
        try {
            PageController.updateModelCondition(modelDelivery, pagination, requestJPAController);
            if ((modelDelivery.getRowCount() < 1) && (pagination.isPossiblePrev())) {
                movePrevPageRequest();
            }
        } catch (Exception e) {
            LogBean.getLogger().error(PAGINATIONERROR + " " + java.util.Calendar.getInstance().getTime(), e);
        }
        return modelDelivery;
    }

    public DataModel<Request> getEditedModelDelivery() {
        modelDelivery = getModelDelivery();
        return modelDelivery;
    }

    public DataModel<CurProduct> getModelCurProduct() throws Exception {
        try {
            PageController.updateModel(modelCurProduct, pagination, curProductJPAController, curRequest);
            if ((modelCurProduct.getRowCount() < 1) && (pagination.isPossiblePrev())) {
                movePrevPageProduct();
            }
        } catch (Exception e) {
            LogBean.getLogger().error(PAGINATIONERROR + " " + java.util.Calendar.getInstance().getTime(), e);
        }
        return modelCurProduct;
    }
    
    public String setupAddRequest() {
        request = new Request();
        return PagesNS.PAGE_ADD_REQUEST;
    }

    public String setupEditDelivery() {
        request = modelDelivery.getRowData();
        return PagesNS.PAGE_EDIT_DELIVERY;
    }

    public String setupEditRequest() {
        request = modelRequest.getRowData();
        return PagesNS.PAGE_EDIT_REQUEST;
    }

    public String createRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            requestJPAController.persist(request);
            PageController.updateModel(modelRequest, pagination, requestJPAController);
            return PagesNS.PAGE_LIST_REQUESTS;
        } catch (Exception e) {
            LogBean.getLogger().error(CREATEERROR + " " + request.getId() + "-" + request.getCode() + " " + java.util.Calendar.getInstance().getTime(), e);
            return "";
        }
    }

    public String createProduct() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            Category curCategory = new Category(curCategoryName, null);
            Producer curProducer = new Producer(curProducerName, null);
            Model curModel = new Model(curModelName, null);
            curProd.setCategory(curCategory);
            curProd.setProducer(curProducer);
            curProd.setModel(curModel);
            Curpro_Request curpro_Request = new Curpro_Request();
            curpro_Request.setCurrentProduct(curProd);
            curpro_Request.setRequest(curRequest);
            curPro_RequestJPAController.persist(curpro_Request);
            curProductJPAController.merge(curProd);
            requestJPAController.merge(curRequest);
            PageController.updateModel(modelCurProduct, pagination, curProductJPAController);
            return PagesNS.PAGE_LIST_PRODUCTS;
        } catch (Exception e) {
            LogBean.getLogger().error(CREATEERROR + " " + curProd.getId() + "-" + curProd.getName() + " " + java.util.Calendar.getInstance().getTime(), e);
            return "";
        }
    }

    public String editRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            requestJPAController.merge(request);
            PageController.updateModel(modelRequest, pagination, requestJPAController);
            return PagesNS.PAGE_LIST_REQUESTS;
        } catch (Exception e) {
            LogBean.getLogger().error(EDITERROR + " " + request.getId() + "-" + request.getCode() + " " + java.util.Calendar.getInstance().getTime(), e);
            return "";
        }
    }

    public String editProduct() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            Category curCategory = curProd.getCategory();
            Model curModel = curProd.getModel();
            Producer curProducer = curProd.getProducer();
            curModel.setName(curModelName);
            curCategory.setName(curCategoryName);
            curProducer.setName(curProducerName);
            curProd.setModel(curModel);
            curProd.setCategory(curCategory);
            curProd.setProducer(curProducer);
            curProductJPAController.merge(curProd);
            PageController.updateModel(modelCurProduct, pagination, curProductJPAController);
            return PagesNS.PAGE_LIST_PRODUCTS;//"list_products";
        } catch (Exception e) {
            LogBean.getLogger().error(EDITERROR + " " + curProd.getId() + "-" + curProd.getName() + " " + java.util.Calendar.getInstance().getTime(), e);
            return "";
        }
    }

    public String editDelivery() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            requestJPAController.merge(request);
            PageController.updateModel(modelDelivery, pagination, requestJPAController);
            return PagesNS.PAGE_LIST_DELIVERY;
        } catch (Exception e) {
            LogBean.getLogger().error(EDITERROR + " " + request.getId() + "-" + request.getCode() + " " + java.util.Calendar.getInstance().getTime(), e);
            return "";
        }
    }

    public String removeRequest() {
        Request requestToRemove = modelRequest.getRowData();
        Long deletedId = requestToRemove.getId();
        String deletedRequestCode = requestToRemove.getCode();
        List<Curpro_Request> setCPR = requestToRemove.getCurrentProduct_Request();
        try {
            for (Curpro_Request elem : setCPR) {
                CurProduct cp = elem.getCurrentProduct();
                curPro_RequestJPAController.remove(elem);
                curProductJPAController.remove(cp);
            }
            requestJPAController.remove(requestToRemove);
            PageController.updateModel(modelRequest, pagination, requestJPAController);
            return PagesNS.PAGE_LIST_REQUESTS;
        } catch (Exception e) {
            LogBean.getLogger().error(REMOVEERROR + " " + deletedId + "-" + deletedRequestCode + " " + java.util.Calendar.getInstance().getTime(), e);
            return "";
        }
    }

    public String removeDelivery() {
        Request requestToRemove = modelDelivery.getRowData();
        Long deletedId = requestToRemove.getId();
        String deletedRequestCode = requestToRemove.getCode();
        List<Curpro_Request> setCPR = requestToRemove.getCurrentProduct_Request();
        try {
            for (Curpro_Request elem : setCPR) {
                CurProduct cp = elem.getCurrentProduct();
                curPro_RequestJPAController.remove(elem);
                curProductJPAController.remove(cp);
            }
            requestJPAController.remove(requestToRemove);
            PageController.updateModel(modelDelivery, pagination, requestJPAController);
            return PagesNS.PAGE_LIST_DELIVERY;
        } catch (Exception e) {
            LogBean.getLogger().error(REMOVEERROR + " " + deletedId + "-" + deletedRequestCode + " " + java.util.Calendar.getInstance().getTime(), e);
            return "";
        }
    }

    public String confirmDelivery() {
        Request requestToRemove = modelDelivery.getRowData();
        Long deletedId = requestToRemove.getId();
        String deletedRequestCode = requestToRemove.getCode();
        List<Curpro_Request> setCPR = requestToRemove.getCurrentProduct_Request();
        try {
            for (Curpro_Request elem : setCPR) {
                CurProduct cp = elem.getCurrentProduct();
                curPro_RequestJPAController.remove(elem);
                curProductJPAController.remove(cp);
            }
            requestJPAController.remove(requestToRemove);
            PageController.updateModel(modelDelivery, pagination, requestJPAController);
            return PagesNS.PAGE_DELIVERY_MESSAGE;
        } catch (Exception e) {
            LogBean.getLogger().error(CONFIRMERROR + " " + deletedId + "-" + deletedRequestCode + " " + java.util.Calendar.getInstance().getTime(), e);
            return "";
        }
    }

    public String removeProduct() throws Exception {
        CurProduct productToRemove = modelCurProduct.getRowData();
        Long deletedId = productToRemove.getId();
        String deletedProductName = productToRemove.getName();
        try {
            Curpro_Request curPro_Req = curPro_RequestJPAController.findByProductId(deletedId);
            curPro_RequestJPAController.remove(curPro_Req);
            return PagesNS.PAGE_LIST_PRODUCTS;//"list_products";
        } catch (Exception e) {
            LogBean.getLogger().error(REMOVEERROR + " " + deletedId + "-" + deletedProductName + " " + java.util.Calendar.getInstance().getTime(), e);
            return "";
        }
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public String moveNextPageRequest() {
        if (pagination.isPossibleNext()) {
            int currentPage = pagination.getCurrentPage();
            pagination.setCurrentPage(++currentPage);
            try {
                PageController.updateModel(modelRequest, pagination, requestJPAController);
                return PagesNS.PAGE_LIST_REQUESTS;//"list_requests";
            } catch (Exception e) {
                LogBean.getLogger().error(PAGINATIONERROR + " " + java.util.Calendar.getInstance().getTime(), e);
                return "";
            }
        } else {
            return "";
        }
    }

    public String movePrevPageRequest() {
        if (pagination.isPossiblePrev()) {
            int currentPage = pagination.getCurrentPage();
            if (currentPage > 0) {
                pagination.setCurrentPage(--currentPage);
            }
            try {
                PageController.updateModel(modelRequest, pagination, requestJPAController);
                return PagesNS.PAGE_LIST_REQUESTS;//"list_requests";
            } catch (Exception e) {
                LogBean.getLogger().error(PAGINATIONERROR + " " + java.util.Calendar.getInstance().getTime(), e);
                return "";
            }
        } else {
            return "";
        }
    }

    public String moveProductListPage() throws IOException, Exception {
        curRequest = modelRequest.getRowData();
        return PagesNS.PAGE_LIST_PRODUCTS;
    }

    public String setupAddProduct() {
        curProd = new CurProduct();
        return PagesNS.PAGE_ADD_PRODUCT;//" add_product";
    }

    public String setupEditProduct() throws Exception {
        curProd = modelCurProduct.getRowData();
        return PagesNS.PAGE_EDIT_PRODUCT;//"edit_request";
    }

    public String moveNextPageProduct() {
        if (pagination.isPossibleNext()) {
            int currentPage = pagination.getCurrentPage();
            pagination.setCurrentPage(++currentPage);
            try {
                PageController.updateModel(modelCurProduct, pagination, curProductJPAController);
                return PagesNS.PAGE_LIST_PRODUCTS;//"list_products";
            } catch (Exception e) {
                LogBean.getLogger().error(PAGINATIONERROR + " " + java.util.Calendar.getInstance().getTime(), e);
                return "";
            }
        } else {
            return "";
        }
    }

    public String movePrevPageProduct() {
        if (pagination.isPossiblePrev()) {
            int currentPage = pagination.getCurrentPage();
            if (currentPage > 0) {
                pagination.setCurrentPage(--currentPage);
            }
            try {
                PageController.updateModel(modelCurProduct, pagination, curProductJPAController);
                return PagesNS.PAGE_LIST_PRODUCTS;//"list_products";
            } catch (Exception e) {
                LogBean.getLogger().error(PAGINATIONERROR + " " + java.util.Calendar.getInstance().getTime(), e);
                return "";
            }
        } else {
            return "";
        }
    }

    public String moveDeliveryPageConfirm() {
        return getProductListStatus();
    }

    public String moveDeliveryPage() {
        return PagesNS.PAGE_LIST_DELIVERY;
    }

    public String moveRequestPage() {
        return PagesNS.PAGE_LIST_REQUESTS;
    }
}

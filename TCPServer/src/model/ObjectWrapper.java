package model;
 
import java.io.Serializable;
 
public class ObjectWrapper  implements Serializable{
    private static final long serialVersionUID = 20210811011L;
    public static final int LOGIN_USER = 1;
    public static final int REPLY_LOGIN_USER = 2;
    public static final int LOGOUT_USER = 3;
    public static final int REPLY_LOGOUT_USER = 4;
    public static final int EDIT_CUSTOMER = 5;
    public static final int REPLY_EDIT_CUSTOMER = 6;
    public static final int SEARCH_CUSTOMER_BY_NAME = 7;
    public static final int REPLY_SEARCH_CUSTOMER = 8;
    public static final int SERVER_INFORM_CLIENT_NUMBER = 9;
    public static final int SCOREBOARD = 10;
    public static final int REPLY_SCOREBOARD = 11;
    
    private int performative;
    private Object data;
    public ObjectWrapper() {
        super();
    }
    public ObjectWrapper(int performative, Object data) {
        super();
        this.performative = performative;
        this.data = data;
    }
    public int getPerformative() {
        return performative;
    }
    public void setPerformative(int performative) {
        this.performative = performative;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }   
}
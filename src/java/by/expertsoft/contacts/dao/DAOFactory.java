package by.expertsoft.contacts.dao;

import by.expertsoft.contacts.implDAO.UserImplDAO;
import java.util.HashMap;
import java.util.Map;

public class DAOFactory {

    protected static Map map = mapInit();

    public DAOFactory() {
        super();
    }

    private static Map mapInit() {
        Map<String, DAO> m = new HashMap<String, DAO>();
        m.put(UserDAO.class.getName(), new UserImplDAO());
        
        return m;
    }

    public static Object getDAO(String className) {
        return map.get(className);
    }

}

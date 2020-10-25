package itis.Tyshenko.repositories;

import java.lang.reflect.Field;
import java.util.*;

public abstract class ReflectionCrudRepository<T> implements CrudRepository<T> {

    protected List<Object> getEntityParam(Map<String, Object> map) {
        List<Object> objects = new ArrayList<>();
        Set<Map.Entry<String, Object>> entrySet = map.entrySet();
        for (Map.Entry<String, Object> entry: entrySet) {
            objects.add(entry.getValue());
        }
        return objects;
    }

    protected List<Object> getParameters(T entity,String[] fieldNames, Object ... args) throws NoSuchFieldException, IllegalAccessException {
        Map<String, Object> map = getUserFields(entity, fieldNames);
        List<Object> params = getEntityParam(map);
        params = getPreparedParamFromEntityAndArgs(params, args);
        return params;
    }

    protected List<Object> getPreparedParamFromEntityAndArgs(List<Object> objects, Object[] args) {
        objects.addAll(Arrays.asList(args));
        return objects;
    }

    protected Map<String, Object> getUserFields(T entity, String[] fieldNames) throws NoSuchFieldException, IllegalAccessException {
        Map<String, Object> fieldsMap = new LinkedHashMap<>();
        Class<? extends Object> hirerClass = entity.getClass();
        for (String fieldName : fieldNames) {
            Field field = hirerClass.getField(fieldName);
            field.setAccessible(true);
            fieldsMap.put(fieldName, field.get(entity));
        }
        return fieldsMap;
    }

    protected String addParamAfterWhereToSqlRequest(String sql, Set<String> fieldsName) {
        StringBuilder buffer = new StringBuilder(sql);
        Iterator<String> iterator = fieldsName.iterator();
        for (int i = 0; i < fieldsName.size() - 1; i++) {
            String field = iterator.next();
            buffer.append(" ").append(field).append(" = ?,");
        }
        String field = iterator.next();
        buffer.append(" ").append(field).append(" = ?;");
        return buffer.toString();
    }
}

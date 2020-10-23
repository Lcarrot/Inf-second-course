package itis.Tyshenko.repositories;

import itis.Tyshenko.entity.users.User;

import java.lang.reflect.Field;
import java.util.*;

public abstract class UserRepository<T extends User> implements CrudRepository<T> {

    private final String[] fieldNames = new String[0];

    public abstract Optional<T> findById(Long id);

    public abstract Optional<T> findByEmail(String email);

    protected List<Object> getEntityParam(Map<String, Object> map) {
        List<Object> objects = new ArrayList<>();
        Set<Map.Entry<String, Object>> entrySet = map.entrySet();
        for (Map.Entry<String, Object> entry: entrySet) {
            if (!entry.getKey().equals("id")) objects.add(entry.getValue());
        }
        return objects;
    }

    protected List<Object> getParameters(T entity, Object ... args) throws NoSuchFieldException, IllegalAccessException {
        Map<String, Object> map = getUserFields(entity);
        List<Object> params = getEntityParam(map);
        params = getPreparedParamFromEntityAndArgs(params, args);
        return params;
    }

    protected List<Object> getPreparedParamFromEntityAndArgs(List<Object> objects, Object[] args) {
        objects.addAll(Arrays.asList(args));
        return objects;
    }

    protected Map<String, Object> getUserFields(T entity) throws NoSuchFieldException, IllegalAccessException {
        Map<String, Object> fieldsMap = new LinkedHashMap<>();
        Class<? extends User> hirerClass = entity.getClass();
        for (String fieldName : fieldNames) {
            Field field = hirerClass.getField(fieldName);
            field.setAccessible(true);
            fieldsMap.put(fieldName, field.get(entity));
        }
        return fieldsMap;
    }
}

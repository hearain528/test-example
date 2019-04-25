package com.hearain.generic;

import java.util.HashMap;
import java.util.Map;

/**
 * 优先考虑类型安全的异构容器,可以保存不同类型的键值
 * @author qijun
 * @date 2018/2/27 0027 14:15
 * @param
 * @return
 * @throws
 */
public class Favorite {

    private Map<Class<?>, Object> favorites = new HashMap<Class<?>, Object>();

    public <T> void putFavorite(Class<T> type, T instance){
        if(type == null){
            throw new NullPointerException("type is NULL");
        }
        favorites.put(type, instance);
    }

    public <T> T getFavorite(Class<T> type){
        return type.cast(favorites.get(type));
    }


}

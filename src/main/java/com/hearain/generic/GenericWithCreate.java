package com.hearain.generic;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2018/1/24 0024 15:31
 * @version: 1.1.0
 * @description:
 */
abstract class GenericWithCreate<T> {

    final T element;

    GenericWithCreate(){
        element = create();
    }

    abstract T create();

}

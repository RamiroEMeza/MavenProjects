package com.solvd.laba.reflection;

import com.solvd.laba.result.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {
    private static final Logger LOGGER = LogManager.getLogger(Reflection.class);

    public static void print(String types, Object[] names) {
        LOGGER.info("List of " + types + " constructors: ");
        for (Object o : names) {
            LOGGER.info(o.toString());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final String PATH = "com.solvd.laba.result.Result";
        try {
            Class<?> reflectedClass = Class.forName(PATH);
            Constructor<?>[] constructors = reflectedClass.getConstructors();
            Reflection.print("Constructors", constructors);

            Method[] methodsMethods = reflectedClass.getMethods();
            Reflection.print("Methods", methodsMethods);

            Field[] fields = reflectedClass.getFields();
            Reflection.print("Fields", fields);

            Result reflectedResult = null;
            Class<Result> resultClass = (Class<Result>) Class.forName(PATH);
            reflectedResult = resultClass.newInstance();
            reflectedResult.setApproved(true);
            reflectedResult.setResult(10);
            reflectedResult.setStudent("Pep Guardiola");
            reflectedResult.setSubject("Football");

            LOGGER.info(reflectedResult.toString());

            Result secondReflectedResult = (Result) constructors[1].newInstance("Mourinho", "Football", true, 9);

            LOGGER.info(secondReflectedResult.toString());
            //Result reflectedResult = (Result) reflectedClass.getConstructor().newInstance();
            //LOGGER.info(reflectedResult.toString());

        } catch (ClassNotFoundException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }


    }
}

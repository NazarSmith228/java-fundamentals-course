package com.bobocode.demo2;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class DemoApp {
    public static void main(String[] args) {
        GreetingService helloService = createMethodLoggingProxy(GreetingService.class);
        helloService.hello(); // logs nothing to the console
        helloService.gloryToUkraine(); // logs method invocation to the console
    }

    /**
     * Creates a proxy of the provided class that logs its method invocations. If a method that
     * is marked with {@link LogInvocation} annotation is invoked, it prints to the console the following statement:
     * "[PROXY: Calling method '%s' of the class '%s']%n", where the params are method and class names accordingly.
     *
     * @param targetClass a class that is extended with proxy
     * @param <T>         target class type parameter
     * @return an instance of a proxy class
     */
    @SuppressWarnings("unchecked")
    public static <T> T createMethodLoggingProxy(Class<T> targetClass) {
        Enhancer enhancer = new Enhancer();
        String invocationMessage = """
                [PROXY: Calling method '%s' of the class '%s'], where the params are method and class names accordingly
                """;
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            if (method.isAnnotationPresent(LogInvocation.class)) {
                System.out.printf(invocationMessage, method.getName(), targetClass.getSimpleName());
            }
            return proxy.invokeSuper(obj, args);
        });

        return (T) enhancer.create();
    }
}

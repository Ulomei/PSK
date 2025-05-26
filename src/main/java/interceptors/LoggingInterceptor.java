package interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Logged
@Interceptor
public class LoggingInterceptor {
    @AroundInvoke
    public Object logMethod(InvocationContext context) throws Exception {
        System.out.println("Entering method: " + context.getMethod().getName());
        return context.proceed();
    }
}

package com.weini.common.aop;

import com.auth0.jwt.interfaces.Claim;
import com.weini.POJO.Do.Commodity;
import com.weini.common.annotation.WeiniPermissionAnnotation;
import com.weini.mapper.AddressMapper;
import com.weini.mapper.UserMapper;
import com.weini.utils.JwtFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Map;

//本aop操作旨在判断用户操作的的用户是否是自己
@Component
@Aspect
public class WeiniPermissionValidateAspect {

    @Resource
    UserMapper userMapper;

    @Resource
    AddressMapper addressMapper;

    @Resource
    HttpServletRequest req;

    @Pointcut("@annotation(com.weini.common.annotation.WeiniPermissionAnnotation)")
    private void cut(){}

    @Before("@annotation(com.weini.common.annotation.WeiniPermissionAnnotation)")
    public void before(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();    //获取参数列表

        Signature signature = joinPoint.getSignature();

        CodeSignature codeSignature = (CodeSignature) signature;
        String[] parameterNames = codeSignature.getParameterNames();    //获取参数名

        MethodSignature methodSignature = (MethodSignature) signature;
        WeiniPermissionAnnotation annotation = methodSignature.getMethod().getAnnotation(WeiniPermissionAnnotation.class);
        String param = annotation.param();      //获取注解的参数
        Class<?> cla = annotation.cla();

        boolean catchParamNameflag = false;   //判断找到paramName对应方法上的参数名

        String authorization = req.getHeader("Authorization");

        Map<String, Claim> mes = JwtFactory.getMes(authorization);  //获取用户id
        String id = mes.get("id").asString();

        for (int i = 0; i < parameterNames.length; i++) {
            if(parameterNames[i].equals(param)){
                catchParamNameflag = true;

                Object objParam = args[i];
                if(objParam.getClass().equals(String.class)){
                    Class<String> c = String.class;
                    try {
                        Field value = setAccessible(c.getDeclaredField("value"));   //更改值
                        Field coder = setAccessible(c.getDeclaredField("coder"));
                        Field hash = setAccessible(c.getDeclaredField("hash"));

                        value.set(objParam,value.get(id));
                        coder.set(objParam,coder.get(id));
                        hash.set(objParam,hash.get(id));

                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }else if(objParam.getClass().equals(Commodity.class)){
                    Class<Commodity> c = Commodity.class;
                    try{
                        Field shopId = setAccessible(c.getDeclaredField("shop_id"));
                        shopId.set(objParam,"");

                    }catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }

                break;
            }
        }

    }

    private Field setAccessible(Field field){
        field.setAccessible(true);
        return field;
    }

}
//    Object[] args = joinPoint.getArgs();
//
//    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//    WeiniPermissionAnnotation annotation = methodSignature.getMethod().getAnnotation(WeiniPermissionAnnotation.class);
//
//    //分别获取paranm、method两个参数
//    String paramName = annotation.param();
//    String methodName = annotation.method();
//
//    CodeSignature signature = (CodeSignature)joinPoint.getSignature();
//    String[] parameterNames = signature.getParameterNames();
//    Class[] parameterTypes = signature.getParameterTypes();
//    String value = "";
//    boolean catchParamNameflag = false;   //判断找到paramName对应方法上的参数名
//        for (int i = 0; i < parameterNames.length; i++) {   //获取value所指向的参数
//        if(parameterNames[i].equals(paramName)){
//        catchParamNameflag = true;
//        if(parameterTypes[i].equals(String.class)){     //如果直接是一个String对象，那这个对应的值就是id
//        value = (String) args[i];
//        }else{
//        Class oClass = parameterTypes[i];
//        Method method = null;
//        try {
//        method = oClass.getMethod(methodName);
//        value = (String) method.invoke(args[i]);
//        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
//        throw NotPermissionException.Builder("权限不够！");
//        }
//        }
//        break;
//        }
//        }
//        if(!catchParamNameflag){
//        throw new IllegalArgumentException("未找到参数"+paramName);
//        }
//
//        User user = userMapper.selectById(value);
//        if(user!=null){
//        if(user.getRole().equals("admin")){     //管理员有权操作一切
//        return;
//        }
//        String authorization = req.getHeader("Authorization");
//        if(!JwtFactory.verify(authorization)){
//        throw NotPermissionException.Builder("token过期！");
//        }
//        Map<String, Claim> mes = JwtFactory.getMes(authorization);
//        String id = mes.get("id").asString();
//
//        if(!value.equals(id)){
//        throw NotPermissionException.Builder("权限不够！");
//        }
//
//        }else{
//        throw ParameterErrorException.Builder("无此用户！");
//        }
//        }
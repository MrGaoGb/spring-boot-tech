# 工程简介

# 延伸阅读

### Springboot集成SpringMvc
#### 1、启动Tomcat,解析web.xml
- ContextLoaderListener.init() ---> 创建父容器 ---> servletContext.setAttribuilt() -- 将父容器保存起来
#### 2、创建DispatcherServlet对象(父子容器)
```xml
<web-app>

     <!-- 监听器(父容器配置) ContextLoaderListener.init方法来创建父容器-->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    <context-param>
        <param-name>contextConfigLocation</param-name>
        <!-- 父容器加载文件路径 -->
        <param-value>/WEB-INF/spring-parent.xml</param-value>
    </context-param>

    <servlet>
        <servlet-name>app</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <!-- 子容器的文件路径 -->
            <param-value>/WEB-INF/spring.xml</param-value>
        </init-param>
        # 表示启动时加载
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        # servletMapping映射 
        <servlet-name>app</servlet-name>
        <url-pattern>/app/*</url-pattern>
    </servlet-mapping>

</web-app>
```
#### 3、调用DispatcherServlet对象的init方法 --> 完成spring容器的创建
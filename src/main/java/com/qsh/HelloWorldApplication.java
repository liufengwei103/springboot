package com.qsh;

import ch.qos.logback.core.db.DBHelper;
import com.qsh.config.MyConfig;
import com.qsh.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 主程序类
 *
 * @SpringBootApplication：这是一个springboot应用
 */
@SpringBootApplication
public class HelloWorldApplication {

    /**
     * 主方法
     */
    public static void main(String[] args) {
//        SpringApplication.run(HelloWorldApplication.class, args);

        /**
         * 返回IOC容器
         */
        ConfigurableApplicationContext run = SpringApplication.run(HelloWorldApplication.class, args);

        //1.查看所有定义的组件
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }

        //容器中组件个数
        int beanDefinitionCount = run.getBeanDefinitionCount();
        System.out.println("容器中组件个数:" + beanDefinitionCount);

        //2.@Cnfiguration
        // 从容器中获取组件(容器中的组件是单实例的)
        User user01 = run.getBean("user01", User.class);
        User user02 = run.getBean("user01", User.class);
        System.out.println("组件：" + (user01 == user02));

        //3.配置类本身也是组件
        //com.qsh.config.MyConfig$$EnhancerBySpringCGLIB$$c63206ac@5a5c128（是被SpringCGLIB增强的代理对象）
        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println("配置类Myconfig:" + bean);

        //4.proxyBeanMethods
        // 外部无论对配置类中的组件注册方法调用多少次，获取的都是之前注册到容器中的单实例对象
        //如果@Configuration(proxyBeanMethods = true)代理对象调用方法。springboot总会检查这个组件是否在容器中，保持组件单实例（有就拿没有才创建）
        User user = bean.user01();
        User user1 = bean.user01();
        System.out.println("调配置类中方法:" + (user == user1));

        //5.获取组件@Import
        System.out.println("=======");
        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }

        System.out.println(run.getBean(DBHelper.class));

        //6.@Conditional
        boolean user011 = run.containsBean("user01");
        System.out.println(user011);

    }
}

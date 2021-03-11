package com.qsh.config;

import ch.qos.logback.core.db.DBHelper;
import com.qsh.domain.Car;
import com.qsh.domain.User;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 三、@Configuration配置
 * 1、@Configuration 告诉springboot这是一个配置类（标注了@Configuration即成为了容器中的组件）
 * 2、@Bean: 给容器中添加组件，（容器中的组件默认是单实例的）
 * 3、配置类本身也是组件;
 * 4、proxyBeanMethods  代理bean的方法默认为true
 * springboot在底层configuration的2种配置full（全配置），lite(轻量级配置)
 * Full(proxyBeanMethods = true) 解决组件依赖场景
 * Lite(proxyBeanMethods = false)  false不会保存代理对象，每次调用都会产生一个新对象
 * 5、最佳实战：
 * 配置类组件之间无依赖关系，用Lite模式，加速容器启动，减少判断；
 * 配置类组件之间有依赖关系，方法会被调用得到之前的单实例组件，用Full模式。
 */
//@Configuration
@Configuration(proxyBeanMethods = true)

/**
 * 四、@Impotr导入组件
 * @Impotr 导入组件：调用无参构造器，创建指定类型的对象，放入到容器中
 *          默认组件的名字就是全类名(com.qsh.domain.User)
 */
@Import({User.class, DBHelper.class})

/**
 * 五、@Conditional条件装配
 * @ConditionalOnBean(name="tom") 加在类上，条件成立后下面所有组件才能生效，否则都不生效
 */
//@ConditionalOnBean(name="tom")

/**
 * 六、@ImportResource 导入资源
 *      导入Spring配置文件
 */
//@ImportResource("classpath:beans.xml")

/**
 * 七、
 * （2）第二种启用配置绑定的方法，在configuration问价中加注解
 * @EnableConfigurationProperties(Car.class)的作用有2个
 * 1是开启car的配置绑定功能
 * 2是将car添加到容器中
 *
 */
@EnableConfigurationProperties(Car.class)
public class MyConfig {

    /**
     * 1、@Bean: 给容器中添加组件，（容器中的组件默认是单实例的）
     * 2、方法名为组件id，返回类型就是组件类型，返回值时就是组件在容器中的实例
     * 3、默认方法名为组件名，自定义组件名的方法：@Bean("user")
     * 4、外部无论对配置类中的组件注册方法调用多少次，获取的都是之前注册到容器中的单实例对象
     */
    @Bean
    /**
     * @Conditional 条件装配
     * 当容器中有名字为tom的组件时，才注入user01组件
     */
//    @ConditionalOnBean(name="tom")
    public User user01() {
        //*** 给@Bean标注的方法传入了对象参数，这个参数的值就会从容器中找。
        return new User("zhangsan", 18);
    }
}
